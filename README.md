# Connect Four
This project involves implementing and testing a class representing the popular board game Connect Four. Connect Four is a two-player connection game played on a two-dimensional grid of tokens. The basic rules are as follows:
- Players take turns dropping tokens into non-full columns on the grid.
- Tokens fall to the lowest available position in the column.
- The game ends when one player successfully connects four tokens horizontally, vertically, or diagonally, or when the grid is completely filled.
The implemented class will provide the functionality to manage the game's state and allow for gameplay interaction. The specific requirements and phases of the game are detailed below.

## Design Overview
The project follows a hierarchical structure with the ConnectFour class representing the core game logic. This class is expected to support various game phases and interactions. Additionally, the ConnectFourTester class is provided for testing the functionality of the `ConnectFour` class under different scenarios. The `ConnectFourCLI` class offers a command-line interface for playing the game.

## Functionality
`ConnectFour`:
- Initializes a game grid with specified dimensions.
- Manages player tokens and their turns.
- Tracks the state of the game, including phase transitions.
- Implements methods to drop tokens, check for wins, and manage game phases.

`ConnectFourTester`:
- Provides test cases to verify the behavior of the ConnectFour class.
- Tests various scenarios, including exceptional cases and phase transitions.
- Ensures the correctness and robustness of the implemented functionality.

`ConnectFourCLI`:
- Enables players to interactively play the game via the command-line interface.
- Facilitates user input for dropping tokens and displays the current state of the game.
- Serves as a tool for manual testing and debugging of the ConnectFour class.


## File Structure and Content
connect-four/
    ├── src/
    │   ├── ConnectFour.java
    │   ├── ConnectFourCLI.java
    │   └── ConnectFourTester.java
    ├── lib/
    │   └── gameutil.jar
    └── README.md