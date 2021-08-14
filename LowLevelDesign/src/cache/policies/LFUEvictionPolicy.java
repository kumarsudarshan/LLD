package cache.policies;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Eviction policy based on LFU - least frequently used algorithm.
 *
 * @param <K> Key type.
 */
public class LFUEvictionPolicy<K> implements EvictionPolicy<K> {

    private PriorityQueue<Counter> leastFrequentlyUsed;
    private Map<K, Counter> mapper;

    public LFUEvictionPolicy() {
        this.leastFrequentlyUsed = new PriorityQueue<Counter>((c1, c2) -> c1.getCount() - c2.getCount());
        this.mapper = new HashMap<>();
    }

    @Override
    public void keyAccessed(K key) {
        if (this.mapper.containsKey(key)) {
            leastFrequentlyUsed.remove(this.mapper.get(key));
            this.mapper.get(key).setCount(this.mapper.get(key).getCount() + 1);
        } else {
            mapper.put(key, new Counter(key, 1));
        }
        leastFrequentlyUsed.add(this.mapper.get(key));
    }

    @Override
    public K evictKey() {
        Counter counter = leastFrequentlyUsed.poll();
        mapper.remove(counter.getKey());
        return (K) counter.getKey();
    }
}

