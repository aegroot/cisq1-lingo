package nl.hu.cisq1.lingo.trainer.domain.lingoRonde.exception;

public class BadWordException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BadWordException() {
        super("word not allowed");
    }
}
