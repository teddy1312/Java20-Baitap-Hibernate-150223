package com.example.Java20BaitapHibernate150223.service.imp;

import com.example.Java20BaitapHibernate150223.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String,Object> getUserByEmailAndPassword(String email, String password);
    boolean saveUser(UserDTO userDTO);
    List<UserDTO> getAllUser();
}
