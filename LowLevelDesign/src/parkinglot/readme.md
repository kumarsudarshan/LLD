
# Design Parking Lot

A parking lot or car park is a dedicated cleared area that is intended for parking vehicles. Shopping malls, sports stadiums, megachurches, and similar venues often feature parking lots over large areas.

#### Requirements

1.	The parking lot should have multiple floors where customers can park their cars.
2.	The parking lot should have multiple entry and exit points.
3.	Customers can collect a parking ticket from the entry points and can pay the parking fee at the exit points on their way out.
4.	Customers can pay the tickets at the automated exit panel or to the parking attendant.
5.	Customers can pay via both cash and credit cards.
6.	Customers should also be able to pay the parking fee at the customer’s info portal on each floor. If the customer has paid at the info portal, they don’t have to pay at the exit.
7.	The system should not allow more vehicles than the maximum capacity of the parking lot. If the parking is full, the system should be able to show a message at the entrance panel and on the parking display board on the ground floor.
8.	Each parking floor will have many parking spots. The system should support multiple types of parking spots such as Compact, Large, Handicapped, Motorcycle, etc.
9.	The Parking lot should have some parking spots specified for electric cars. These spots should have an electric panel through which customers can pay and charge their vehicles.
10.	The system should support parking for different types of vehicles like car, truck, van, motorcycle, etc.
11.	Each parking floor should have a display board showing any free parking spot for each spot type.
12.	The system should support a per-hour parking fee model. For example, customers have to pay Rs. 20 for the first hour, Rs. 10 for the second and third hours, and Rs.15 for all the remaining hours.


![image](https://user-images.githubusercontent.com/8271393/126473247-4f8c7fee-32b3-432a-8c02-f7962681d100.png)
![image](https://user-images.githubusercontent.com/8271393/126473268-178ed7d7-f388-4c6e-af13-9d5e7ee94221.png)

![parking lot](https://user-images.githubusercontent.com/8271393/126515079-40af7417-4326-4876-9bff-6dec275681e8.png)


#### Question 1: How to print the vehicle number and vehicle info on the ticket?
Answer: When we get ParkingTicket object, in EntrancePanel class we can write print method.

#### Question 2: How to assign a parking spot to vehicle? Do you consider the closet parking spot to the entrance? what if someone parked at wrong place and went away?
