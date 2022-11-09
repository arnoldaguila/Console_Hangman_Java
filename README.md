# Console_Hangman
Remake of my console Hangman in python (Work in progress)

Version 11/8/22

- Created Started on the Java version of my python Hangman_Console game.
- Created the ConsoleHangman class; getWord, blankLinesPrint, and hangmanPrint methods.
- Created win boolean to print a win or lose message.
- Found a bug for the inputs. It messes with the variable chances and the hangmanPrint method.
- Need to test for more bugs.

Version 11/9/22

- Created an outer while loop to restart the game when the user wins or loses.
- Created 2 boolean variables endSwitch and restartSwitch to end the program while playing.
The user can also restart the game mid-game if the user wanted to have a fresh start.
- Created an if and else if statement to change the values of endSwitch and restartSwitch.
- Fixed the hangmanPrint() bug by adding breaks.
- Need to implement the ASCII so the user can't use numbers and symbols.