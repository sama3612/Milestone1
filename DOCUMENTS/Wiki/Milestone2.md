# Wiki Update (2/11/21 2:00 PM scheduled meeting)
Meeting minutes are located after the Revised Project Risks section.


## User Stories
As a player, I can place my ships, shoot, view both players’ boards, and know which ships are alive or dead. 

As a developer, I want my code to be readable by adding frequent comments and the progress to be transparent by using git often and properly.

As a developer, I want to utilize TDD strategies to code safely while covering all of my bases.

As a developer, I want to communicate effectively while pair programming with my partner to ensure that we are working together to code well.


## Planning Game and Time Estimates:
### Using TDD, we will first implement the Driver, Map, and UI class. Once we get the driver class to start the game and switch between users, we can take input from the user and place the ships in the formation that they want to start the game in -- this will require the Ship class to also be created.

Time Estimated: 3 hours

Time Taken: 2.5 hours


### Then we will need to create the Weapon class without the additional features that we thought of, so that the user can attack the opposing team.

Time Estimated: 2 hours

Time Taken: 1 hours

### Next we need to keep track of the health of ships, update the health of each ship, keep track of when the ships have been destroyed and eventually when the game has been won as well.

Time Estimated: 2 hours

Time Taken: 3 hours


## TESTS
### Test-1
Title: Register 

Description: Any user with a computer should be able to access it..

Test Steps:
Register and input your name and the available user.
On the map, enter where you want your 3 different ships to be located .
Click the ‘Start Game’ button.
### Test-2
Title: Start Game

Description: Once registered you should be able to start your game.

Test Steps:
When it’s your turn, enter your attack coordinates.
Send your coordinates and get your results.
Notice how your map updates based on your results.
### Test-3
Title: Finish Game

Description: Once all the ships are sunk, the game should end.

Test Steps:
Sink all the ships
Notice if game ends and player are notified


## Revised Project Risks:
### Unit Testing
Risk Mitigation: We can rewatch the lecture concerning unit testing as well use internet resources 
### Working with UI
Risk Mitigation: As we code, we can learn by doing, as well as utilize the internet to learn more
### Experience with Git
Risk Mitigation: We will need to use Git extensively during this Milestone.


## Meeting Minutes
First we read over Milestone 2 requirements as a team and looked over the grading rubric

We started working on our first wiki and discussed as a team how we can plan the game, which user stories to tackle before the upcoming meeting, and then split up into two pairs to start pair programming through TDD to knock down the user stories.

We finished up the meeting by collaborating to figure out how long each segment of our coding should take.

We also reevaluated our project risks, added new risks, and wrote out some risk mitigation strategies as a team.
