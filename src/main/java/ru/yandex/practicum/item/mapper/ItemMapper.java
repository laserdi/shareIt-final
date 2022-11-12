package ru.yandex.practicum.item.mapper;

import org.mapstruct.Mapper;
import ru.yandex.practicum.item.dto.ItemDto;
import ru.yandex.practicum.item.model.Item;
//Mapstruct хватит, чтобы на основании интерфейса UserMapper на этапе компиляции сгенерировать нужную
// реализацию, которая будет переводить.

@Mapper(componentModel = "spring")
public interface ItemMapper {
    /**
     * Из объекта для ответа в контроллере в юзера.
     */
    Item mapToModel(ItemDto itemDto);
    
    /**
     * Из юзера в объект для ответа в контроллере.
     */
    ItemDto mapToDto(Item item);
}
