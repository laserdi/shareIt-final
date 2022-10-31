package ru.yandex.practicum.item;

import ru.yandex.practicum.item.model.Item;

import java.util.List;

public interface ItemService {
    
    /**
     * Добавить вещь в репозиторий.
     * @param item добавленная вещь.
     * @return добавленная вещь.
     */
    Item add(Item item);
    /**
     * Получить список вещей.
     *
     * @return список вещей.
     */
    List<Item> getAllItems();
    
    /**
     * Получить вещь по ID.
     *
     * @param id ID вещи.
     * @return запрашиваемая вещь.
     */
    Item getItemById(Long id);
    
    /**
     * Есть ли запрашиваемая вещь с ID в хранилище.
     *
     * @param id ID запрашиваемой вещи.
     * @return запрашиваемач вещь.
     */
    Boolean isExcludeItemById(Long id);
    
    /**
     * Удалить вещь с ID из хранилища.
     *
     * @param id ID удаляемой вещи.
     */
    void removeItemById(Long id);
    
}
