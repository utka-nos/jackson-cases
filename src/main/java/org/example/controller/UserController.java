package org.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDTO;
import org.example.dto.views.JsonViews;
import org.example.model.User;
import org.example.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    /**
     * Добавляем нового пользователя
     * @param userDTO - Из тела запроса получаем пользователя
     * @return сохраненного пользователя
     */
    @PostMapping
    @JsonView(JsonViews.Main.class)
    public UserDTO addNewUser(
            @RequestBody UserDTO userDTO
    ) {
        log.info("received userDTO: {{}}", userDTO);
        User userToSave = modelMapper.map(userDTO, User.class);
        User savedUser = userRepo.save(userToSave);
        return modelMapper.map(savedUser, UserDTO.class);
    }


    /**
     * Получаем полную информацию о пользователе
     * @param id - идентификатор пользователя
     * @return полная информация о пользователе
     */
    @GetMapping("/{id}")
    @JsonView(JsonViews.Full.class)
    public UserDTO getUserInfo(
            @PathVariable Long id
    ){
        User user = userRepo.findById(id).get();
        return modelMapper.map(user, UserDTO.class);
    }

}
