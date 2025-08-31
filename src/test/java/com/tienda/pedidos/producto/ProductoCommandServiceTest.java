package com.tienda.pedidos.producto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductoCommandServiceTest {

    @Autowired
    private ProductoCommandService productoCommandService;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void crearProductoPersisteNombre() {
        String sku = "sku-test";
        String nombre = "Producto de prueba";
        BigDecimal precio = new BigDecimal("10.00");
        int stock = 5;

        productoCommandService.crearProducto(sku, nombre, precio, stock);
        Producto productoGuardado = productoRepository.findById(sku).orElseThrow();

        assertEquals(nombre, productoGuardado.getNombre());
        assertEquals(precio, productoGuardado.getPrecio());
        assertEquals(stock, productoGuardado.getStock());
    }
}
