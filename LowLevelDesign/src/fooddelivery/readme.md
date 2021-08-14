# Food delivery app - Zomato, Swiggy

## Requirements
1. Restaurant can register themselves.
2. User can create, update, delete, get their profiles.
3. User can search for the restaurant using restaurant name, city name.
4. Restaurant can add, update foodmenu.
5. User can see the foodmenu. User can get the food items based on Meal type or Cuisine type.
6. User can add/remove items to/from the cart. User can get all the items of the cart.
7. User can place or cancel the order. User can get all the orders ordered by him/her.
8. User can apply the coupons. User can get the detailed bill containing tax details.
9. User can made a payment using different modes of payment - credit card, wallet, etc.
10. Delivery boy can get all the deliveries made by him using his Id.
11. User can get the order status anytime. Success, Out for Delivery, Delivered, etc.

### Foodkart:

#### Description:

Flipkart is starting a new online food ordering service.
In this Service, users can order food from a restaurant which is serviceable in their area and the restaurant will deliver it.

#### Features:

- Restaurants can only serve one specialized dish.
- Restaurants can serve in multiple areas.
- At a time, users can order from one restaurant, and the quantity of food can be more than one.
- Users should be able to rate any restaurant with or without comment.
- Rating of a restaurant is the average rating given by all customers.

Sample Test Case:
All the inputs here are just indicating the high level inputs that function should accept. You are free to model entities as per your choice.

register_user(“Pralove”, “M”, “phoneNumber-1”, “HSR”)
register_user(“Nitesh”, “M”, “phoneNumber-2”, “BTM”)
register_user(“Vatsal”, “M”,  “phoneNumber-3”, “BTM”)

login_user(“phoneNumber-1”)

register_restaurant(“Food Court-1”, “BTM/HSR”, “NI Thali”, 100, 5)
NOTE: we will have 2 delimiters in input : ',' to specify separate fields & '/' to identify different pincodes.
register_restaurant(“Food Court-2”, “BTM”, “Burger”, 120, 3)

login_user(“phoneNumber-2”)
register_restaurant(“Food Court-3”, “HSR”, “SI Thali”, 150, 1)
login_user(“phoneNumber-3”)
show_restaurant(“price”)

Output :  Food Court-2, Burger
	   Food Court-1, NI Thali

place_order(“Food Court-1”, 2)
Output: Order Placed Successfully.

place_order(““Food Court-2”, 7)
Output : Cannot place order

create_review(“Food Court-2”, 3, “Good Food”)
create_review(“Food Court-1”, 5, “Nice Food”)

show_restaurant(“rating”)
Output :  Food Court-1, NI Thali
	   Food Court-2, Burger

login_user(“phoneNumber-1”)
update_quantity(“Food Court-2”, 5)
Output: Food Court-2, BTM, Burger - 8

update_location(“Food Court-2”, “BTM/HSR”)
Output: Food Court-2, “BTM/HSR”, Burger - 8





