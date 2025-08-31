package com.tienda.pedidos.producto;

public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException() {
        super("Producto no encontrado");
    }

    public ProductoNotFoundException(String message) {
        super(message);
    }
}
