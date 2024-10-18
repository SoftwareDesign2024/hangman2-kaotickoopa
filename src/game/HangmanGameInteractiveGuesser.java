package game;

import util.ConsoleReader;
import util.HangmanDictionary;


/**
 * This class represents the traditional word-guessing game Hangman
 * that plays interactively with the user.
 *
 * @author Robert C. Duvall
 * @author Shannon Duvall
 */
public class HangmanGameInteractiveGuesser {
    

    // word that is being guessed
    
    // how many guesses are remaining
    
    // what is shown to the user
    
    // tracks letters guessed

    private Executioner exe = new Executioner();
    private Guesser player = new Guesser();


    /**
     * Create Hangman game with the given dictionary of words to play a game with words 
     * of the given length and giving the user the given number of chances.
     */
    public HangmanGameInteractiveGuesser (HangmanDictionary dictionary, int wordLength, int numGuesses) {
        
        player.setGuesses(numGuesses);
        player.setGuessingLetters(player.getAlph());
        exe.setUpSecretWord(dictionary, numGuesses);
        exe.setUpDisplayWord();
    }

    /**
     * Play one complete game.
     */

    
    public void play () {
        boolean gameOver = false;
        while (!gameOver) {
            printStatus();

            String guess = ConsoleReader.promptString("Make a guess: ");
            if (guess.length() == 1 && Character.isAlphabetic(guess.charAt(0))) {
                makeGuess(guess.toLowerCase().charAt(0));
                if (isGameLost()) {
                    System.out.println("YOU ARE HUNG!!!");
                    gameOver = true;
                }
                else if (isGameWon()) {
                    System.out.println("YOU WIN!!!");
                    gameOver = true;
                }
            }
            else {
                System.out.println("Please enter a single letter ...");
            }
        }
        System.out.println("The secret word was " + exe.getSecretWord());
    }


    // Process a guess by updating the necessary internal state.
    public void makeGuess (char guess) {
        // do not count repeated guess as a miss
        int index = player.getLettersLeft().indexOf("" + guess);
        if (index >= 0) {
            player.recordGuess(index);
            if (! exe.checkGuessInSecret(guess)) {
                player.decreaseNumGuesses();
            }
        }
    }

   

    // Returns true only if given guess is in the secret word.
    

    // Returns a secret word.
    

    // Returns true only if the guesser has guessed all letters in the secret word.
    private boolean isGameWon () {
        return exe.getDisplayWord().equals(exe.getSecretWord());
    }

    // Returns true only if the guesser has used up all their chances to guess.
    private boolean isGameLost () {
        return player.getGuessesLeft() == 0;
    }

    // Print game stats
    private void printStatus () {
        System.out.println(exe.getDisplayWord());
        System.out.println("# misses left = " + player.getGuessesLeft());
        System.out.println("letters not yet guessed = " + player.getLettersLeft());
        // NOT PUBLIC, but makes it easier to test
        //System.out.println("*** " + mySecretWord);
        System.out.println();
    }
}
