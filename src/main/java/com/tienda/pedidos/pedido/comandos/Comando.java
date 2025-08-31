package com.tienda.pedidos.pedido.comandos;

import com.tienda.pedidos.pedido.Pedido;

public abstract class Comando {
    public abstract Pedido ejecutar();

    public abstract Pedido deshacer();
}
