package ru.yandex.practicum.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.exception.ValidateException;
import ru.yandex.practicum.item.dto.ItemDto;
import ru.yandex.practicum.item.mapper.ItemMapper;
import ru.yandex.practicum.item.model.Item;
import ru.yandex.practicum.item.service.ItemService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ItemController {
    private final ItemMapper mapper;
    private final ItemService itemService;
    
    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable Long itemId) {
        return mapper.mapToDto(itemService.getItemById(itemId));
    }
    
    @GetMapping()
    public List<ItemDto> getAll(@RequestHeader("X-Sharer-User-Id") Long userId) {
        List<ItemDto> result = itemService.getAllItems(userId).stream()
                .filter(item -> item.getOwnerId().equals(userId))
                .map(mapper::mapToDto).collect(Collectors.toList());
        return result;
    }
    
    @GetMapping("/search")
    public List<ItemDto> searchItemsByText(@RequestParam(value = "text", required = false) String text) {
        if (text == null || text.isBlank()) {
            String message = String.format("По запросу поиска '%s' передан пустой список.", text);
            log.info(message);
            return Collections.emptyList();
        }
        
        List<ItemDto> list = new ArrayList<>();
        for (Item item : itemService.searchItemsByText(text)) {
            ItemDto itemDto = mapper.mapToDto(item);
            list.add(itemDto);
        }
        String message = String.format("По запросу поиска '%s' передан список: %s", text, list);
        log.info(message);
        return list;
    }
    
    /**
     * Добавление новой вещи. Будет происходить по эндпойнту POST /items. На вход поступает объект ItemDto. userId в
     * заголовке X-Sharer-User-Id — это идентификатор пользователя, который добавляет вещь. Именно этот пользователь —
     * владелец вещи. Идентификатор владельца будет поступать на вход в каждом из запросов, рассмотренных далее.
     * @return
     */
    @PostMapping
    public ItemDto add(@RequestHeader(value = "X-Sharer-User-Id", required = false) Long ownerId,
                       @RequestBody @Validated ItemDto itemDto) {
        itemDto.setOwnerId(ownerId);
        return mapper.mapToDto(itemService.add(mapper.mapToModel(itemDto), ownerId));
    }
    
    @PatchMapping("{itemId}")
    public ItemDto update(@RequestHeader(value = "X-Sharer-User-Id", required = false) Long ownerId,
                          @PathVariable Long itemId, @Validated @RequestBody ItemDto itemDto) {
        if (ownerId == null) {
            String message = "Для обновления надо передать ID хозяина вещи.";
            log.info("Error 400. " + message);
            throw new ValidateException(message);
        }
        itemDto.setOwnerId(ownerId);
        itemDto.setId(itemId);
        Item item = mapper.mapToModel(itemDto);
        ItemDto result = mapper.mapToDto(itemService.updateInStorage(item, ownerId));
        log.info("Была обновлена вещь {}, id = {}", result.getName(), result.getId());
        return result;
    }
    
}
