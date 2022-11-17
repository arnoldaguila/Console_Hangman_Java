import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


/**
 * ConsoleHangman
 * This is the ConsoleHangman class it contains a static main and getWord methods.
 * @author Arnold Aguila
 * @version 11/17/22
 */
public class ConsoleHangman {
    public static void main(String[] args) {
        gameMessage(); // running game message
        game(); // running game.
    }


    /**
     * cleanUp()
     * This method sets the input string to lower case and removes all spaces.
     * @param user_input
     */
    public static String cleanUp(String user_input){
        user_input = user_input.toLowerCase();
        return user_input.replace("\\s","");
    }


    /**
     * getWord()
     * This static method gets random word from a txt file.
     * @param file
     * @return RANDOM: String,
     */
    public static String getWord(String file){
        Random randInt = new Random();
        Scanner input = null;
        try{
            input = new Scanner(new File(file));
        }catch (FileNotFoundException e){
            System.out.println("Can't find the file yo.");
        }
        ArrayList<String> wordArray = new ArrayList<>();
        while(input.hasNextLine()){
            wordArray.add(input.nextLine()); // reading file and adding to an ArrayList
        }
        input.close();
        return wordArray.get(randInt.nextInt(0, wordArray.size())); // returning a random word from the ArrayList.
    }

    /**
     * blankLinesPrint()
     * This static method gets a LinkedList String argument and returns a String.
     * @param blankLines LinkedList String,
     * @return output String
     */
    public static String blankLinesPrint(LinkedList<String> blankLines){
        String output = "";
        for(int i = 0; i < blankLines.size(); i++){
            output = output + blankLines.get(i);
        }
        return output;
    }

    /**
     * hangmanPrint()
     * This static method prints out the gallow and the hangman.
     * @param chances int
     */
    public static void hangmanPrint(int chances){
        switch (chances){
            case 6:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("------");
                break;
            case 5:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("------");
                break;
            case 4:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|         |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("------");
                break;
            case 3:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|        /|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("------");
                break;
            case 2:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|        /|\\");
                System.out.println("|");
                System.out.println("|");
                System.out.println("------");
                break;
            case 1:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|        /|\\");
                System.out.println("|        /");
                System.out.println("|");
                System.out.println("------");
                break;
            case 0:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|        /|\\");
                System.out.println("|        / \\");
                System.out.println("|");
                System.out.println("------");
                break;
        }
    }

    /**
     * gameMessage()
     * This method prints out the welcome and controls
     */
    public static void gameMessage(){
        System.out.println();
        System.out.println("This is a hangman console game. When prompted please enter a letter.");
        System.out.println("You can type 'end' + the enter button to shut down the game early or type in");
        System.out.println("'restart' + the enter button to restart the game if you want to restart mid-game.");
        System.out.println("Good Luck :)");
        System.out.println();
    }

