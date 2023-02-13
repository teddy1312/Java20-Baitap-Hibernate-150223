package com.example.Java20BaitapHibernate150223.service;

import com.example.Java20BaitapHibernate150223.dto.UserDTO;
import com.example.Java20BaitapHibernate150223.entity.Roles;
import com.example.Java20BaitapHibernate150223.entity.Users;
import com.example.Java20BaitapHibernate150223.repository.UserRepository;
import com.example.Java20BaitapHibernate150223.service.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Map<String,Object>  getUserByEmailAndPassword(String email, String password) {
        Map<String,Object> map = new HashMap<>();

        UserDTO userDTO = new UserDTO();

        int count = userRepository.countByEmailAndPassword(email,password);
        if(count > 0){
            Users users = userRepository.findByEmail(email);

            userDTO.setId(users.getId());
            userDTO.setEmail(users.getEmail());
            userDTO.setPassword(users.getPassword());
            userDTO.setFullname(users.getFullname());
            userDTO.setPhone(users.getPhone());
            userDTO.setRole_id(users.getRoles().getId());

            map.put("Data",userDTO);
        } else {
            map.put("Error","Không tồn tại email hoặc mật khẩu không đúng");
        }

        return map;
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        Roles roles = new Roles();
        roles.setId(userDTO.getRole_id());

        Users users = new Users();
        users.setEmail(userDTO.getEmail());
        users.setPassword(userDTO.getPassword());
        users.setFullname(userDTO.getFullname());
        users.setPhone(userDTO.getPhone());
        users.setRoles(roles);

        try {
            userRepository.save(users);
            return true;
        } catch (Exception e){
            System.out.println("An error has occurred when save user | "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<UserDTO> list = new ArrayList<>();

        for (Users user : userRepository.findAll()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword(user.getPassword());
            userDTO.setFullname(user.getFullname());
            userDTO.setPhone(user.getPhone());
            userDTO.setRole_id(user.getRoles().getId());

            list.add(userDTO);
        }

        return list;
    }

}
