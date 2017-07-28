package engine.exception;

/**
 * Created by Wojciech Jaronski on 25.07.2017.
 */
public class BrakKsiegiException extends BrakException{
    public BrakKsiegiException(String s) {
        super("ksiegi: "+s);
    }
}
