package com.hcl.bankingservice.controller;

import com.hcl.bankingservice.dto.UserDto;
import com.hcl.bankingservice.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userService/v1/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        LOGGER.info("Retrieved all users");
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id){

        LOGGER.info("getOneById - {}", id);
        final UserDto result = userService.getOneById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserDto> creat(@RequestBody UserDto userDto) {
        LOGGER.info("{} - create", userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        final UserDto result = userService.create(userDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        LOGGER.info("User created: {}",userDto.getUsername());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
        LOGGER.info("update - {}", userDto);
        final UserDto result = userService.update(userDto);
        if (result == null) {
            return ResponseEntity.unprocessableEntity()
                    .build();
        }
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        LOGGER.info("delete - {}", id);
        final boolean isDeleted = userService.delete(id);
        if (!isDeleted) {
            return ResponseEntity.badRequest()
                    .build();
        }
        return ResponseEntity.noContent()
                .build();
    }

}
////package com.hcl.bankingservice.controller;
////
////import com.hcl.bankingservice.dto.UserDto;
////import com.hcl.bankingservice.service.UserService;
////import io.swagger.v3.oas.annotations.security.SecurityRequirement;
////import jakarta.validation.Valid;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.security.access.prepost.PreAuthorize;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.List;
////
////@RestController
////@RequestMapping("/api/customerService/v1/users")
////public class UserController {
////    private static final String controllers = "USerController";
////
////    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());
////
////    @Autowired
////    private UserService userService;
////
////    @Autowired
////    private PasswordEncoder passwordEncoder;
////
////    @GetMapping
////    public ResponseEntity<List<UserDto>> getAllUsers(){
////        List<UserDto> users = userService.getAllUsers();
////        LOGGER.info("Retrieved all users");
////        return ResponseEntity.ok(users);
////    }
////    @GetMapping("{id}")
////    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id){
////        UserDto userDto = userService.getOneById(id);
////        if(userDto != null){
////            LOGGER.info("Retrieved user by Id: {}",id);
////            return ResponseEntity.ok(userDto);
////        }
////        else{
////            LOGGER.warn("User not found with ID : {}",id);
////            return ResponseEntity.notFound().build();
////        }
////    }
////    @PostMapping
////    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
////        LOGGER.info("{} - create", userDto);
////        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
////        final UserDto result = userService.create(userDto);
////        if (result == null) {
////            return ResponseEntity.unprocessableEntity().build();
////        }
////        LOGGER.info("User created: {}",userDto.getUsername());
////        return new ResponseEntity<>(result, HttpStatus.CREATED);
////    }
////
////    @PutMapping
////    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
////        LOGGER.info("update - {}", userDto);
////        final UserDto result = userService.update(userDto);
////        if (result == null) {
////            return ResponseEntity.unprocessableEntity()
////                    .build();
////        }
////        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
////    }
////
////    @DeleteMapping("{id}")
////    public ResponseEntity<Void> delete(@PathVariable long id) {
////        LOGGER.info("delete - {}", id);
////        final boolean isDeleted = userService.delete(id);
////        if (!isDeleted) {
////            return ResponseEntity.badRequest()
////                    .build();
////        }
////        return ResponseEntity.noContent()
////                .build();
////    }
////
////}
//package com.hcl.bankingservice.controller;
//
//import com.hcl.bankingservice.dto.UserDto;
//import com.hcl.bankingservice.service.UserService;
//import jakarta.validation.Valid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/userService/v1/users")
//public class UserController {
//    private static final String controllers = "USerController";
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getName());
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @GetMapping
//    public ResponseEntity<List<UserDto>> getAll() {
//        LOGGER.info("{} - getAll", controllers);
//        final List<UserDto> result = userService.getAllUsers();
//        return ResponseEntity.ok(result);
//    }
//
//    @PostMapping
//    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
//        LOGGER.info("{} - create", userDto);
//        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        final UserDto result = userService.create(userDto);
//        if (result == null) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//    }
//    @PutMapping
//    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto) {
//        LOGGER.info("update - {}", userDto);
//        final UserDto result = userService.update(userDto);
//        if (result == null) {
//            return ResponseEntity.unprocessableEntity()
//                    .build();
//        }
//        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> delete(@PathVariable long id) {
//        LOGGER.info("delete - {}", id);
//        final boolean isDeleted = userService.delete(id);
//        if (!isDeleted) {
//            return ResponseEntity.badRequest()
//                    .build();
//        }
//        return ResponseEntity.noContent()
//                .build();
//    }
//
//
//
//}
//
//
//
////package com.hcl.bankingservice.controller;
////
////import com.hcl.bankingservice.model.User;
////import com.hcl.bankingservice.repository.UserRepository;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestBody;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RestController;
////
////import java.util.List;
////
////@RestController
////@RequestMapping("/api/customerService/v1/users")
////public class UserController {
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private PasswordEncoder passwordEncoder;
////
////    @GetMapping
////    public List<User> getAll() {
////        return userRepository.findAll();
////    }
////
////    @PostMapping
////    public User create(@RequestBody User user) {
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
////        return userRepository.save(user);
////    }
////}
////
////
//

