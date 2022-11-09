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
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> guessedLettersBefore = new ArrayList<>(); // Array for the guessed letters
        boolean win = false; //for winning and losing message.
        int chances = 6;//6 chances cause there are 6 body parts. Head, body, left arm, right arm, left leg, right leg.
        String secretWord = getWord("word_bank.txt");
        LinkedList<String> blankLinesArray = new LinkedList<>();
        for(int i = 0; i < secretWord.length(); i++){//adding blank lines for the number of letters in the secret word.
            blankLinesArray.add("_");
        }
        System.out.println("Not supposed to be here: " + secretWord); //HERE FOR TESTING PURPOSE.

        while(true){
            hangmanPrint(chances); // prints the gallow and the hangman.
            System.out.println(blankLinesPrint(blankLinesArray));
            System.out.println("Chances left: " + chances);
            System.out.println();
            System.out.print("Enter a letter: ");
            String input = userInput.nextLine();
            input = input.toLowerCase();
            input = input.replace(" ", "");
            if (input.length() > 1){ //if input was more than one letter
                System.out.println("Error: Can't input more then 1 letter.");
            }else{
                if(guessedLettersBefore.contains(input)){ //if letter is in the guessedLettersBefore array.
                    System.out.println("You already guess that letter.");
                    chances = chances - 1;
                    if(chances == 0){
                        break;
                    }
                }else{
                    if(secretWord.contains(input)){ //if letter is in the secretWord
                        guessedLettersBefore.add(input);
                        //Nested for loop to check if input string matches with the letter of the secretWord than
                        //gets the index of secretWord's letter that matched with input and uses it for the
                        //blankLinesArray set method and changes a blank line with the input letter.
                        for(int i = 0; i < input.length(); i++){
                            for(int j = 0; j < secretWord.length(); j++){
                                if (input.charAt(i) == secretWord.charAt(j)){
                                    blankLinesArray.set(j, input);
                                }
                            }
                        }
                        if (blankLinesPrint(blankLinesArray).equals(secretWord)){
                            win = true;
                            break;
                        }
                    }else {
                        chances = chances - 1;
                        if(chances == 0){
                            break;
                        }else{
                            guessedLettersBefore.add(input);
                            System.out.println();
                        }

                    }
                }
            }
        }

        if(win){ // win loss messages.
            System.out.println("You Win!");
        }else{
            System.out.println(("You lost."));
        }
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
     * @param chances
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
            case 5:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
            case 4:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|         |");
                System.out.println("|");
                System.out.println("|");
            case 3:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|        /|");
                System.out.println("|");
                System.out.println("|");
            case 2:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|        /|\\");
                System.out.println("|");
                System.out.println("|");
            case 1:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|        /|\\");
                System.out.println("|        /");
                System.out.println("|");
            case 0:
                System.out.println("___________");
                System.out.println("|         |");
                System.out.println("|         O");
                System.out.println("|        /|\\");
                System.out.println("|        / \\");
                System.out.println("|");
        }
    }

}
