package cacheservice;

import java.util.LinkedHashMap;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int capacity = 0;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    void putKeyValue(K key, V value) {
        super.put(key, value);
        this.capacity++;
    }


    V getValue(K key) {
        return super.get(key);
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return this.capacity < super.size();
    }

}
