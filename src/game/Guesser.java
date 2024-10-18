package game;

public class Guesser {

    private StringBuilder myLettersLeftToGuess;
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private int myNumGuessesLeft;
    private Executioner exe;

    public void makeGuess (char guess) {
        // do not count repeated guess as a miss
        int index = myLettersLeftToGuess.indexOf("" + guess);
        if (index >= 0) {
            recordGuess(index);
            //System.out.println("Guess: " + guess);
            if (! exe.checkGuessInSecret(guess)) {
                myNumGuessesLeft -= 1;
            }
        }
    }

    public void setUpExecutioner4AG(Executioner exe){
        this.exe = exe;
    }

     // Record that a specific letter was guessed
    public void recordGuess (int index) {
        myLettersLeftToGuess.deleteCharAt(index);
    }

    public void setGuessingLetters(String ALPHABET){
        myLettersLeftToGuess = new StringBuilder(ALPHABET);
    }

    public int getGuessesLeft(){
        return myNumGuessesLeft;
    }
    public void setGuesses(int setNumber){
        myNumGuessesLeft = setNumber;
    }

    public void decreaseNumGuesses(){
        myNumGuessesLeft--;
    }
    public StringBuilder getLettersLeft(){
        return myLettersLeftToGuess;
    }

    public String getAlph(){
        return ALPHABET;
    }

    
   
}
