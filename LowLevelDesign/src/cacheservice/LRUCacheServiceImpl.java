package cacheservice;

public class LRUCacheServiceImpl<K,V> implements CacheService<K, V>{
	
	LRUCache<K, V> lruCache = null;
	
	public LRUCacheServiceImpl(LRUCache<K, V> lruCache) {
		this.lruCache = lruCache;
	}

	@Override
	public void put(K key, V value) {
		lruCache.put(key, value);
	}

	@Override
	public V get(K key) {
		return lruCache.get(key);
	}

}
