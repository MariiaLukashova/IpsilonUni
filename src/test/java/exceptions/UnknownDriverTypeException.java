package exceptions;

/**
 * it throws if driver not usable
 */
public class UnknownDriverTypeException extends RuntimeException{
    public UnknownDriverTypeException(String driverName){
        super("Unknown webdriver: " + driverName);
    }
}
