package com.project.project.service;

import com.project.project.dto.RegistrationDTO;
import com.project.project.exceptions.UsernameNotFound;
import com.project.project.exceptions.UsernameTaken;
import com.project.project.model.Role;
import com.project.project.model.User;
import com.project.project.repository.RoleRepository;
import com.project.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User findOne(String username) throws UsernameNotFound {

        return userRepository.findOneByUsername(username).orElseThrow(() -> new UsernameNotFound(username));
    }

    public User save(RegistrationDTO registrationDTO) throws UsernameTaken {


        Optional<User> user = userRepository.findOneByUsername(registrationDTO.getUsername());

        if (user.isPresent())
        {
           throw new UsernameTaken();
        } else {
            User newUser = new User();

            newUser.setFirstname(registrationDTO.getFirstname());
            newUser.setLastname(registrationDTO.getLastname());
            newUser.setUsername(registrationDTO.getUsername());
            newUser.setPassword(registrationDTO.getPassword());
            newUser.setEmail(registrationDTO.getEmail());
            newUser.setAddress(registrationDTO.getAddress());
            newUser.setPhone(registrationDTO.getPhone());

            HashSet<Role> roles = new HashSet<Role>();
            Role role = roleRepository.findOneById(1L);
            roles.add(role);
            newUser.setRoles(roles);

            return userRepository.save(newUser);
        }
    }
}
