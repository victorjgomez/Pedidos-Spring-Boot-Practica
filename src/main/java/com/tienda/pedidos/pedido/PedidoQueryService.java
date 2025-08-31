package com.tienda.pedidos.pedido;


import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PedidoQueryService {

    private final PedidoRepository pedidoRepository;

    public PedidoQueryService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido getPedidoById(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public ArrayList<Pedido> getPedidosByEstado(Estado estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public ArrayList<Pedido> getPedidosByFecha(java.util.Date fecha) {
        return pedidoRepository.findByfecha(fecha);
    }
}
