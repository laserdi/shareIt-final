package ru.yandex.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.item.model.Item;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    
    
    //прочитать заголовки
    @GetMapping(value = "/get-headers")
    public ResponseEntity<?> getHeaders(@RequestHeader Map<String, String> headers){//представляет заголовки ввиде мапы,
        //где ключ это наименование заголовка, а значение мапы - это значение заголовка
        return ResponseEntity.ok(headers);
    }
    
    
    /**
     * Добавление новой вещи. Будет происходить по эндпойнту POST /items.
     * На вход поступает объект ItemDto.
     * userId в заголовке X-Sharer-User-Id — это идентификатор пользователя,
     * который добавляет вещь. Именно этот пользователь — владелец вещи.
     * Идентификатор владельца будет поступать на вход в каждом из запросов,
     * рассмотренных далее.
     * @return
     */
    @PostMapping
    public Item add(Item item) {
        return itemService.add(item);
        
    }
    
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
}
