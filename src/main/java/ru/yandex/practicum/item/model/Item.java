package ru.yandex.practicum.item.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Set;

@Data
public class Item {
    private enum Status {free, busy}  //свободна, занята
    
    Long id;            //Идентификатор вещи.
    String name;        //Название вещи.
    String description; //Описание вещи.
    Long ownerId;       //ID хозяина вещи.
    Boolean available;  //Статус для бронирования: свободна, занята.
    @JsonIgnore
    Boolean isRequest;  //Вещь создана по запросу ищущего пользователя (True - да)?
    Set<Long> reviews;  //ID фидбеков на вещь.
}
