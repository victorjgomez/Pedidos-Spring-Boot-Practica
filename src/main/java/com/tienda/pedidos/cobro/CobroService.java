package com.tienda.pedidos.cobro;

import com.tienda.pedidos.pedido.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CobroService {

    private final boolean forzarFallaTemporal;

    public CobroService(@Value("${cobro.forzar-falla-temporal:false}") boolean forzarFallaTemporal) {
        this.forzarFallaTemporal = forzarFallaTemporal;
    }

    public boolean process(Pedido pedido){

        if (forzarFallaTemporal) {
            throw new RuntimeException("Falla temporal forzada");
        }

        BigDecimal total = pedido.getProductos().stream()
                .map(p -> p.getProducto().getPrecio().multiply(BigDecimal.valueOf(p.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int roundedValue = total.intValue();

        return (roundedValue % 2 == 0);
    }
}
