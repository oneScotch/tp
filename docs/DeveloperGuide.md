# Developer Guide

## Acknowledgements
Inspiration for User Guide and Developer Guide: AddressBook (Level 3)
https://se-education.org/addressbook-level3/DeveloperGuide.html
https://se-education.org/addressbook-level3/UserGuide.html

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation
###System Architecture

System Architecture diagram of the design of Code Hunt:
// Todo {diagram}

**Main component of the architecture (Game.java)**

On launch, the Game.java initialises the logger and input scanner. It calls Game main menu to enter the game.

The rest of the Code Hunt consists of below components:

**Ui** :Command Line User Interface of Code Hunt. (Menu classes)
- main/Game main menu: Loads previous record or initializes the components in the correct sequence, and connects them up with each other.
- game/Game menu: Chooses easy/difficult game level; Checks game progress/ achieved cards content.
- game/Easy menu, Difficult menu: Two game levels.
- card/Card menu: 

**Parser**: Parser to parser user inputs from Ui.

**IO**: The input/output formatter.

**Errors**: Show different error information.

**Messages** Numbers/Strings: Store all the constant values that are used in this programme and shown to user

**Data**:
- `Player`: The database of all data for one player
- `Card`: The card with coding knowledge that a player can collect
- `CardManager`: All operations that player can do to a card list
- `Game`: The parent class of game
- `GameRecord`: The list storing all the games completed by the user
- `QuizGame`: The game base on four forms of quiz
- `GuessingNumGame`: The guessing number game
- `TreasureHuntGame`: The treasure hunt game
- `TreasureHuntMap`: All the maps for the treasure hunt game

The Class Diagram shows the associations between classes:
![](https://plantuml.com/class-diagram)

###How the architecture components interact with each other

The Sequence Diagram below shows how the components interact with each other for the scenario where the user issues the command
//TODO {diagram}



## Product scope

### Target user profile
This product is for children ages 6 and up who have little prior coding experience but have an interest in discovering their interests and talents in the technology field.

### Value proposition

As digital literacy becomes increasingly important, this product is designed for preparing our next generation to be future-ready. It would raise children's interest in coding by simple coding tasks and help them learn some basic coding skills through different levels of text-based adventure games. It is also designed to help children to develop their logical thinking.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
