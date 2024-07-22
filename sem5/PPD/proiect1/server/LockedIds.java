import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockedIds {
    private final Map<Integer, Lock> locks = new HashMap<>();

    public synchronized void add(Integer id) {
        if (!locks.containsKey(id)) {
            locks.put(id, new ReentrantLock());
        }
    }
    public synchronized Lock get(Integer id) {
        return locks.get(id);
    }

}
