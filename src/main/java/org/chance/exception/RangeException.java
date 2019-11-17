package org.chance.exception;

public class RangeException extends IllegalArgumentException {

    public static final long serialVersionUID = 128391028309174821L;

    public RangeException() {
        super();
    }

    public RangeException(String message) {
        super(message);
    }

    public RangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RangeException(Throwable cause) {
        super(cause);
    }
}
