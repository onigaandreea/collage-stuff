import java.util.List;

public class Producer implements Runnable {
    private final MyQueue queue;
    private final List <ParticipantNode> participantsList;
    private final LockedIds lockedIds;

    public Producer(MyQueue queue, List<ParticipantNode> list, LockedIds lockedIds) {
        this.queue = queue;
        this.participantsList = list;
        this.lockedIds = lockedIds;
    }

    @Override
    public void run() {
        for (var p : this.participantsList) {
            lockedIds.add(p.id);
            queue.put(p);
        }
        queue.myNotify();
    }
}
