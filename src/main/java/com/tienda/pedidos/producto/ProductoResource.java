package com.tienda.pedidos.producto;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoResource {

    private ProductoCommandService productoCommandService;
    private ProductoQueryService productoQueryService;

    public ProductoResource(ProductoCommandService productoCommandService, ProductoQueryService productoQueryService) {
        this.productoCommandService = productoCommandService;
        this.productoQueryService = productoQueryService;
    }

    @PostMapping(value = "/products")
    public Producto crearProducto(@RequestBody Producto producto){
        return productoCommandService.crearProducto(
                producto.getSku(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock()
        );
    }


}
