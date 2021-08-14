package cache.test;

import cache.policies.LRUEvictionPolicy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LRUEvictionPolicyTest {
    private LRUEvictionPolicy<Integer> lruEvictionPolicy;

    @Before
    public void setUp() {
        lruEvictionPolicy = new LRUEvictionPolicy<>();
    }

    @Test
    public void testNoKeyToEvictInitially() {
        Assert.assertNull(lruEvictionPolicy.evictKey());
    }

    @Test
    public void testKeysAreEvictedInTheOrderInWhichTheyAreAccessedAccess() {
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(3);
        lruEvictionPolicy.keyAccessed(4);
        Assert.assertEquals(1l, (long) lruEvictionPolicy.evictKey());
        Assert.assertEquals(2l, (long) lruEvictionPolicy.evictKey());
        Assert.assertEquals(3l, (long) lruEvictionPolicy.evictKey());
        Assert.assertEquals(4l, (long) lruEvictionPolicy.evictKey());
    }

    @Test
    public void testReaccesingKeyPreventsItFromEviction() {
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(3);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(4);
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(5);
        Assert.assertEquals(3l, (long) lruEvictionPolicy.evictKey());
        Assert.assertEquals(2l, (long) lruEvictionPolicy.evictKey());
        Assert.assertEquals(4l, (long) lruEvictionPolicy.evictKey());
        Assert.assertEquals(1l, (long) lruEvictionPolicy.evictKey());
        Assert.assertEquals(5l, (long) lruEvictionPolicy.evictKey());
    }
}