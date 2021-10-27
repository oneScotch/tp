# User Guide

## Introduction

Code Hunt is a text-based adventure game using command lines for children aged 4 to 9 who have little prior coding experience but have an interest in coding to learn basic coding knowledge through playing games and collecting knowledge cards.

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest codehunt.jar from [here](http://link.to/duke).
3. Copy the file to the folder you want to use as the home folder for your CodeHunt.
4. Double-click the file to start the game.
5. Type the command in the command box and press Enter to execute it. Here are some example commands you can try:
   - `help`: List all commands you can use, show their functions and examples.
   - `clear`: Clear your previous game record and reload the game from the beginning
   - `pause`: Save your current game progress and go back to game-level selection option
   - `exit`: Exit the game and the system will automatically save your game progress to your local file in your computer
6. If you need any help, you can refer to [this website](http://link.to/duke) to check the User Guide


## Features 

{Give detailed description of each feature}

###Main menu
#### start a new game `newgame`
Start a new game 
Format: `newgame`

#### continue the game recorded previously `continue`
Continue with a locally stored game progress
Format: `continue`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Copy the local folder containing the CodeHunt jar file and `{username}.txt file to another device
(only copy the txt file is available, but ensure that it is in the same folder with CodeHunt jar file)

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
