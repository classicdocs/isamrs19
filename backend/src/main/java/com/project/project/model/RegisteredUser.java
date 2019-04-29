package com.project.project.model;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.Set;

@Entity
public class RegisteredUser extends User {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Friendship> friends;

    public RegisteredUser() {

    }

    public Set<Friendship> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friendship> friends) {
        this.friends = friends;
    }
}
