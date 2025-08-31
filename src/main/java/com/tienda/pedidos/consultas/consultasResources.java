package com.tienda.pedidos.consultas;

import com.tienda.pedidos.pedido.Estado;
import com.tienda.pedidos.pedido.Pedido;
import com.tienda.pedidos.pedido.PedidoQueryService;
import com.tienda.pedidos.producto.Producto;
import com.tienda.pedidos.productoPedido.ProductoPedidoQueryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
public class consultasResources {

    private PedidoQueryService pedidoQueryService;
    private ProductoPedidoQueryService productoPedidoQueryService;

    public consultasResources(PedidoQueryService pedidoQueryService, ProductoPedidoQueryService productoPedidoQueryService) {
        this.pedidoQueryService = pedidoQueryService;
        this.productoPedidoQueryService = productoPedidoQueryService;
    }


    @RequestMapping(value = "/reports/top-products")
    public ArrayList<Producto> productosMasVendidos(@RequestParam int n){
        return (ArrayList<Producto>) productoPedidoQueryService.getProductosMasVendidos(n);
    }


    @RequestMapping(value = "/reports/orders-by-status")
    public ArrayList<Pedido> pedidosByEstado(@RequestParam Estado estado){
        return (ArrayList<Pedido>) pedidoQueryService.getPedidosByEstado(estado);
    }

    @RequestMapping(value = "/reports/daily-sales")
    public ArrayList<Pedido> pedidosByFecha(@RequestParam Date fecha){
        return (ArrayList<Pedido>) pedidoQueryService.getPedidosByFecha(fecha);
    }
}
