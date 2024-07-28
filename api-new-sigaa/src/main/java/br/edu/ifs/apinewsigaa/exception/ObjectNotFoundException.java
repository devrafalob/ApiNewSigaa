package br.edu.ifs.apinewsigaa.exception;

import java.io.Serial;

public class ObjectNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public ObjectNotFoundException(String msg) {
        super(msg);
    }
    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
