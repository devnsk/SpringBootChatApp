package com.nsk.websocket.service;

import com.nsk.websocket.entity.Status;
import com.nsk.websocket.entity.User;
import com.nsk.websocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

public void saveUser(User user){
    user.setStatus(Status.ONLINE);
    userRepository.save(user);
}

    public void disconnect(User user){
    var storedUser =  userRepository.findById(user.getNickName()).orElse(null);
    if(storedUser != null){
        storedUser.setStatus(Status.OFFLINE);
        userRepository.save(storedUser);
    }
    }

    public List<User> getAllUsers(){

    return userRepository.findAllByStatus(Status.ONLINE);
    }

}
