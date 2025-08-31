package com.tienda.pedidos.consultas;

import com.tienda.pedidos.pedido.Estado;
import com.tienda.pedidos.pedido.Pedido;
import com.tienda.pedidos.pedido.PedidoQueryService;
import com.tienda.pedidos.producto.Producto;
import com.tienda.pedidos.productoPedido.ProductoPedidoQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ConsultasResource {

    private final PedidoQueryService pedidoQueryService;
    private final ProductoPedidoQueryService productoPedidoQueryService;

    public ConsultasResource(PedidoQueryService pedidoQueryService, ProductoPedidoQueryService productoPedidoQueryService) {
        this.pedidoQueryService = pedidoQueryService;
        this.productoPedidoQueryService = productoPedidoQueryService;
    }


    @GetMapping("/reports/top-products")
    public List<Producto> productosMasVendidos(@RequestParam int n) {
        return productoPedidoQueryService.getProductosMasVendidos(n);
    }


    @GetMapping("/reports/orders-by-status")
    public List<Pedido> pedidosByEstado(@RequestParam Estado estado) {
        return pedidoQueryService.getPedidosByEstado(estado);
    }

    @GetMapping("/reports/daily-sales")
    public List<DailySalesDTO> pedidosByFecha(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return pedidoQueryService.getDailySales(from, to);
    }
}
