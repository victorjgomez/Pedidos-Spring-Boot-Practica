package com.tienda.pedidos.pedido;


import com.tienda.pedidos.pedido.comandos.ProcesarPedidoCommand;
import com.tienda.pedidos.producto.Producto;
import com.tienda.pedidos.producto.ProductoRepository;
import com.tienda.pedidos.productoPedido.ProductoPedido;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PedidoCommandService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoCommandService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido getById(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido crearPedido(Pedido pedido) {
        pedido.setEstado(Estado.CREATED);
        return pedidoRepository.save(pedido);
    }

    public Pedido cambiarEstado(Pedido pedido, Estado estado) {
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }

    public ArrayList<Producto> obtenerProductos(Pedido pedido) {
        ArrayList<Producto> productos = new ArrayList<>();

        for (ProductoPedido productoPedido : pedido.getProductos()) {
            Producto producto = this.productoRepository.getBySku(productoPedido.getProducto().getSku());

            if (producto != null) {
                productos.add(producto);
            }
            else {
                System.out.println("Producto con SKU " + productoPedido.getProducto().getSku() + " no encontrado.");
                throw new RuntimeException("Producto con SKU " + productoPedido.getProducto().getSku() + " no encontrado.");
            }
        }

        return productos;
    }

    public synchronized Pedido procesarPedido(ProcesarPedidoCommand procesarPedidoCommand){
        try {
            return procesarPedidoCommand.ejecutar();
        }
        catch (Exception e){
            System.out.println("Error al procesar el pedido: " + e.getMessage());
            return procesarPedidoCommand.deshacer();
        }
    }
}
