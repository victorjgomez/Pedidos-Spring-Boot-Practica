package com.tienda.pedidos.pedido;


import com.tienda.pedidos.consultas.DailySalesDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<DailySalesDTO> getDailySales(LocalDate from, LocalDate to) {
        var fromDate = java.util.Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
        var toDate = java.util.Date.from(to.atStartOfDay(ZoneId.systemDefault()).toInstant());
        ArrayList<Pedido> pedidos = pedidoRepository.findByFechaBetween(fromDate, toDate);
        Map<LocalDate, BigDecimal> totals = new HashMap<>();
        for (Pedido pedido : pedidos) {
            LocalDate date = pedido.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            BigDecimal total = pedido.getProductos().stream()
                    .map(p -> p.getProducto().getPrecio())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            totals.merge(date, total, BigDecimal::add);
        }
        List<DailySalesDTO> result = new ArrayList<>();
        totals.forEach((d, t) -> result.add(new DailySalesDTO(d, t)));
        return result;
    }
}
