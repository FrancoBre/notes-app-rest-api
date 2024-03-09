package com.demo.notesapp.mapper;

import com.demo.notesapp.dto.UserDTO;
import com.demo.notesapp.model.User;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        return user;
    }

    public static UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public static List<UserDTO> mapToDTO(List<User> users) {
        return users.stream().map(UserMapper::mapToDTO).toList();
    }
}
