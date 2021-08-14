package vendingmachine;

import java.time.LocalDateTime;

interface LoggingService {
    void logPurchase(ItemLocation itemLocation, LocalDateTime purchaseDateTime);
}
