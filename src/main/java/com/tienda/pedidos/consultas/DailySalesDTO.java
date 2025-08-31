package com.tienda.pedidos.consultas;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailySalesDTO {
    private LocalDate date;
    private BigDecimal total;

    public DailySalesDTO(LocalDate date, BigDecimal total) {
        this.date = date;
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
