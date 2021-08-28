package ru.romanow.scc.warehouse.exceptions;

public class OrderItemAlreadyExistsException
        extends RuntimeException {
    public OrderItemAlreadyExistsException(String message) {
        super(message);
    }
}
