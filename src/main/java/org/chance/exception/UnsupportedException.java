package org.chance.exception;

public class UnsupportedException extends UnsupportedOperationException {

    public static final long serialVersionUID = 128391028309174821L;

    public UnsupportedException() {
        super();
    }

    public UnsupportedException(String message) {
        super(message);
    }

    public UnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedException(Throwable cause) {
        super(cause);
    }
}
