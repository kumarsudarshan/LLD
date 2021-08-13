package cacheservice;

public interface CacheService<K, V> {

    /*
     * cache service supports two operations get and put
     */

    public void put(K key, V value);

    public V get(K key);

}
