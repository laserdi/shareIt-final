package ru.yandex.practicum.user.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@Slf4j
@Qualifier("InMemory")
public class UserRepositoryImpl implements UserRepository {
    
    private final HashMap<Long, User> userMap = new HashMap<>();
    
    private Long counter = 0L;
    
    /**
     * Добавить юзера в БД.
     *
     * @param user пользователь.
     * @return добавляемый пользователь.
     */
    @Override
    public User addToStorage(User user) {
        user.setId(++counter);
        userMap.put(user.getId(), user);
        return user;
    }
    
    /**
     * Обновить юзера в БД.
     *
     * @param user пользователь
     * @return обновлённый пользователь.
     */
    @Override
    public User updateInStorage(User user) {
        userMap.put(user.getId(), user);
        return user;
    }
    
    /**
     * Удалить пользователя из БД.
     *
     * @param id ID удаляемого пользователя.
     */
    @Override
    public void removeFromStorage(Long id) {
        userMap.remove(id);
    }
    
    /**
     * Получить список всех пользователей.
     *
     * @return список пользователей.
     */
    @Override
    public List<User> getAllUsersFromStorage() {
        return new ArrayList<>(userMap.values());
    }
    
    /**
     * Получить пользователя по ID.
     *
     * @param id ID пользователя.
     * @return User - пользователь присутствует в библиотеке.
     * <p>null - пользователя нет в библиотеке.</p>
     */
    @Override
    public User getUserById(Long id) {
        return userMap.get(id);
    }
    
    /**
     * Проверка наличия юзера в БД.
     *
     * @param id пользователя.
     * @return True - пользователь найден. False - пользователя нет в БД.
     */
    @Override
    public boolean isExistUserInDB(Long id) {
        return userMap.containsKey(id);
    }
    
    /**
     * Проверка наличия пользователя по `Email`.
     *
     * @param newEmail адрес эл. почты нового пользователя.
     * @return True - пользователь с Email есть в БД. False - нет.
     */
    @Override
    public boolean isExistUserByEmail(String newEmail) {
        return getAllUsersFromStorage().stream().map(User::getEmail).anyMatch(email->email.equals(newEmail));
    }
}
