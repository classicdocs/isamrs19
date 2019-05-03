package com.project.project.model;

import javax.persistence.*;

@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private RegisteredUser friend;

    public Friendship() {

    }

    public Friendship(RegisteredUser r) {
        this.friend = r;
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
}
