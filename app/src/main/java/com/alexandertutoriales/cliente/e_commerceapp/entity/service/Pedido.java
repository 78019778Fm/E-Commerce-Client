package com.alexandertutoriales.cliente.e_commerceapp.entity.service;


import java.sql.Date;

public class Pedido {
    private int id;
    private Date fechaCompra;
    private Cliente cliente;
    private Double monto;
    private boolean anularPedido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public boolean isAnularPedido() {
        return anularPedido;
    }

    public void setAnularPedido(boolean anularPedido) {
        this.anularPedido = anularPedido;
    }
}