    public static void game(){
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> guessedLettersBefore = new ArrayList<>(); // Array for the guessed letters
        int chances = 6; // number of body parts and chances.
        String secretWord = getWord("word_bank.txt");
        secretWord = cleanUp(secretWord);
        LinkedList<String> blankLinesArray = new LinkedList<>();
        for(int i = 0; i < secretWord.length(); i++){//adding blank lines for the number of letters in the
            blankLinesArray.add("_");                //secret word.
        }
//        System.out.println("Not supposed to be here: " + secretWord); //HERE FOR TESTING PURPOSE.

        while(true){
            System.out.println();
            guessedLettersArray(guessedLettersBefore); // printing out the letters that are guessed.
            hangmanPrint(chances); // prints the gallow and the hangman.
            System.out.println(blankLinesPrint(blankLinesArray)); // printing out the blank lines
            System.out.println("Chances left: " + chances); // printing out how many guesses the user has left.
            System.out.println();
            System.out.print("Enter a letter: ");
            String input = userInput.nextLine();
            input = cleanUp(input);
            if (input.equals("")) {
                System.out.println("Error: You didn't enter a letter.");
            } else if (input.length() > 1) { //if input was more than one letter
                if (input.equals("end")) { //checking to see if the input is end.
                    System.out.println();
                    System.out.println("Ending game.");
                    return;
                } else if (input.equals("restart")) { //checking to see if input is restart.
                    System.out.println();
                    System.out.println("Restarting game.");
                    game();
                } else { // if neither end | restart print error message.
                    System.out.println("Error: Can't input more then 1 letter.");
                }
            } else {
                int ascii = input.charAt(0);
                if ((ascii < 97) || (ascii > 122)) { // checking to see if the input is a letter.
                    System.out.println("Error invalid input. Please enter a letter.");
                } else {
                    if (guessedLettersBefore.contains(input)) { //if letter has been guessed before.
                        System.out.println("You already guess that letter.");
                    }else{
                        if (secretWord.contains(input)) { //if letter is in the secretWord
                            guessedLettersBefore.add(input);
                            for (int i = 0; i < input.length(); i++) {
                                for (int j = 0; j < secretWord.length(); j++) {
                                    if (input.charAt(i) == secretWord.charAt(j)) {
                                        blankLinesArray.set(j, input); // updating blank lines when the user guesses correctly
                                    }
                                }
                            }
                            System.out.println();
                            if (blankLinesPrint(blankLinesArray).equals(secretWord)) {
                                win();
                                return;
                            }
                        } else {
                            chances = chances - 1;
                            if (chances == 0) {
                                loss(secretWord);
                                return;
                            } else {
                                guessedLettersBefore.add(input);
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * win()
     * This static method prints a win message, then asks the user if they want to play again.
     */
    public static void win(){
        Scanner user_input = new Scanner(System.in);
        System.out.println("You won!");
        while(true){
            System.out.print("Do you want to play again? Yes or no: ");
            String answer = user_input.next();
            answer = cleanUp(answer);
            if (answer.equals("")){
                System.out.println("Error: Invalid Input. Enter Yes or No.");
            } else if (answer.equals("yes")) {
                System.out.println("Restarting game.");
                game();
            } else if (answer.equals("no")) {
                System.out.println("Ending game");
                break;
            }else {
                System.out.println("Error: Invalid Input. Enter Yes or No.");
            }
        }
    }

    /**
     * loss()
     * This static method prints a loss message, then asks the user if they want to play again.
     */
    public static void loss(String secretWord){
        Scanner user_input = new Scanner(System.in);
        System.out.println("You lose.");
        System.out.printf("The word is %s%n", secretWord);
        while(true){
            System.out.print("Do you want to play again? Yes or no: ");
            String answer = user_input.next();
            answer = cleanUp(answer);
            if (answer.equals("")){
                System.out.println();
                System.out.println("Error: Invalid Input. Enter Yes or No.");
            } else if (answer.equals("yes")) {
                System.out.println();
                System.out.println("Restarting game.");
                game();
            } else if (answer.equals("no")) {
                System.out.println();
                System.out.println("Ending game");
                break;
            }else {
                System.out.println();
                System.out.println("Error: Invalid Input. Enter Yes or No.");
            }
        }
    }

    /**
     * guessedLettersArray()
     * This static method prints out the guessed letters
     * @param guessedLettersBefore
     */
    public static void guessedLettersArray(ArrayList<String> guessedLettersBefore){ // printing the guessed letters array.
        if (guessedLettersBefore.size() != 0) { // if the ArrayList is empty don't print anything out.
            String guessedLettersBeforePrint = "{";
            for (int i = 0; i < guessedLettersBefore.size(); i++) {
                if (i != guessedLettersBefore.size() - 1) {
                    guessedLettersBeforePrint = guessedLettersBeforePrint + "'" + guessedLettersBefore.get(i) + "',";
                } else {
                    guessedLettersBeforePrint = guessedLettersBeforePrint + "'" + guessedLettersBefore.get(i) + "'}";
                }
            }
            System.out.println("Guessed letters -> " + guessedLettersBeforePrint);
        }
    }

}