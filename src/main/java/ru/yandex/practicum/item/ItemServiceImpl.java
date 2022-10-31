package ru.yandex.practicum.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.exception.NotFoundRecordInBD;
import ru.yandex.practicum.item.model.Item;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    ItemRepository itemRepository;
    
    @Autowired
    public ItemServiceImpl(@Qualifier("InMemory") ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    /**
     * Добавить вещь в репозиторий.
     *
     * @param item добавленная вещь.
     * @return добавленная вещь.
     */
    @Override
    public Item add(Item item) {
        
        return itemRepository.add(item);
    }
    
    /**
     * Получить список вещей.
     *
     * @return список вещей.
     */
    @Override
    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }
    
    /**
     * Получить вещь по ID.
     *
     * @param id ID вещи.
     * @return запрашиваемая вещь.
     */
    @Override
    public Item getItemById(Long id) {
        Item result;
        if (itemRepository.isExcludeItemById(id)) {
            throw new NotFoundRecordInBD("Запись о вещи не найдена в БД.");
        }
        result = itemRepository.getItemById(id);
        return result;
    }
    
    /**
     * Есть ли запрашиваемая вещь с ID в хранилище.
     *
     * @param id ID запрашиваемой вещи.
     * @return запрашиваемач вещь.
     */
    @Override
    public Boolean isExcludeItemById(Long id) {
        return null;
    }
    
    /**
     * Удалить вещь с ID из хранилища.
     *
     * @param id ID удаляемой вещи.
     */
    @Override
    public void removeItemById(Long id) {
    
    }
}
