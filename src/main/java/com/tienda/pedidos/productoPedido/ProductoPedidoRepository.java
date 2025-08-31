package com.tienda.pedidos.productoPedido;

import com.tienda.pedidos.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoPedidoRepository extends JpaRepository<ProductoPedido, Integer> {
    @Query(value = "SELECT p.* " +
            "FROM producto p " +
            "JOIN producto_pedido pp ON p.sku = pp.sku " +
            "GROUP BY p.sku " +
            "ORDER BY SUM(pp.cantidad) DESC " +
            "LIMIT :n", nativeQuery = true)
    List<Producto> findProductosMasVendidosLimit(@Param("n") int n);
}
