package ru.yandex.practicum.item;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.item.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Qualifier("InMemory")
public class ItemRepositoryImpl implements ItemRepository {
    
    Map<Long, Item> itemMap = new HashMap<>();
    
    /**
     * Добавить вещь в репозиторий.
     * @param item добавленная вещь.
     * @return добавленная вещь.
     */
    @Override
    public Item add(Item item) {
        return itemMap.put(item.getId(), item);
    }
    
    /**
     * Получить список всех вещей.
     *
     * @return список вещей.
     */
    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(itemMap.values());
    }
    
    /**
     * Получить вещь по ID.
     *
     * @param id ID вещи.
     * @return запрашиваемая вещь.
     */
    @Override
    public Item getItemById(Long id) {
        return itemMap.get(id);
    }
    
    /**
     * Есть ли вещь с ID в хранилище?
     *
     * @param id ID запрашиваемой вещи.
     * @return True - вещь есть в хранилище, False - вещи нет в хранилище.
     */
    @Override
    public Boolean isExcludeItemById(Long id) {
        return itemMap.containsKey(id);
    }
    
    /**
     * Удалить вещь с ID из хранилища.
     *
     * @param id ID удаляемой вещи.
     */
    @Override
    public void removeItemById(Long id) {
        itemMap.remove(id);
    }
    
    /**
     * Удалить вещи пользователя с ID = userId.
     *
     * @param userId ID пользователя, вещи которого надо удалить.
     */
    @Override
    public void removeItemsByUserId(Long userId) {
        List<Long> idForRemove = new ArrayList<>();
        
        for (Item item : itemMap.values()) {
            if (item.getOwnerId().equals(userId)) {
                idForRemove.add(item.getId());
            }
        }
        idForRemove.forEach(id -> itemMap.remove(id));
    }
}
