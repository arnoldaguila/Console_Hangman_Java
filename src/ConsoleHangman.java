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
        while(true){
            Scanner userInput = new Scanner(System.in);
            ArrayList<String> guessedLettersBefore = new ArrayList<>(); // Array for the guessed letters
            boolean win = false; //for winning and losing message.
            boolean endSwitch = false;
            boolean restartSwitch = false;
            int chances = 6;//6 chances cause there are 6 body parts. Head, body, left arm, right arm, left leg,
                            //right leg.
            String secretWord = getWord("word_bank.txt");
            String og = secretWord; // saving original word
            secretWord = secretWord.toLowerCase(); // setting secretWord to lower for gaming purposes.
            LinkedList<String> blankLinesArray = new LinkedList<>();
            for(int i = 0; i < secretWord.length(); i++){//adding blank lines for the number of letters in the
                blankLinesArray.add("_");                //secret word.
            }

            // Game message.
            System.out.println();
            System.out.println("This is a hangman console game. When prompted please enter a letter.");
            System.out.println("You can type 'end' + the enter button to shut down the game early or type in");
            System.out.println("'restart' + the enter button to restart the game if you want to restart mid-game.");
            System.out.println("Good Luck :)");
            System.out.println();
//        System.out.println("Not supposed to be here: " + secretWord); //HERE FOR TESTING PURPOSE.

            while(true){
                hangmanPrint(chances); // prints the gallow and the hangman.
                System.out.println(blankLinesPrint(blankLinesArray));
                System.out.println("Chances left: " + chances);
                System.out.println();
                System.out.print("Enter a letter: ");
                String input = userInput.nextLine();
                input = input.toLowerCase();
                input = input.replaceAll("\\s", "");
                if (input.length() > 1){ //if input was more than one letter
                    if(input.equals("end")){ //checking to see if the input is end.
                        endSwitch = true;
                        break;
                    }else if (input.equals("restart")) { //checking to see if input is restart.
                        restartSwitch = true;
                        break;
                    }else{ // if neither end | restart print error message.
                        System.out.println("Error: Can't input more then 1 letter.");
                    }
                }else{
                    int ascii = input.charAt(0);
                    if ((ascii < 97) || (ascii > 122)){
                        System.out.println("Error invalid input. please enter a letter.");
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
            }

            if (endSwitch == false && restartSwitch == false){
                if(win){ // win loss messages.
                    System.out.println(og);
                    System.out.println();
                    System.out.println("YOU WIN!");
                }else{
                    System.out.println();
                    hangmanPrint(chances);
                    System.out.printf("The word was %s.%n", og);
                    System.out.println(("You lose."));
                }
                System.out.println();
                System.out.println("Do you want to play again?");
                System.out.print("Enter Yes or No: ");
                String onOff = userInput.nextLine();
                onOff = onOff.toLowerCase();
                onOff = onOff.replace("\\s", "");
                if (onOff.equals("no")){
                    System.out.println("Ending program.");
                    break;
                }
            }else if (restartSwitch == true && endSwitch == false){
                System.out.println("Restarting Program.");
            }else{
                System.out.println("Ending program.");
                break;
            }
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

}
