package com.project.project.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class RegisteredUser extends User {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Friendship> friends;

    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    private Set<FriendRequest> friendRequests;

    public RegisteredUser() {

    }

    public Set<Friendship> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friendship> friends) {
        this.friends = friends;
    }

    public Set<FriendRequest> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(Set<FriendRequest> friendRequests) {
        this.friendRequests = friendRequests;
    }
}
