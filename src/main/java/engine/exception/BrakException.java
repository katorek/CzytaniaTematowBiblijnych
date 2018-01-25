package engine.exception;

/**
 * Created by Wojciech Jaronski on 25.07.2017.
 */
public class BrakException extends Exception {
    BrakException(String s) {
        System.err.println("Brak " + s);
    }
}
