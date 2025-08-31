package com.tienda.pedidos.producto;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException() {
        super("Stock insuficiente");
    }

    public OutOfStockException(String message) {
        super(message);
    }
}
