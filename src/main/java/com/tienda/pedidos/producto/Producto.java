package com.tienda.pedidos.producto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Producto {
    @Id
    private String sku;
    private BigDecimal precio;
    private int stock;

    public Producto() {
    }

    public Producto(String sku, BigDecimal precio, int stockInicial) {
        this.sku = sku;
        this.precio = precio;
        this.stock = stockInicial;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "producto{" +
                "sku='" + sku + '\'' +
                ", precio=" + precio +
                ", stockInicial=" + stock +
                '}';
    }
}
