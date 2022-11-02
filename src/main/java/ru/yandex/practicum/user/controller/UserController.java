package ru.yandex.practicum.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.user.dto.UserDto;
import ru.yandex.practicum.user.mapper.UserMapper;
import ru.yandex.practicum.user.model.User;
import ru.yandex.practicum.user.service.UserService;
import ru.yandex.practicum.validation.ValidationService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
@Slf4j
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService service;
    private UserMapper mapper;
    private ValidationService validationService;
    
    /**
     * Добавить юзера в БД.
     *
     * @param userDto пользователь.
     * @return добавляемый пользователь.
     */
    @PostMapping
    ResponseEntity<UserDto> addToStorage(@RequestBody UserDto userDto) {
        
        User user = mapper.mapToModel(userDto);
        User createdUser = service.addToStorage(user);
        
        ResponseEntity<UserDto> response = new ResponseEntity<>(
                mapper.mapToDto(createdUser), HttpStatus.CREATED);
        String message = String.format("В БД добавлен новый пользователь:\t%s", response.getBody());
        log.info(message);
        return response;
    }
    
    /**
     * Обновить юзера в БД.
     *
     * @param user пользователь
     * @return обновлённый пользователь.
     */
    User updateInStorage(User user) {
        return null;
    }
    
    /**
     * Удалить пользователя из БД.
     *
     * @param id ID удаляемого пользователя.
     */
    ResponseEntity<String> removeFromStorage(Long id) {
        service.removeFromStorage(id);
        // TODO: 01.11.2022 Удалить вещи пользователя.
        String message = String.format("Выполнено удаление пользователя с ID = %d.", id);
        log.info(message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    /**
     * Получить список всех пользователей.
     *
     * @return список пользователей.
     */
    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsersFromStorage() {
        List<UserDto> allUsersDto = new ArrayList<>();
        List<User> allUsers = service.getAllUsers();
        
        allUsers.stream().map(user -> mapper.mapToDto(user)).forEach(allUsersDto::add);
        
        ResponseEntity<List<UserDto>> response = new ResponseEntity<>(
                allUsersDto, HttpStatus.OK);
        log.info("Выдан список всех пользователей.");
        return response;
    }
    
    /**
     * Получить пользователя по ID.
     *
     * @param id ID пользователя.
     * @return User - пользователь присутствует в библиотеке.
     * <p>null - пользователя нет в библиотеке.</p>
     */
    ResponseEntity<UserDto> getUserById(Long id) {
        ResponseEntity<UserDto> response = new ResponseEntity<>(
                mapper.mapToDto(service.getUserById(id)), HttpStatus.OK);
        String message = String.format("Выдан ответ на запрос пользователя по ID = %d:\t%s", id, response);
        log.info(message);
        return response;
    }
    
}
