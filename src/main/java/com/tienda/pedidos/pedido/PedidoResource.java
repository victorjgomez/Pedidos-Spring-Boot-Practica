package com.tienda.pedidos.pedido;

import com.tienda.pedidos.cobro.CobroService;
import com.tienda.pedidos.pedido.comandos.ProcesarPedidoCommand;
import com.tienda.pedidos.producto.ProductoCommandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoResource {

    private PedidoCommandService pedidoCommandService;
    private PedidoQueryService pedidoQueryService;

    private ProductoCommandService productoCommandService;

    private CobroService cobroService;


    public PedidoResource(PedidoCommandService pedidoCommandService, PedidoQueryService pedidoQueryService) {
        this.pedidoCommandService = pedidoCommandService;
        this.pedidoQueryService = pedidoQueryService;
    }

    @RequestMapping(value = "/orders")
    public Pedido crearPedido(@RequestBody Pedido pedido){
        return pedidoCommandService.crearPedido(pedido);
    }


    @RequestMapping(value = "/order")
    public Pedido obtenerPedido(@RequestParam int id){
        return pedidoQueryService.getPedidoById(id);
    }

    @RequestMapping(value = "/processOrder")
    public Pedido procesarPedido(int id){
        Pedido pedido = pedidoCommandService.getById(id);
        if (pedido == null) {
            System.out.println("Pedido con ID " + id + " no encontrado.");
            throw new RuntimeException("Pedido con ID " + id + " no encontrado.");
        }
        ProcesarPedidoCommand procesarPedidoCommand = new ProcesarPedidoCommand(pedido, productoCommandService,
                pedidoCommandService, cobroService);

        return pedidoCommandService.procesarPedido(procesarPedidoCommand);
    }


}
