package com.tienda.pedidos.pedido;

import com.tienda.pedidos.pedido.Estado;
import com.tienda.pedidos.producto.Producto;
import com.tienda.pedidos.producto.ProductoRepository;
import com.tienda.pedidos.productoPedido.ProductoPedido;
import com.tienda.pedidos.productoPedido.ProductoPedidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PedidoCascadeTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoPedidoRepository productoPedidoRepository;

    @Test
    void savesPedidoWithProductos() {
        Producto producto = new Producto("SKU-1", new BigDecimal("10.00"), 10);
        productoRepository.save(producto);

        Pedido pedido = new Pedido(1, null, Estado.CREATED, new Date());
        ProductoPedido productoPedido = new ProductoPedido(producto, pedido, 2);
        pedido.setProductos(List.of(productoPedido));

        pedidoRepository.save(pedido);

        assertEquals(1, productoPedidoRepository.count());
    }
}
