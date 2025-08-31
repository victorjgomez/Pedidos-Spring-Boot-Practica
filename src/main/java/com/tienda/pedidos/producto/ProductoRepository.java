package com.tienda.pedidos.producto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, String> {

    public Producto getBySku(String sku);
}
