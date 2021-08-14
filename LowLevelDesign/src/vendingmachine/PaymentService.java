package vendingmachine;

import java.math.BigDecimal;

interface PaymentService {
    BigDecimal makePayment(Payment payment);
}
