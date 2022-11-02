package ru.yandex.practicum.user.mapper;

import org.mapstruct.Mapper;
import ru.yandex.practicum.user.dto.UserDto;
import ru.yandex.practicum.user.model.User;

//Mapstruct хватит, чтобы на основании интерфейса UserMapper на этапе компиляции сгенерировать нужную
// реализацию, которая будет переводить.
@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Из объекта для ответа в контроллере в юзера.
     */
    User mapToModel(UserDto itemDto);
    /**
    * Из юзера в объект для ответа в контроллере.
    */
    UserDto mapToDto(User user);
}
