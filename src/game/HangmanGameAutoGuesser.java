package game;

import util.HangmanDictionary;


/**
 * This class represents the traditional word-guessing game Hangman
 * where the computer guesses letters based on a predictable pattern.
 *
 * @author Robert C. Duvall
 */
public class HangmanGameAutoGuesser {

    private Executioner exe = new Executioner();
    private AutoGuesser player = new AutoGuesser();

    /**
     * Create Hangman game with the given dictionary of words to play a game with words 
     * of the given length and giving the user the given number of chances.
     */
    public HangmanGameAutoGuesser (HangmanDictionary dictionary, int wordLength, int numGuesses) {
        player.setUpExecutioner4AG(exe);
        exe.setUpSecretWord(dictionary, wordLength);
        player.setGuessingLetters(player.getFreqLetters());
        player.setGuesses(numGuesses);
        exe.setUpDisplayWord();
    }

    /**
     * Play one complete game.
     */
    public void play () {
        boolean gameOver = false;
        while (!gameOver) {
            printStatus();

            player.makeNextGuess();
            if (isGameLost()) {
                System.out.println("YOU ARE HUNG!!!");
                gameOver = true;
            }
            else if (isGameWon()) {
                System.out.println("YOU WIN!!!");
                gameOver = true;
            }
        }
        System.out.println("The secret word was " + exe.getSecretWord());
    }

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
        System.out.println("*** " + exe.getSecretWord());
        System.out.println();
    }
}
