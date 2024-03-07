# Connect Four
This project involves implementing and testing a class representing the popular board game Connect Four. Connect Four is a two-player connection game played on a two-dimensional grid of tokens. The core functionality of the game revolves around strategically dropping tokens into columns to form a line of four tokens either horizontally, vertically, or diagonally. Below is an overview of the project's design and functionality:

## Design Overview
The project is structured to provide a class-based representation of the Connect Four game. It aims to offer a flexible and modular design, enabling easy interaction with the game's components. The codebase is organized into multiple files, each responsible for distinct aspects of the game's implementation. This design approach ensures better code organization, scalability, and maintainability.

## Functionality
The Connect Four class facilitates setting up a new game by initializing the game grid with the specified number of rows and columns. It assigns unique tokens to each player and prepares the game for play.

Players take turns dropping their tokens into columns on the grid. The tokens fall to the lowest available position in the selected column. The game continues until a player forms a line of four tokens, indicating a win, or the grid is filled, resulting in a draw.

The Connect Four game progresses through different phases, including NEW, READY, PLAYABLE, and OVER. Each phase corresponds to specific actions and behaviors within the game, such as initializing the game, setting up players, playing moves, and determining game outcomes.

## File Structure and Content
- **ConnectFour.java**: Contains the implementation of the ConnectFour class, responsible for managing the game state, handling player moves, and determining game outcomes.

- **ConnectFourTester.java**: Provides a testing framework for the ConnectFour class, allowing developers to verify the correctness and functionality of various methods and behaviors.

- **ConnectFourCLI.java**: Implements a command-line interface for playing Connect Four, enabling users to interactively engage with the game and test its functionality.
