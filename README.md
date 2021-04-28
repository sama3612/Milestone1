# Dream Team
### CSCI4448-5448 Milestone1

## Members
Sai Maddhi, Andrew Wu, Noorain Baig, Beka Admasu

Under the documents directory, the coding standard, team contract, and CRC cards can all be found

#### Refactoring & Implementation (Code Complete, Second Edition):
 - Increasing modularity
    - Cleaner routine by separating getAttacked() and getAttackedBelow into different classes. 
 - Shortening routine
    - getAttacked() was separated into different functions for each type of weapon to reduce the amount of work one function was doing.
    - This also increased modularity, as we have more well-defined, well-named routines that only do one thing.
 - Increasing cohesion
    - In order to add the ability for any ship to be submerged, getAttackedBelow was moved to each Ship subclass (Submarine, Battleship, Minesweeper, Destroyer), and then moved to the Ship class so all ships could have that functionality without a duplicated function in each subclass. 
    - We also increased cohesion by having subclasses for each kind of ship instead of having the Ship class responsible for everything.
 - Convert long routine to class
    - When we were initially creating the UI, we had all of the code to interact with the user and gather input inside of the Game class itself. This resulted in a super long and messy class.
    - To clean this up, we created a UI class that handled the interaction with the user and merely utilized the Scanner object that was created once in the Game class.
 - Combine similar routines by parameterizing them
    - This is what we did when we were refactoring the getAttacked function inside of the Map class. We initially had all of the code inside the getAttacked function for every single weapon and the code was close to 200 lines long. How we fixed this was by parameterizing the different types of attacks into different functions and then calling those functions whenever the user chose that specific attack.
 - Pass specific fields rather than a whole object
    - When we were initially utilizing the Coordinate class that we created, we always created a new Coordinate object to specify a certain location on the board. To change this,
    - we refactored the code so that we are only passing in a row and column variables and then locating the correct Coordinate based on the row and column. 
 - Long Parameter List
    - We adjoined the size of a ship to its name and vice versa so that only needed to pass in one parameter and get two values. 
 - Uncommunicative/ Inconsistent Names
    - Renamed the tests to follow camelcase with descriptive titles.
 - Dead Code
    - Deleted all commented-out code and unnecessary comments.
###Refactoring of the getAttacked functions discussed.
![Before Refactoring](/before_refactoring.jpeg)
![After Refactoring](/after_refactoring.png)

-We also have more screenshots of before and afters on the milestone 5 wiki.

#### Design Patterns
As requested by Vasu, here are some of the design patterns that we incorporated during our development of the Battleship Game.
 - Abstract Factory Pattern for creating different types of ships
    - When we are creating a ship that is specified by the user, we create a new ship that is a certain type of ship that inherits from the parent class Ship.
 - Strategy Pattern for choosing different weapons
    - We used this pattern when creating the getAttacked functions. Based on whatever weapon the user chooses, we attack a certain coordinate based on that attack
 - Observer Pattern for sinking ships
    - Based on a certain attack, we use an observer pattern to check every single ship whenever a block is sunk. By checking every ship, we are using the observer pattern to notify all observers and subscribing/unsubscribing whenever we create or sink a ship respectively. 
 - Observer pattern for updating player maps, player attacks, and points updates
    - Each player is updated on the other player’s moves
 - Command Pattern for Attacks
    - As discussed in the Milestone meetings, when we used our getAttacked method, we passed it through multiple classes starting from the Player class where the attack was initiated and passed
 - Singleton Pattern for UI
    - In order to avoid recreating the Scanner object every single time, we only created the Scanner object once and used that same object whenever called on the UI class to gather some user input. This resulted in a design pattern that was similar to a Singleton Pattern
 it through to each of the ships to see if they got attacked

