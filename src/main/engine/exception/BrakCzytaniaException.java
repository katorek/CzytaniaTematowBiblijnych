package engine.exception;

/**
 * Created by Wojciech Jaronski on 25.07.2017.
 */
public class BrakCzytaniaException extends BrakException{
    public BrakCzytaniaException(String s) {
        super("czytania: "+s);
    }
}
