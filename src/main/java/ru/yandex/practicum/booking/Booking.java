package ru.yandex.practicum.booking;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {
    Long id;                    //ID бронирования
    Long itemId;                //ID вещи
    Long userId;                //ID юзера.
    LocalDateTime startTime;    //Дата начала бронирования
    LocalDateTime endTime;      //Дата окончания бронирования
}
