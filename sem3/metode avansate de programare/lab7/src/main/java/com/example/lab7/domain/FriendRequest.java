package com.example.lab7.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class FriendRequest extends Entity<Long>{
    private long idUser1;
    private long idUser2;
    private LocalDateTime send;
    private String status;

    public FriendRequest(long idUser1, long idUser2, LocalDateTime send) {
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.send = send;
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

    public LocalDateTime getSendRequest() {
        return send;
    }

    public void setSendRequest(LocalDateTime send) {
        this.send = send;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendRequest that = (FriendRequest) o;
        return idUser1 == that.idUser1 && idUser2 == that.idUser2 && send.equals(that.send);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser1, idUser2, send);
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "idUser1=" + idUser1 +
                ", idUser2=" + idUser2 +
                ", friendsFrom=" + send +
                '}';
    }
}
