package com.project.project.model;

import javax.persistence.*;

@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private RegisteredUser friend;

    @Column(name = "confirmed")
    private boolean confirmed;

    @Column(name = "pending")
    private boolean pending;

    public Friendship() {

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

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }
}
