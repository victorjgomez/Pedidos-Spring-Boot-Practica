package com.tienda.pedidos.producto;

import org.springframework.stereotype.Service;

@Service
public class ProductoQueryService {

    private final ProductoRepository productoRepository;

    public ProductoQueryService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto getProductoById(String id) {
        return productoRepository.findById(id).orElseThrow(ProductoNotFoundException::new);
    }

}
