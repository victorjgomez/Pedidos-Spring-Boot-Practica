package com.tienda.pedidos.pedido;

import com.tienda.pedidos.cobro.CobroService;
import com.tienda.pedidos.pedido.Estado;
import com.tienda.pedidos.pedido.comandos.ProcesarPedidoCommand;
import com.tienda.pedidos.producto.ProductoCommandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoResource {

    private final PedidoCommandService pedidoCommandService;
    private final PedidoQueryService pedidoQueryService;

    private final ProductoCommandService productoCommandService;

    private final CobroService cobroService;


    public PedidoResource(PedidoCommandService pedidoCommandService, PedidoQueryService pedidoQueryService,
                          ProductoCommandService productoCommandService, CobroService cobroService) {
        this.pedidoCommandService = pedidoCommandService;
        this.pedidoQueryService = pedidoQueryService;
        this.productoCommandService = productoCommandService;
        this.cobroService = cobroService;
    }

    @PostMapping("/orders")
    public Pedido crearPedido(@RequestBody Pedido pedido){
        return pedidoCommandService.crearPedido(pedido);
    }


    @GetMapping("/orders/{id}")
    public Pedido obtenerPedido(@PathVariable int id){
        return pedidoQueryService.getPedidoById(id);
    }

    @PostMapping("/orders/{id}/process")
    public Pedido procesarPedido(@PathVariable int id){
        Pedido pedido = pedidoCommandService.getById(id);
        if (pedido == null) {
            System.out.println("Pedido con ID " + id + " no encontrado.");
            throw new RuntimeException("Pedido con ID " + id + " no encontrado.");
        }
        if (pedido.getEstado() != Estado.CREATED) {
            return pedido;
        }
        ProcesarPedidoCommand procesarPedidoCommand = new ProcesarPedidoCommand(pedido, productoCommandService,
                pedidoCommandService, cobroService);

        return pedidoCommandService.procesarPedido(procesarPedidoCommand);
    }


}
