import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleHangmanV2 {
    public static void main(String[] args) {
        gameMessage();
        game();
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
        String secretWord = getWord("word_bank.txt");
        secretWord = secretWord.toLowerCase(); // setting secretWord to lower for game purposes.
        secretWord = secretWord.replace("\\s", "");
        String hiddenWord= blankLines(secretWord);

        System.out.println("Not supposed to be here: " + secretWord); //HERE FOR TESTING PURPOSE.

        String guess = userInput.next();
        guess = guess.toLowerCase();
        guess = guess.replace("\\s", "");
        if (guess.equals("end")){
            return; // stop's method
        }else if(guess.equals("restart")){
            game(); // recurse to restart.
        }else{

        }

    }

    public static String blankLines(String secretWord){
        String blankLines = "";
        for (int i = 0; i < secretWord.length(); i++){
            blankLines = blankLines + "_";
        }
        return blankLines;
    }

}