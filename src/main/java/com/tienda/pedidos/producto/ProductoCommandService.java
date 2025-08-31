package com.tienda.pedidos.producto;

import org.springframework.stereotype.Service;

@Service
public class ProductoCommandService {

    private final ProductoRepository productoRepository;

    public ProductoCommandService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public synchronized Producto decreaseStock(String id, int cantidad) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(ProductoNotFoundException::new);

        if (producto.getStock() < cantidad) {
            throw new OutOfStockException();
        }

        producto.setStock(producto.getStock() - cantidad);
        return productoRepository.save(producto);
    }

    public synchronized Producto increaseStock(String id, int cantidad) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(ProductoNotFoundException::new);

        producto.setStock(producto.getStock() + cantidad);
        return productoRepository.save(producto);
    }


}
