package com.example.lab7.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Friendship extends Entity<Long>{
    private long idUser1;
    private long idUser2;
    private LocalDateTime friendsFrom;

    public Friendship(long idUser1, long idUser2, LocalDateTime friendsFrom) {
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.friendsFrom = friendsFrom;
    }

    public long getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(long idUser1) {
        this.idUser1 = idUser1;
    }

    public long getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(long idUser2) {
        this.idUser2 = idUser2;
    }

    public LocalDateTime getFriendsFrom() {
        return friendsFrom;
    }

    public void setFriendsFrom(LocalDateTime friendsFrom) {
        this.friendsFrom = friendsFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendship that = (Friendship) o;
        return idUser1 == that.idUser1 && idUser2 == that.idUser2 && friendsFrom.equals(that.friendsFrom) ||
                idUser1 == that.idUser2 && idUser2 == that.idUser1 && friendsFrom.equals(that.friendsFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser1, idUser2, friendsFrom);
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "idUser1=" + idUser1 +
                ", idUser2=" + idUser2 +
                ", friendsFrom=" + friendsFrom +
                '}';
    }
}