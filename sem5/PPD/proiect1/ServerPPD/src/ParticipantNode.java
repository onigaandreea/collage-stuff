import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParticipantNode implements Comparable<ParticipantNode>, Serializable {
    public int id;
    public int score;
    public int country;
    private ParticipantNode next = null;
    Lock mutex = new ReentrantLock();

    public ParticipantNode(int id, int score, int country) {
        this.id = id;
        this.score = score;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public ParticipantNode getNext() {
        return next;
    }

    public void setNext(ParticipantNode next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantNode node = (ParticipantNode) o;
        return Objects.equals(id, node.id);
    }

    @Override
    public int compareTo(ParticipantNode o) {
        if (!Objects.equals(this.score, o.score)) {
            return o.score - this.score;
        }
        else {
            return this.id - o.id;
        }
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", score=" + score +
                ", country=" + country +
                '}';
    }
}