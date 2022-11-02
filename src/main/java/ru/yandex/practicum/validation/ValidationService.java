package ru.yandex.practicum.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.exception.NotFoundRecordInBD;
import ru.yandex.practicum.exception.ValidateException;
import ru.yandex.practicum.item.ItemService;
import ru.yandex.practicum.user.model.User;
import ru.yandex.practicum.user.service.UserService;

@Service
@Slf4j
public class ValidationService {
    ItemService itemService;
    UserService userService;
    
    
    /**
     * Проверка пользователя на уникальность почты.
     *
     * @param user пользователь.
     * @throws ValidateException генерируемое исключение.
     */
    public void checkUniqueEmail(User user) throws ValidateException {
        final String email = user.getEmail();
        if (userService.isExistUserByEmail(email)) {
            String message = "Пользователь с таким Email уже есть в БД.";
            log.info(message);
            throw new ValidateException(message);
        }
    }
    
    /**
     * Проверка всех полей пользователя.
     *
     * @param user пользователь.
     * @throws ValidateException генерируемое исключение.
     */
    public void validateUserFields(User user) {
        final String email = user.getEmail();
        if (email == null || email.isBlank()) {
            String error = "Email пользователя не может пустым.";
            log.info(error);
            throw new ValidateException(error);
        }
        
        final String name = user.getName();
        if (name == null || name.isBlank()) {
            String error = "Имя пользователя не может быть пустым.";
            log.info(error);
            throw new ValidateException(error);
        }
    }
    
    /**
     * Проверка наличия юзера в БД.
     *
     * @param id пользователя.
     * @throws NotFoundRecordInBD - пользователь найден в БД.
     */
    public void checkExistUserInDB(Long id) {
        if (userService.getUserById(id) == null) {
            String error = String.format("Error 404. Пользователь с ID = '%d' не найден в БД.", id);
            log.info(error);
            throw new NotFoundRecordInBD(error);
        }
    }
    
}
