import java.util.concurrent.locks.Lock;

public class Consumer extends Thread {
    private final MyQueue queue;
    private final MyList participantsList;
    private final LockedIds lockedIds;

    public Consumer(MyQueue queue, MyList participantsList, LockedIds lockedIds) {
        this.queue = queue;
        this.participantsList = participantsList;
        this.lockedIds = lockedIds;
    }

    @Override
    public void run() {
        while (true) {
            ParticipantNode p = queue.get();
            if (p != null) {
                Lock mutex = lockedIds.get(p.id);
                mutex.lock();
                participantsList.update(p);
                mutex.unlock();
            }
            else
                break;
        }
    }
}