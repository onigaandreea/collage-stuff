import java.util.*;

public class MyList {
    private final ParticipantNode santinel = new ParticipantNode(-1, Integer.MAX_VALUE, -1);
    private final SyncronizedSet disqualified = new SyncronizedSet();
    private void add(ParticipantNode p) {
        ParticipantNode previous = santinel;
        ParticipantNode current = santinel.getNext();
        previous.mutex.lock();
        while (current != null) {
            current.mutex.lock();
            if (previous.compareTo(p) < 0 && current.compareTo(p) > 0) {
                p.setNext(current);
                previous.setNext(p);
                previous.mutex.unlock();
                current.mutex.unlock();
                return;
            }
            ParticipantNode aux = previous;
            previous = current;
            current = previous.getNext();
            aux.mutex.unlock();
        }
        previous.setNext(p);
        previous.mutex.unlock();
    }

    private void remove(ParticipantNode p) {
        ParticipantNode previous = santinel;
        ParticipantNode current = santinel.getNext();
        previous.mutex.lock();
        while (current != null) {
            current.mutex.lock();
            if (Objects.equals(current.id, p.id)) {
                previous.setNext(current.getNext());
                previous.mutex.unlock();
                current.mutex.unlock();
                return;
            }
            ParticipantNode aux = previous;
            previous = current;
            current = previous.getNext();
            aux.mutex.unlock();
        }
        previous.mutex.unlock();
    }

    private ParticipantNode getParticipant(ParticipantNode p) {
        ParticipantNode previous = santinel;
        ParticipantNode current = santinel.getNext();
        previous.mutex.lock();
        while (current != null) {
            current.mutex.lock();
            if (Objects.equals(current.id, p.id)) {
                previous.mutex.unlock();
                current.mutex.unlock();
                return current;
            }
            ParticipantNode aux = previous;
            previous = current;
            current = previous.getNext();
            aux.mutex.unlock();
        }
        previous.mutex.unlock();
        return null;
    }

    public synchronized void update(ParticipantNode n) {
        if (disqualified.contains(n.id)) {
            return;
        }
        ParticipantNode actualNode = this.getParticipant(n);
        if (actualNode == null) {
            if (n.score == -1) {
                disqualified.add(n.id);
            } else {
                this.add(n);
            }
        } else {
            if (n.score == -1) {
                this.remove(n);
                disqualified.add(n.id);
            } else {
                this.remove(n);
                n.score += actualNode.score;
                this.add(n);
            }
        }
    }

    public List<ParticipantNode> getList() {
        List<ParticipantNode> theList = new ArrayList<>();
        ParticipantNode previous = santinel;
        ParticipantNode current = santinel.getNext();
        previous.mutex.lock();
        while (current != null) {
            current.mutex.lock();
            theList.add(current);
            ParticipantNode aux = previous;
            previous = current;
            current = previous.getNext();
            aux.mutex.unlock();
        }
        previous.mutex.unlock();
        return theList;
    }
    public static class SyncronizedSet {
        private Set<Integer> disqualified = new HashSet<>();
        public synchronized boolean contains(Integer id) {
            return disqualified.contains(id);
        }

        public synchronized boolean add(Integer id) {
            return disqualified.add(id);
        }
    }
}