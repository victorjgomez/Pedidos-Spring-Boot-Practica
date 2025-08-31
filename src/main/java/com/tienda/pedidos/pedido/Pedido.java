package com.tienda.pedidos.pedido;

import com.tienda.pedidos.productoPedido.ProductoPedido;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class Pedido {

    @Id
    private int orderId;

    private ArrayList<ProductoPedido> productos;

    private Estado estado;

    private Date fecha;

    public Pedido() {
    }

    public Pedido(int orderId, ArrayList<ProductoPedido> productos, Estado estado, Date fecha) {
        this.orderId = orderId;
        this.productos = productos;
        this.estado = estado;
        this.fecha = fecha;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ArrayList<ProductoPedido> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ProductoPedido> productos) {
        this.productos = productos;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "orderId=" + orderId +
                ", productos=" + productos +
                ", estado=" + estado +
                ", fecha=" + fecha +
                '}';
    }
}
