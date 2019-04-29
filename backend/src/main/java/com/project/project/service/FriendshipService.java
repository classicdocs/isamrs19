package com.project.project.service;

import com.project.project.dto.FriendshipDTO;
import com.project.project.exceptions.AlreadyFriend;
import com.project.project.exceptions.FriendshipWrongRole;
import com.project.project.exceptions.UserNotFound;
import com.project.project.model.Friendship;
import com.project.project.model.RegisteredUser;
import com.project.project.model.User;
import com.project.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendshipService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public FriendshipDTO add(FriendshipDTO friendshipDTO) throws UserNotFound, AlreadyFriend, FriendshipWrongRole {

        Optional<User> user = userRepository.findOneById(friendshipDTO.getFrom());
        if (user.isPresent()) {
            if (!user.get().getRole().getRole().equals("User")) {
                throw new FriendshipWrongRole(user.get().getRole().getRole());
            }
            Optional<User> user2 = userRepository.findOneById(friendshipDTO.getTo());
            if (user2.isPresent()) {
                if (!user2.get().getRole().getRole().equals("User")) {
                    throw new FriendshipWrongRole(user2.get().getRole().getRole());
                }
                RegisteredUser r1 = (RegisteredUser) user.get();
                RegisteredUser r2 = (RegisteredUser) user2.get();
                for (Friendship friendship : r1.getFriends()) {
                    if (friendship.getFriend().equals(r2))
                        throw new AlreadyFriend(r2.getUsername());
                }

                Friendship f = new Friendship();
                f.setFriend(r2);
                f.setPending(true);
                f.setConfirmed(false);
                r1.getFriends().add(f);
                userRepository.save(r1);

                // poslati mail
                String subject = "Friend request";
                String message = "";
                message += "<html><body>";
                message += "<head><p> Email of user: " + r2.getUsername() + "</p></head>";
                message += "<p> You have new friend request from " + r1.getUsername() + " </p>";
                message += "<a href='http://localhost:8080/users/" + r1.getId() + "/profil'>Click on this link to see his profile</a><br>";
                message += "<a href='http://localhost:8088/api/hello'>Click on this link to accept friend request</a>";
                message += "</body></html>";
                emailService.prepareAndSend(subject, message);
                return friendshipDTO;
            } else {
                throw  new UserNotFound(friendshipDTO.getTo());
            }
        } else {
            throw new UserNotFound(friendshipDTO.getFrom());
        }



    }
}
