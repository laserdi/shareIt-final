package ru.yandex.practicum.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.exception.ValidateException;
import ru.yandex.practicum.item.ItemRepository;
import ru.yandex.practicum.user.model.User;

@Service
@Slf4j
public class ValidationService {
    ItemRepository itemRepository;
    
    void validateUser(User user) throws ValidateException {
    
    }
}
