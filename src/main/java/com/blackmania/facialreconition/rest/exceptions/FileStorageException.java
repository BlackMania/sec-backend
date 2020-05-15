package com.blackmania.facialreconition.rest.exceptions;

public class FileStorageException extends Throwable {

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Exception cause) {
        super(message, cause);
    }
}
