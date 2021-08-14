package cache.policies;

public class Counter<K> {
    private K key;
    private int count;

    public Counter(K key, int count) {
        this.key = key;
        this.count = count;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
