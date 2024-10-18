package game;

public class AutoGuesser extends Guesser{
    private int index = -1;
    private static final String LETTERS_ORDERED_BY_FREQUENCY = "etaoinshrldcumfpgwybvkxjqz";


    public void makeNextGuess(){
        index++;
        System.out.println("AutoGuess: " + LETTERS_ORDERED_BY_FREQUENCY.charAt(index));
        makeGuess(LETTERS_ORDERED_BY_FREQUENCY.charAt(index));
    }

    public String getFreqLetters(){
        return LETTERS_ORDERED_BY_FREQUENCY;
    }

    

}
