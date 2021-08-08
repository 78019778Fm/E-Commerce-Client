package com.alexandertutoriales.cliente.e_commerceapp.entity.service.dto;


import com.alexandertutoriales.cliente.e_commerceapp.entity.service.Cliente;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.DetallePedido;
import com.alexandertutoriales.cliente.e_commerceapp.entity.service.Pedido;

import java.util.ArrayList;
import java.util.List;

public class GenerarPedidoDTO {
    private Pedido pedido;
    private ArrayList<DetallePedido> detallePedidos;
    private Cliente cliente;

    public GenerarPedidoDTO() {
        this.pedido = new Pedido();
        this.detallePedidos = new ArrayList<>();
        this.cliente = new Cliente();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(ArrayList<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }
}
