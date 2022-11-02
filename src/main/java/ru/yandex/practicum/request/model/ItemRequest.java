package ru.yandex.practicum.request.model;

import java.time.LocalDateTime;

public class ItemRequest {
    Long id;                //уникальный идентификатор запроса;
    String description;     //текст запроса, содержащий описание требуемой вещи;
    Long requestor;         //пользователь, создавший запрос;
    LocalDateTime created;  //дата и время создания запроса.
}
