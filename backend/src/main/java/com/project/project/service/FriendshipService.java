package com.project.project.service;

import com.project.project.dto.FriendDTO;
import com.project.project.dto.FriendshipDTO;
import com.project.project.exceptions.AlreadyFriend;
import com.project.project.exceptions.FriendshipWrongRole;
import com.project.project.exceptions.UserNotFound;
import com.project.project.model.FriendRequest;
import com.project.project.model.Friendship;
import com.project.project.model.RegisteredUser;
import com.project.project.model.User;
import com.project.project.repository.FriendRequestRepository;
import com.project.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendshipService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRequestRepository friendRequestRepository;


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
                for (Friendship friend : r1.getFriends()) {
                    if (friend.getFriend() == r2)
                        throw new AlreadyFriend(r2.getUsername());
                }
                FriendRequest objToRemove1 = null;
                FriendRequest objToRemove2 = null;
                for (FriendRequest request : r1.getFriendRequests()) {
                    if (request.getFrom() == r2.getId())  {
                        r1.getFriends().add(new Friendship(r2));
                        r2.getFriends().add(new Friendship(r1));
                        objToRemove1 = request;
                        for (FriendRequest request2 : r2.getFriendRequests()) {
                            if (request2.getTo() == r1.getId())
                                objToRemove2 = request2;
                        }

                    }
                }
                if (objToRemove1 != null) {
                    r1.getFriendRequests().remove(objToRemove1);
                    r2.getFriendRequests().remove(objToRemove2);

                    friendRequestRepository.delete(objToRemove1);
                    friendRequestRepository.delete(objToRemove2);
                    userRepository.save(r1);
                    userRepository.save(r2);
                    return null;
                }
                FriendRequest fr = new FriendRequest();
                fr.setTo(r2.getId());
                r1.getFriendRequests().add(fr);
                userRepository.save(r1);

                fr = new FriendRequest();
                fr.setFrom(r1.getId());
                r2.getFriendRequests().add(fr);
                userRepository.save(r2);

                return friendshipDTO;
            } else {
                throw  new UserNotFound(friendshipDTO.getTo());
            }
        } else {
            throw new UserNotFound(friendshipDTO.getFrom());
        }
    }

    public FriendDTO accept(FriendshipDTO friendshipDTO) throws UserNotFound {
        Optional<User> user = userRepository.findOneById(friendshipDTO.getFrom());
        Optional<User> user2 = userRepository.findOneById(friendshipDTO.getTo());
        if (user.isPresent()) {
            if (user2.isPresent()) {
                RegisteredUser r1 = (RegisteredUser) user.get();
                RegisteredUser r2 = (RegisteredUser) user2.get();

                r1.getFriends().add(new Friendship(r2));
                r2.getFriends().add(new Friendship(r1));

                FriendRequest objToRemove1 = null;
                FriendRequest objToRemove2 = null;
                for (FriendRequest request : r1.getFriendRequests()) {
                    if (request.getTo() == r2.getId())  {

                        objToRemove1 = request;
                        for (FriendRequest request2 : r2.getFriendRequests()) {
                            if (request2.getFrom() == r1.getId())
                                objToRemove2 = request2;
                        }

                    }
                }
                if (objToRemove1 != null) {
                    r1.getFriendRequests().remove(objToRemove1);
                    r2.getFriendRequests().remove(objToRemove2);

                    friendRequestRepository.delete(objToRemove1);
                    friendRequestRepository.delete(objToRemove2);
                    userRepository.save(r1);
                    userRepository.save(r2);
                }
                return new FriendDTO(user.get());

            } else {
                throw new UserNotFound(friendshipDTO.getFrom());
            }
        } else {
            throw new UserNotFound(friendshipDTO.getFrom());
        }
    }
}
