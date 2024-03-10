package com.example.lab7.domain;

import java.time.LocalTime;
import java.util.Objects;

public class Message extends Entity<Long>{
    private long from;
    private long to;
    private LocalTime whenWasSended;
    private String text;

    public Message(long from, long to, LocalTime whenWasSended, String text) {
        this.from = from;
        this.to = to;
        this.whenWasSended = whenWasSended;
        this.text = text;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public LocalTime getWhenWasSended() {
        return whenWasSended;
    }

    public void setWhenWasSended(LocalTime whenWasSended) {
        this.whenWasSended = whenWasSended;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return from == message.from && to == message.to && Objects.equals(whenWasSended, message.whenWasSended) && Objects.equals(text,message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, whenWasSended);
    }

    @Override
    public String toString() {
        return "Message{" +
                "from=" + from +
                ", to=" + to +
                ", whenWasSended='" + whenWasSended +
                ", message: " + text + '\'' +
                '}';
    }
}
