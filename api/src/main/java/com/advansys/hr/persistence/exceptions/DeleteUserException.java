package com.advansys.hr.persistence.exceptions;

public class DeleteUserException extends RuntimeException {
    private static final long serialVersionUID = 5506033991116551044L;

    public DeleteUserException(String message) {
        super(message);
    }
}
