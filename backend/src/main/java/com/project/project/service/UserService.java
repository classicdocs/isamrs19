package com.project.project.service;

import com.project.project.dto.UserRegistrationDTO;
import com.project.project.exceptions.UsernameNotFound;
import com.project.project.exceptions.UsernameTaken;
import com.project.project.model.RegisteredUser;
import com.project.project.model.Role;
import com.project.project.model.User;
import com.project.project.repository.RoleRepository;
import com.project.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User findOne(String username) throws UsernameNotFound {

        return userRepository.findOneByUsername(username).orElseThrow(() -> new UsernameNotFound(username));
    }

    public User save(UserRegistrationDTO userRegistrationDTO) throws UsernameTaken {


        Optional<User> user = userRepository.findOneByUsername(userRegistrationDTO.getUsername());

        if (user.isPresent()) {
            throw new UsernameTaken();
        } else {
            RegisteredUser newUser = new RegisteredUser();

            newUser.setFirstname(userRegistrationDTO.getFirstname());
            newUser.setLastname(userRegistrationDTO.getLastname());
            newUser.setUsername(userRegistrationDTO.getUsername());
            newUser.setPassword(userRegistrationDTO.getPassword());
            newUser.setEmail(userRegistrationDTO.getEmail());
            newUser.setAddress(userRegistrationDTO.getAddress());
            newUser.setPhone(userRegistrationDTO.getPhone());

            Role role = roleRepository.findOneById(1L);
            newUser.setRole(role);

            newUser.setFriends(new HashSet<RegisteredUser>());

            return userRepository.save(newUser);
        }
    }
}
