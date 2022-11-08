import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * Hangman
 * This is the hangman class for the hangman console game.
 * @author Arnold Aguila
 * @version 11/8/22
 */
public class Hangman {
    public static void main(String[] args) {
        ArrayList<Character> guessedLetters = new ArrayList<>();
        int chances = 6;
        String secretWord = getWord("word_bank.txt");
        String blankLines = "";
        for(int i = 0; i < secretWord.length(); i++){ // creating blank lines that is the length of the secret word.
            blankLines = blankLines + "_";
        }

        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();
        input = input.toLowerCase();
        input = input.replace(" ", "");
        if (input.length() > 1){
            System.out.println("Error: Can't input more then 1 letter.");
        }else{
            char letter = input.charAt(0);
            if(guessedLetters.contains(letter)){
                chances--;
                

            }
        }
    }

    /**
     * getWord()
     * This function gets random word from a txt file.
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
}
