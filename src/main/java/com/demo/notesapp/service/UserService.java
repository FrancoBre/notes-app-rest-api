package com.demo.notesapp.service;

import com.demo.notesapp.dto.UserDTO;
import com.demo.notesapp.mapper.UserMapper;
import com.demo.notesapp.model.User;
import com.demo.notesapp.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserDTO> getAll() {
        List<User> result = (List<User>) repository.findAll();
        if (!result.isEmpty()) {
            return UserMapper.mapToDTO(result);
        } else {
            return new ArrayList<>();
        }
    }

    public UserDTO getUserById(Long id) throws EntityNotFoundException {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return UserMapper.mapToDTO(user.get());
        } else {
            throw new EntityNotFoundException("No user record exist for given id");
        }
    }

    public UserDTO createOrUpdate(UserDTO userDTO) {
        User oldEntity = UserMapper.mapToEntity(userDTO);

        if (oldEntity.getId() == null) {
            User newEntity = repository.save(oldEntity);
            return UserMapper.mapToDTO(newEntity);
        } else {
            Optional<User> user = repository.findById(oldEntity.getId());
            if (user.isPresent()) {
                User newEntity = user.get();
                newEntity.setUsername(oldEntity.getUsername());
                newEntity = repository.save(newEntity);
                return UserMapper.mapToDTO(newEntity);
            } else {
                user = Optional.of(repository.save(oldEntity));
                return UserMapper.mapToDTO(user.get());
            }
        }
    }

    public void deleteUserById(Long id) throws EntityNotFoundException {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("No user record exist for given id");
        }
    }
}