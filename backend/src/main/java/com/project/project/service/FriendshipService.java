package com.project.project.service;

import com.project.project.dto.FriendDTO;
import com.project.project.dto.FriendshipDTO;
import com.project.project.exceptions.*;
import com.project.project.model.FriendRequest;
import com.project.project.model.Friendship;
import com.project.project.model.RegisteredUser;
import com.project.project.model.User;
import com.project.project.repository.FriendRequestRepository;
import com.project.project.repository.FriendshipRepository;
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

    @Autowired
    private FriendshipRepository friendshipRepository;


    public FriendDTO add(FriendshipDTO friendshipDTO) throws UserNotFound, AlreadyFriend, FriendshipWrongRole, FriendRequestAlreadySent {

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
                        r1.getFriends().add(new Friendship(r2,r1));
                        r2.getFriends().add(new Friendship(r1,r2));
                        objToRemove1 = request;
                        for (FriendRequest request2 : r2.getFriendRequests()) {
                            if (request2.getTo() == r1.getId())
                                objToRemove2 = request2;
                        }

                    } else if (request.getTo() == r2.getId()) {
                        throw new FriendRequestAlreadySent(r2.getUsername());
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
                fr.setUser(r1);
                fr.setTo(r2.getId());
                r1.getFriendRequests().add(fr);
                userRepository.save(r1);

                fr = new FriendRequest();
                fr.setUser(r2);
                fr.setFrom(r1.getId());
                r2.getFriendRequests().add(fr);
                userRepository.save(r2);

                return new FriendDTO(r2);
            } else {
                throw  new UserNotFound(friendshipDTO.getTo());
            }
        } else {
            throw new UserNotFound(friendshipDTO.getFrom());
        }
    }

    public FriendDTO accept(FriendshipDTO friendshipDTO) throws UserNotFound, FriendshipWrongRole {
        Optional<User> user = userRepository.findOneById(friendshipDTO.getFrom());
        Optional<User> user2 = userRepository.findOneById(friendshipDTO.getTo());
        if (user.isPresent()) {
            if (user2.isPresent()) {
                if (!user.get().getRole().getRole().equals("User")) {
                    throw new FriendshipWrongRole(user.get().getRole().getRole());
                }
                else if (!user2.get().getRole().getRole().equals("User")) {
                    throw new FriendshipWrongRole(user2.get().getRole().getRole());
                }

                RegisteredUser r1 = (RegisteredUser) user.get();
                RegisteredUser r2 = (RegisteredUser) user2.get();



                r1.getFriends().add(new Friendship(r2,r1));
                r2.getFriends().add(new Friendship(r1,r2));

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

    public boolean checkRequest(Long from, Long to) throws UserNotFound, FriendshipWrongRole {
        Optional<User> user = userRepository.findOneById(from);
        if (user.isPresent()) {
            if (!user.get().getRole().getRole().equals("User")) {
                throw new FriendshipWrongRole(user.get().getRole().getRole());
            }
            RegisteredUser registeredUser = (RegisteredUser) user.get();

            for (FriendRequest request: registeredUser.getFriendRequests()) {
                if (request.getTo() == to) {
                    return true;
                }
            }
            return false;
        } else {
            throw new UserNotFound(from);
        }
    }

    public FriendDTO cancel(FriendshipDTO friendshipDTO) throws UserNotFound, FriendRequestDoesntExist, FriendshipWrongRole {
        Optional<User> user = userRepository.findOneById(friendshipDTO.getFrom());
        Optional<User> user2 = userRepository.findOneById(friendshipDTO.getTo());
        if (user.isPresent()) {
            if (user2.isPresent()) {
                if (!user.get().getRole().getRole().equals("User")) {
                    throw new FriendshipWrongRole(user.get().getRole().getRole());
                }
                else if (!user2.get().getRole().getRole().equals("User")) {
                    throw new FriendshipWrongRole(user2.get().getRole().getRole());
                }

                RegisteredUser r1 = (RegisteredUser) user.get();
                RegisteredUser r2 = (RegisteredUser) user2.get();

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
                if (objToRemove1 != null && objToRemove2 != null) {
                    r1.getFriendRequests().remove(objToRemove1);
                    r2.getFriendRequests().remove(objToRemove2);

                    friendRequestRepository.delete(objToRemove1);
                    friendRequestRepository.delete(objToRemove2);
                    userRepository.save(r1);
                    userRepository.save(r2);
                    return new FriendDTO(user.get());

                } else {
                    throw new  FriendRequestDoesntExist(friendshipDTO.getTo());
                }

            } else {
                throw new UserNotFound(friendshipDTO.getFrom());
            }
        } else {
            throw new UserNotFound(friendshipDTO.getFrom());
        }
    }

    public FriendDTO remove(FriendshipDTO friendshipDTO) throws FriendRequestDoesntExist, UserNotFound, FriendshipWrongRole {
        Optional<User> user = userRepository.findOneById(friendshipDTO.getFrom());
        Optional<User> user2 = userRepository.findOneById(friendshipDTO.getTo());
        if (user.isPresent()) {
            if (user2.isPresent()) {
                if (!user.get().getRole().getRole().equals("User")) {
                    throw new FriendshipWrongRole(user.get().getRole().getRole());
                }
                else if (!user2.get().getRole().getRole().equals("User")) {
                    throw new FriendshipWrongRole(user2.get().getRole().getRole());
                }
                RegisteredUser r1 = (RegisteredUser) user.get();
                RegisteredUser r2 = (RegisteredUser) user2.get();

                Friendship f1 = null;
                Friendship f2 = null;

                for(Friendship friendship : r1.getFriends()) {
                    if (friendship.getFriend().getId() == r2.getId())
                        f1 = friendship;
                }

                for (Friendship friendship : r2.getFriends()) {
                    if (friendship.getFriend().getId() == r1.getId())
                        f2 = friendship;
                }

                if (f1 != null && f2 != null) {

                    r1.getFriends().remove(f1);
                    r2.getFriends().remove(f2);

                    friendshipRepository.delete(f1);
                    friendshipRepository.delete(f2);

                    userRepository.save(r1);
                    userRepository.save(r2);


                    return new FriendDTO(r2);

                } else {
                    throw new FriendRequestDoesntExist(friendshipDTO.getTo());
                }

            } else {
                throw new UserNotFound(friendshipDTO.getFrom());
            }
        } else {
            throw new UserNotFound(friendshipDTO.getFrom());
        }

    }

    public boolean checkFriend(Long from, Long to) throws FriendshipWrongRole, UserNotFound {
        Optional<User> user = userRepository.findOneById(from);
        if (user.isPresent()) {
            if (!user.get().getRole().getRole().equals("User")) {
                throw new FriendshipWrongRole(user.get().getRole().getRole());
            }
            RegisteredUser registeredUser = (RegisteredUser) user.get();

            for (Friendship friendship: registeredUser.getFriends()) {
                if (friendship.getFriend().getId() == to) {
                    return true;
                }
            }
            return false;
        } else {
            throw new UserNotFound(from);
        }
    }
}
