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
- Created a game message using print statements, from line 32 to 38.
- Updated the if statement checking the input variable. It's now checking if the input length is over 1, is it 
end or restart.
- In the else statement of the input length check it converts the input string to a char then to an int (ascii)
then checks to see if the input is in the ascii range.
- Implemented a guessed letters print at the top of the hangmanPrint static method call at line 43 to 53.
- Created a game message method to trim down the code in the main.


Version 11/11/22

- Created a ConsoleHangmanV2.java so that I can lean up the main method and implement better practices.