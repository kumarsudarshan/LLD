# Design Elevator

**simulating the scenario**

- A person is on a particular floor. Suppose Ground Floor. He wants to go to 5th Floor.
So he clicks on the elevator button with up direction. We will be calling this the ExternalRequest.
This Request will be having the direction and the floor on which the button has been pressed by the user i.e. source floor.
The elevator will check the available requests if any and then process this request depending on some priority.
The elevator reaches the source floor i.e. the 0th or the ground floor. The person enters the elevator.

- The person enters the elevator. The person then presses the 5th floor button in the elevator to indicate the elevator to go to 5th floor.
This will be the internal request. So the internal request will be having only the floor to which the person wants to go to i.e.
the destination floor. The elevator moves to the fifth floor. And the person then exits the elevator.

- If suppose when the elevator is moving from the ground floor to the fifth floor and it reaches the first floor.
At this moment suppose another person on the second floor want's to go in the UP direction.
Then the elevator will stop for this request and the person on second floor will enter the elevator.
Suppose he presses the 4th floor button. Then the elevator will first stop at 4th floor which was the destination of the person
which entered on the second floor. Later the elevator stops on the fifth floor and the person from the ground floor exits.

- If suppose when the elevator is moving from the ground floor to the fifth floor and it reaches the first floor.
At this moment suppose another person on the second floor want's to go in the DOWN direction.
Then the elevator will not stop for this request immediately. Elevator will first go to the fifth floor where the person
from the ground floor will exit. Elevator will then go to the second floor. The person will enter the elevator and press 0.
The elevator will then move to the zeroth floor.

![image](https://user-images.githubusercontent.com/8271393/129456922-e523c534-8d63-4248-aea2-8c221af7920d.png)
![image](https://user-images.githubusercontent.com/8271393/129456925-848536dd-067f-4040-97e8-6983c2eef09e.png)
