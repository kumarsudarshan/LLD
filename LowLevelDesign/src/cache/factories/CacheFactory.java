package cache.factories;

import cache.Cache;
import cache.policies.LFUEvictionPolicy;
import cache.policies.LRUEvictionPolicy;
import cache.storage.HashMapBasedStorage;

public class CacheFactory<K, V> {

    public Cache<K, V> lruCache(final int capacity) {
        return new Cache<K, V>(new LRUEvictionPolicy<K>(), new HashMapBasedStorage<K, V>(capacity));
    }

    public Cache<K, V> lfuCache(final int capacity) {
        return new Cache<K, V>(new LFUEvictionPolicy<K>(), new HashMapBasedStorage<K, V>(capacity));
    }
}
