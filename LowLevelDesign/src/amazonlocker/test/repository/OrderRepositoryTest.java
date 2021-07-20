package amazonlocker.test.repository;

import amazonlocker.repository.OrderRepository;
import amazonlocker.test.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderRepositoryTest {
    @Before
    public void setup() {
        OrderRepository.orderMap.put("o1", TestData.getPhoneOrder());
        OrderRepository.orderMap.put("o2", TestData.getHeadSetOrder());
    }

    @Test
    public void shouldGetOrderById() {
        Assert.assertNotNull(OrderRepository.getOrder("o1"));
        Assert.assertNotNull(OrderRepository.getOrder("o2"));
    }
}
