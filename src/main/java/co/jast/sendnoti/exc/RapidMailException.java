package co.jast.sendnoti.exc;

public class RapidMailException extends RuntimeException {

    public RapidMailException() {
        super();
    }

    public RapidMailException(String message) {
        super(message);
    }

    public RapidMailException(String message, Throwable cause) {
        super(message, cause);
    }

}
