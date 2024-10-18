package game;

import util.DisplayWord;
import util.HangmanDictionary;

public class Executioner {
    private String mySecretWord;
    private DisplayWord myDisplayWord;

    public boolean checkGuessInSecret (char guess) {
        //System.out.println("SW: " + mySecretWord + " DW: " + myDisplayWord);
        if (mySecretWord.indexOf(guess) >= 0) {
            myDisplayWord.update(guess, mySecretWord);
            return true;
        }
        return false;
    }

    private String makeSecretWord (HangmanDictionary dictionary, int wordLength) {
        return dictionary.getRandomWord(wordLength).toLowerCase();
    }

    public void setUpSecretWord(HangmanDictionary dictionary, int wordLength){
        mySecretWord = makeSecretWord(dictionary, wordLength);
    }
    public void setUpDisplayWord(){
         myDisplayWord = new DisplayWord(mySecretWord);
    }

    public DisplayWord getDisplayWord(){
        return myDisplayWord;
    }

    public String getSecretWord(){
        return mySecretWord;
    }

    public void setSecretWord(String sw){
        mySecretWord = sw;
    }

    public void setDisplayWord(DisplayWord dw){
        myDisplayWord = dw;
    }
}
