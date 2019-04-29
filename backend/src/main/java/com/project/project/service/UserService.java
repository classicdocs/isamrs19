package com.project.project.service;

import com.project.project.dto.ChangePasswordDTO;
import com.project.project.dto.FriendDTO;
import com.project.project.dto.UserRegistrationDTO;
import com.project.project.exceptions.UserNotFound;
import com.project.project.exceptions.UserNotLoggedFirstTime;
import com.project.project.exceptions.UsernameNotFound;
import com.project.project.exceptions.UsernameTaken;
import com.project.project.model.Friendship;
import com.project.project.model.RegisteredUser;
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

            newUser.setFriends(new HashSet<Friendship>());

            return userRepository.save(newUser);
        }
    }

    public UserRegistrationDTO update(UserRegistrationDTO userDTO, Long id) throws UsernameNotFound, UsernameTaken {

        Optional<User> user = userRepository.findOneById(id);

        if (user.isPresent()) {
            Optional<User> user2 = userRepository.findOneByUsernameAndIdNot(userDTO.getUsername(), id);
            if (user2.isPresent()) {
                throw new UsernameTaken();
            } else {
                user.get().setUsername(userDTO.getUsername());
                user.get().setPassword(userDTO.getPassword());
                user.get().setAddress(userDTO.getAddress());
                user.get().setEmail(userDTO.getEmail());
                user.get().setFirstname(userDTO.getFirstname());
                user.get().setLastname(userDTO.getLastname());
                user.get().setPhone(userDTO.getPhone());

                userRepository.save(user.get());
                return new UserRegistrationDTO(user.get());
            }
        } else {
            throw new UsernameNotFound(userDTO.getUsername());
        }
    }

    public UserRegistrationDTO getUser(Long id) throws UserNotFound {

        Optional<User> user = userRepository.findOneById(id);
        if (user.isPresent()) {
            return new UserRegistrationDTO(user.get());
        } else {
            throw new UserNotFound(id);
        }
    }

    public Set<FriendDTO> searchFriends(Long id, String friend) {

        Set<User> users = userRepository.searchFriends(id, friend);

        Set<FriendDTO> friends = new HashSet<>();

        for (User user :  users) {
            FriendDTO f = new FriendDTO(user);
            friends.add(f);
        }

        return friends;
    }

    public boolean changePassword(ChangePasswordDTO changePasswordDTO) throws UserNotFound, UserNotLoggedFirstTime {

        Optional<User> user = userRepository.findOneById(changePasswordDTO.getId());
        if (user.isPresent()) {
            if (!user.get().getLoggedFirstTime()) {
                user.get().setPassword(changePasswordDTO.getPassword());
                user.get().setLoggedFirstTime(true);
                userRepository.save(user.get());
                return true;
            }
            throw new UserNotLoggedFirstTime(changePasswordDTO.getId());
        } else {
            throw new UserNotFound(changePasswordDTO.getId());
        }
    }
}
