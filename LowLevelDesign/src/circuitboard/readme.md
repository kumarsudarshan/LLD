## Design Circuit board system

Design a Circuit board system that can contain one or more switches in any specific arrangement 
as defined by User input (Assume inputs from command line, UI not required).

 #### Rules:

- A switch can be of type XOR, OR, AND, NOT.
- A switch can have one or more inputs. Each input can be 0 or 1.
- A switch will always result in one output which is 0 or 1.
- It is possible to arrange switches in series or parallel and there can be intermediate switches 
  such that inputs of switch is coming from output to other switches.
- As a system, the circuit should result in a single output.

#### Expectation:

- Data Models to support such a system.
- Algorithm to calculate the resultant output (0 or 1) of the Circuit board.
