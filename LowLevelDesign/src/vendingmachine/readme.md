# Design Vending Machine

#### Requirements

- Add items to the vending machine in fixed number of slots
- Payment using card or cash
- Select item to dispense


**Think of all the Real objects :**

Customer
Product/Item (Product/Item Type (softdrink, cold coffee, cold tea))
Payment (transaction)
Cash or Card (Credit/Debit Card)
Buttom,
Item Code

**Think about work flow :**

Customer select an item (by entering the code A5)
Customer is presented by item price
Customer chooses to pay or cancels
Customer can add Card and do payment
Payment goes through (Item is despense) else add Card info again
Alernatively, Customer adds bills cal and return the change and despense the item
Design patttern

**Command/Stragegy pattern**

Singleton for Vending machine instance
Fascade pattern for charing for multiple item