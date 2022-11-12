package ru.yandex.practicum.user.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class User {
    Long id;        //ID пользователя.
    String name;    //Имя пользователя.
    String email;   //Электронная почта.
}
