package ru.yandex.practicum.feedback;

import lombok.Data;

/**
 * Отзыв о бронировании.
 */
@Data
public class Feedback {
    Long id;
    Long userId;
    Long itemId;
    String content;
}
