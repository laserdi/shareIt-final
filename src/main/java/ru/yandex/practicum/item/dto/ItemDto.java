package ru.yandex.practicum.item.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ItemDto {
    Long id;            //Идентификатор вещи.
    String name;        //Название вещи.
    String description; //Описание вещи.
    Long ownerId;       //ID хозяина вещи.
    Boolean available;  //Статус для бронирования: свободна, занята.
    Set<Long> reviews;  //ID фидбеков на вещь.
}
