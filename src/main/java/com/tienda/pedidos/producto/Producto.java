package com.tienda.pedidos.producto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Producto {
    @Id
    private String sku;
    private String nombre;
    private BigDecimal precio;
    private int stock;

    public Producto() {
    }

    public Producto(String sku, String nombre, BigDecimal precio, int stockInicial) {
        this.sku = sku;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stockInicial;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stockInicial=" + stock +
                '}';
    }
}
