package ru.yandex.practicum.feedback;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class FeedbackRepositoryImpl implements FeedbackRepository {
    Map<Long, Feedback> feedbackMap = new HashMap<>();
    
    /**
     * Получить все отзывы о вещах.
     * @return список отзывов.
     */
    @Override
    public List<Feedback> getAllFeedbacks() {
        return new ArrayList<>(feedbackMap.values());
    }
    
    /**
     * Получить отзыв по его ID.
     * @param id ID отзыва.
     * @return запрашиваемый отзыв.
     */
    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackMap.get(id);
    }
    
    /**
     * Получить список отзывов на вещь с id.
     * @param itemId ID вещи, для которой надо найти отзывы.
     * @return список отзывов вещи с ID.
     */
    @Override
    public List<Feedback> getFeedbacksByItemId(Long itemId) {
        List<Feedback> result = feedbackMap.values().stream()
                .filter(fb -> fb.getItemId().equals(itemId)).collect(Collectors.toList());
        return result;
    }
    
    /**
     * Есть ли запрашиваемый отзыв с ID в хранилище.
     * @param id ID запрашиваемого отзыва.
     * @return запрашиваемый отзыв.
     */
    @Override
    public Boolean isExcludeItemById(Long id) {
        return feedbackMap.containsKey(id);
    }
    
    /**
     * Удалить отзыв с ID из хранилища.
     * @param id ID удаляемого отзыва.
     */
    @Override
    public void removeFeedbackById(Long id) {
        feedbackMap.remove(id);
    }
    
    /**
     * Удалить все отзывы вещи с ID = itemId.
     * @param itemId ID вещи.
     */
    @Override
    public void removeFeedbacksByItemId(Long itemId) {
        List<Long> idForRemove = new ArrayList<>();
        
        for (Feedback fb : feedbackMap.values()) {
            if (fb.getItemId().equals(itemId)) {
                idForRemove.add(fb.getId());
            }
        }
        idForRemove.forEach(id -> feedbackMap.remove(id));
    }
}
