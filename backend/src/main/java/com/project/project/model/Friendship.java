package com.project.project.model;

import javax.persistence.*;

@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private RegisteredUser friend;

    @ManyToOne
    private RegisteredUser user;

    public Friendship() {

    }

    public Friendship(RegisteredUser r, RegisteredUser user) {
        this.friend = r;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegisteredUser getFriend() {
        return friend;
    }

    public void setFriend(RegisteredUser friend) {
        this.friend = friend;
    }

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }
}
