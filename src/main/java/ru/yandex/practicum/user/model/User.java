package ru.yandex.practicum.user.model;

import lombok.Data;

@Data
public class User {
    Long id;
    String name;
    String email;
}
