package com.project.project.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class RegisteredUser extends User {

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Friendship> friends;

    @OneToMany(mappedBy = "user",  fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FriendRequest> friendRequests;

    @OneToMany(mappedBy = "user",  fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FlightReservation> flightReservations;

    @OneToMany(mappedBy = "user",  fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FlightInvitation> flightInvitations;

    public RegisteredUser() {

    }

    public Set<FlightReservation> getFlightReservations() {
        return flightReservations;
    }

    public void setFlightReservations(Set<FlightReservation> flightReservations) {
        this.flightReservations = flightReservations;
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

    public Set<FlightInvitation> getFlightInvitations() {
        return flightInvitations;
    }

    public void setFlightInvitations(Set<FlightInvitation> flightInvitations) {
        this.flightInvitations = flightInvitations;
    }
}
