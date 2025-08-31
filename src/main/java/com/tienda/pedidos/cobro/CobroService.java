package com.tienda.pedidos.cobro;

import com.tienda.pedidos.pedido.Pedido;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CobroService {

    public boolean process(Pedido pedido){

        BigDecimal total = pedido.getProductos().stream().map(p -> p.getProducto().getPrecio()).reduce(BigDecimal.ZERO, BigDecimal::add);

        int roundedValue = total.intValue();

        return (roundedValue % 2 == 0);
    }
}
