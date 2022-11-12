package ru.yandex.practicum.feedback;

import java.util.List;

public interface FeedbackRepository {
    /**
     * Получить все отзывы о вещах.
     * @return список отзывов.
     */
    List<Feedback> getAllFeedbacks();
    
    /**
     * Получить отзыв по его ID.
     * @param id ID отзыва.
     * @return запрашиваемый отзыв.
     */
    Feedback getFeedbackById(Long id);
    
    /**
     * Получить отзывы на вещь с id.
     * @param itemId ID вещи, для которой надо найти отзывы.
     * @return список отзывов вещи с ID.
     */
    List<Feedback> getFeedbacksByItemId(Long itemId);
    
    /**
     * Есть ли запрашиваемая отзыв с ID в хранилище.
     * @param id ID запрашиваемого отзыва.
     * @return запрашиваемый отзыв.
     */
    Boolean isExcludeItemById(Long id);
    
    /**
     * Удалить отзыв с ID из хранилища.
     * @param id ID удаляемого отзыва.
     */
    void removeFeedbackById(Long id);
    
    /**
     * Удалить все отзывы вещи с ID = itemId.
     * @param itemId ID вещи.
     */
    void removeFeedbacksByItemId(Long itemId);
}
