package com.tienda.pedidos.pedido.comandos;

import com.tienda.pedidos.cobro.CobroService;
import com.tienda.pedidos.pedido.Estado;
import com.tienda.pedidos.pedido.Pedido;
import com.tienda.pedidos.pedido.PedidoCommandService;
import com.tienda.pedidos.producto.ProductoCommandService;
import com.tienda.pedidos.productoPedido.ProductoPedido;

import java.util.ArrayList;

public class ProcesarPedidoCommand extends Comando {


    private final Pedido pedido;

    private ProductoCommandService productoCommandService;

    private PedidoCommandService pedidoCommandService;

    private CobroService cobroService;


    public ProcesarPedidoCommand(Pedido pedido, ProductoCommandService productoCommandService,
                                 PedidoCommandService pedidoCommandService, CobroService cobroService) {
        this.pedido = pedido;
        this.productoCommandService = productoCommandService;
        this.pedidoCommandService = pedidoCommandService;
        this.cobroService = cobroService;
    }

    @Override
    public Pedido ejecutar() {
        // Lógica para procesar el pedido
        ArrayList<ProductoPedido> productos = pedido.getProductos();

        for (ProductoPedido productoPedido : productos){
            productoCommandService.discreaseStock(productoPedido.getProducto().getSku(), productoPedido.getCantidad());
        }

        boolean permiso = this.cobroService.process(pedido);

        if (!permiso){
            throw new RuntimeException("El pedido no tiene autorizacion para proceder");
        }

        return pedidoCommandService.cambiarEstado(pedido, Estado.CONFIRMED);
    }

    @Override
    public Pedido deshacer() {
        // Lógica para deshacer el procesamiento del pedido

        ArrayList<ProductoPedido> productos = pedido.getProductos();

        for (ProductoPedido productoPedido : productos){
            productoCommandService.increaseStock(productoPedido.getProducto().getSku(), productoPedido.getCantidad());
        }

        return pedidoCommandService.cambiarEstado(pedido, Estado.REJECTED);
    }
}
