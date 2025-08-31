package com.tienda.pedidos.productoPedido;

import com.tienda.pedidos.producto.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoPedidoQueryService {

    private final ProductoPedidoRepository productoPedidoRepository;

    public ProductoPedidoQueryService(ProductoPedidoRepository productoPedidoRepository) {
        this.productoPedidoRepository = productoPedidoRepository;
    }

    public List<Producto> getProductosMasVendidos(int n) {
        return productoPedidoRepository.findProductosMasVendidosLimit(n);
    }
}
