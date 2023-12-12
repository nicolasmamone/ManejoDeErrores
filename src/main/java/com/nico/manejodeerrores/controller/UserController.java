package com.nico.manejodeerrores.controller;

import com.nico.manejodeerrores.exception.ResourceNotFoundException;
import com.nico.manejodeerrores.model.User;
import com.nico.manejodeerrores.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Long userId){
        verifyUser(userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody User user){
        user = userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    protected void verifyUser(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new ResourceNotFoundException("Usuario con id: "+ userId +" no encontrado. " );
        }
    }
}
