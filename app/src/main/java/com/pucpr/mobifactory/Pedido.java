package com.pucpr.mobifactory;

public class Pedido {
    private String id;
    private String cliente;
    private String dataEntrega;
    private String status;

    public Pedido(String id, String cliente, String dataEntrega, String status) {
        this.id = id;
        this.cliente = cliente;
        this.dataEntrega = dataEntrega;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public String getStatus() {
        return status;
    }
}