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
 * @version 11/8/22
 */
public class ConsoleHangman {
    public static void main(String[] args) {
        gameMessage(); // running game message
        game(getWord("word_bank")); // running game.
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
            wordArray.add(input.nextLine());
        }
        input.close();
        return wordArray.get(randInt.nextInt(0, wordArray.size()));
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

    public static void game(String word){
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> guessedLettersBefore = new ArrayList<>(); // Array for the guessed letters
        int chances = 6; // number of body parts and chances.
        String secretWord = word;
        secretWord = cleanUp(secretWord);
        LinkedList<String> blankLinesArray = new LinkedList<>();
        for(int i = 0; i < secretWord.length(); i++){//adding blank lines for the number of letters in the
            blankLinesArray.add("_");                //secret word.
        }
//        System.out.println("Not supposed to be here: " + secretWord); //HERE FOR TESTING PURPOSE.
        while(true) {
            guessedLettersArray(guessedLettersBefore);
            hangmanPrint(chances); // prints the gallow and the hangman.
            System.out.println(blankLinesPrint(blankLinesArray));
            System.out.println("Chances left: " + chances);
            System.out.println();
            System.out.print("Enter a letter: ");
            String input = userInput.nextLine();
            input = input.toLowerCase();
            input = input.replaceAll("\\s", "");
            if (input.equals("")) {
                System.out.println("Error: You didn't enter a letter.");
            } else if (input.length() > 1) { //if input was more than one letter
                if (input.equals("end")) { //checking to see if the input is end.
                    endSwitch = true;
                    break;
                } else if (input.equals("restart")) { //checking to see if input is restart.

                    break;
                } else { // if neither end | restart print error message.
                    System.out.println("Error: Can't input more then 1 letter.");
                }
            } else {
                int ascii = input.charAt(0);
                if ((ascii < 97) || (ascii > 122)) {
                    System.out.println("Error invalid input. please enter a letter.");
                } else {
                    if (guessedLettersBefore.contains(input)) { //if letter is in the guessedLettersBefore array.
                        System.out.println("You already guess that letter.");
                        chances = chances - 1;
                        if (chances == 0) {
                            loss();
                            break;
                        }
                    } else {
                        if (secretWord.contains(input)) { //if letter is in the secretWord
                            guessedLettersBefore.add(input);
                            for (int i = 0; i < input.length(); i++) {
                                for (int j = 0; j < secretWord.length(); j++) {
                                    if (input.charAt(i) == secretWord.charAt(j)) {
                                        blankLinesArray.set(j, input);
                                    }
                                }
                            }
                            System.out.println();
                            if (blankLinesPrint(blankLinesArray).equals(secretWord)) {
                                win();
                                break;
                            }
                        } else {
                            chances = chances - 1;
                            if (chances == 0) {
                                break;
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



    public static void win(){
        Scanner user_input = new Scanner(System.in);
        System.out.println("You won!");
        System.out.print("Do you want to play again?");
        String answer = user_input.next();
        answer = cleanUp(answer);
    }

    /**
     * cleanUp()
     * This method sets the input string to lower case and removes all spaces.
     * @param user_input
     */
    public static String cleanUp(String user_input){
        user_input = user_input.toLowerCase();
        return user_input.replace(" ","");
    }

    public static void guessedLettersArray(ArrayList<String> guessedLettersBefore){
        if (guessedLettersBefore.size() != 0) { // printing the guessed letters array.
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