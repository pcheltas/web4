package com.pcheltas.web4.services;

import com.pcheltas.web4.exceptions.TokenUnauthorizedException;
import com.pcheltas.web4.exceptions.UserAlreadyExistsException;
import com.pcheltas.web4.model.User;
import com.pcheltas.web4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User userInit(String username, String token){
        User user = new User();
        user.setUsername(username);
        user.setToken(token);

        return user;
    }

    public boolean isUserExists(User user){
        return userRepository.findUserByUsername(user.getUsername()) != null;
    }

    public boolean isUserValid(User user){
        return (user.getUsername() != null && user.getToken() != null);
    }


    public void addUser(User user) {
        if (isUserExists(user)){
           throw new UserAlreadyExistsException("User with this username already exists");
        }
        if (!isUserValid(user)){
            throw new TokenUnauthorizedException("Invalid data for registration");
        }

        userRepository.save(user);
    }

    public void checkUser(User user){
        User existingUser = userRepository.findUserByUsername(user.getUsername());

        if (existingUser == null){
            throw new TokenUnauthorizedException("No such user");
        }

        if (!existingUser.getToken().equals(user.getToken())){
            throw new TokenUnauthorizedException("Invalid password");
        }


    }


}
