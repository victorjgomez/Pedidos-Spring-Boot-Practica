package com.tienda.pedidos.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Date;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    public ArrayList<Pedido> findByEstado(Estado estado);

    public ArrayList<Pedido> findByfecha(Date fecha);

    public ArrayList<Pedido> findByFechaBetween(Date from, Date to);
}
