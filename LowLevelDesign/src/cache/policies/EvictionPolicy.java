package cache.policies;

/**
 * Interface for defining eviction policies.
 *
 * @param <K> Type of key.
 */
public interface EvictionPolicy<K> {

    void keyAccessed(K key);

    /**
     * Evict key from eviction policy and return it.
     */
    K evictKey();
}
