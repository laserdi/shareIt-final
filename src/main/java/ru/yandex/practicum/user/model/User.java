package ru.yandex.practicum.user.model;

import lombok.Data;


@Data
public class User {
    /**
     * ID пользователя.
     */
    Long id;        //ID
    /**
     * Имя пользователя.
     */
    String name;    //Имя пользователя.
    /**
     * Электронная почта.
     */
    String email;   //Электронная почта.
}
