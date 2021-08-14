package cache.test;


import cache.policies.LFUEvictionPolicy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class LFUEvictionPolicyTest {
    private LFUEvictionPolicy<Integer> lfuEvictionPolicy;

    @Before
    public void setUp() {
        lfuEvictionPolicy = new LFUEvictionPolicy<>();
    }

    @Test
    public void testNoKeyToEvictInitially() {
        assertNull(lfuEvictionPolicy.evictKey());
    }

    @Test
    public void testKeysAreEvictedInTheOrderInWhichTheyAreAccessedAccess() {
        lfuEvictionPolicy.keyAccessed(1);
        lfuEvictionPolicy.keyAccessed(2);
        lfuEvictionPolicy.keyAccessed(3);
        lfuEvictionPolicy.keyAccessed(1);
        lfuEvictionPolicy.keyAccessed(1);
        lfuEvictionPolicy.keyAccessed(3);
        Assert.assertEquals(2l, (long) lfuEvictionPolicy.evictKey());
        Assert.assertEquals(3l, (long) lfuEvictionPolicy.evictKey());
        Assert.assertEquals(1l, (long) lfuEvictionPolicy.evictKey());
    }
}