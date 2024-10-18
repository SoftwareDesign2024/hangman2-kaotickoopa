package game;

import util.ConsoleReader;
import util.HangmanDictionary;


/**
 * This class represents the traditional word-guessing game Hangman
 * that plays interactively with the user.
 *
 * @author Robert C. Duvall
 */
public class HangmanGameCheatingComputer {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    

    // word that is being guessed
    
    // how many guesses are remaining
    
    // what is shown to the user
    
    // tracks letters guessed
    
    // executioner state
    

    private CheatingExecutioner exe = new CheatingExecutioner();
    private Guesser player = new Guesser();



    /**
     * Create Hangman game with the given dictionary of words to play a game with words 
     * of the given length and giving the user the given number of chances.
     */
    public HangmanGameCheatingComputer (HangmanDictionary dictionary, int wordLength, int numGuesses) {

        player.setGuesses(numGuesses);
        player.setGuessingLetters(player.getAlph());
        exe.setUpSecretWord(dictionary, numGuesses);
        exe.setUpDisplayWord();
        /*
        mySecretWord = getSecretWord(dictionary, wordLength);
        myNumGuessesLeft = numGuesses;
        myLettersLeftToGuess = new StringBuilder(ALPHABET);
        myDisplayWord = new DisplayWord(mySecretWord);
        myRemainingWords = dictionary.getWords(wordLength);
         */
        
        exe.setUpRemainingWords(dictionary, wordLength);
        
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
    private void makeGuess (char guess) {
    	exe.cheat(guess);
        // do not count repeated guess as a miss
        int index = player.getLettersLeft().indexOf("" + guess);
        if (index >= 0) {
            player.recordGuess(index);
            if (! exe.checkGuessInSecret(guess)) {
                player.decreaseNumGuesses();
            }
        }
    }
    // Returns true only if the guesser has guessed all letters in the secret word.
    private boolean isGameWon () {
        return exe.getDisplayWord().toString().equals(exe.getSecretWord());
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
