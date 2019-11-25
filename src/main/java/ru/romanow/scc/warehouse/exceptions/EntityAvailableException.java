package ru.romanow.scc.warehouse.exceptions;

public class EntityAvailableException
        extends RuntimeException {
    public EntityAvailableException(String message) {
        super(message);
    }
}
