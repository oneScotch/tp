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


## UI pages
### Main page:
```
 ____          _        _   _             _  
/ ___|___   __| | ___  | | | |_   _ _ __ | |_
| |   / _ \ / _` |/ _ \ | |_| | | | | '_ \| __|
| |__| (_) | (_| |  __/ |  _  | |_| | | | | |_
\____\___/ \__,_|\___| |_| |_|\__,_|_| |_|\__|
Welcome to A text-based code game for children ages 6 and up(Code Hunt).Are you ready to play?
Type "help" to see a list of available commands.
```

- `newgame`     Start a new game. The system will give you a string of user number that is used to continue the game next time.
- `continue`    Continue the game stored previously.
- `help `       Display a list of available commands.
- `about`       Display information about Code Hunt.
- `exit`        Exit Code Hunt.

### Intermediate page
- `start`       Start the game
- `check`       Check current game progress
- `card`        Manage your holding cards
- 'back'        Back to main menu
- `exit`        Exit Code Hunt

### Difficulty level page: choose game difficulty level
- `Easy Level`:
- `Difficult Level`:

### Game page

## Games
- `Guessing Number Game`
  The game is to guess the secret number in a given range in limited attempts
- `Hangman Game`
  The game is to guess a word about coding by giving a letter every time with limited failing attempts
- `Quiz Game`
  The game is to do a mini quiz about coding containing different types of questions like MCQ, TFQ, MRQ and fill-in-blank quesitons
- `Treasure Hunt Game`
  The game is to move the treasure hunter to find the treasure noted in the map

## Card
- `winCard`
- `listCard`
- `deleteCard`
- `findCard`
- `buyTip`



## FAQ

**Q**: How do I reload my game?
=======
**A**: Remember the username given to you when you first play the game. You can reload the game by entering
this username next time.

## Command Summary

Action |  Format | Function
 ------------ | ------------- | -------------
newgame | newgame | Start a new game
continue | econtinue | Continue the game stored previously
help | help | Display a list of available commands
about | about | Display information about Code Hunt
exit | exit | Exit Code Hunt
start | start | Start the game
check | check | Check current game progress
card | card | Manage your holding cards
back | back | Back to main menu


