package com.project.project.model;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.Set;

@Entity
public class RegisteredUser extends User {

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_friends", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "friends")
    private Set<RegisteredUser> friends;

    public RegisteredUser() {

    }

    public Set<RegisteredUser> getFriends() {
        return friends;
    }

    public void setFriends(Set<RegisteredUser> friends) {
        this.friends = friends;
    }
}
