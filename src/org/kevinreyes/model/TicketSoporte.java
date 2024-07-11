/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.model;

/**
 *
 * @author informatica
 */
public class TicketSoporte {
    private int ticketSoportId;
    private String descripcionTicket;
    private String status;
    private String cliente;
    private int clienteId;
    private int  facturaId;

    public TicketSoporte() {
    }

    public TicketSoporte(int ticketSoportId, String descripcionTicket, String status, int clienteId, int facturaId) {
        this.ticketSoportId = ticketSoportId;
        this.descripcionTicket = descripcionTicket;
        this.status = status;
        this.clienteId = clienteId;
        this.facturaId = facturaId;
    }

    public TicketSoporte(int ticketSoportId, String descripcionTicket, String status, String cliente, int facturaId) {
        this.ticketSoportId = ticketSoportId;
        this.descripcionTicket = descripcionTicket;
        this.status = status;
        this.cliente = cliente;
        this.facturaId = facturaId;
    }

    public int getTicketSoportId() {
        return ticketSoportId;
    }

    public void setTicketSoportId(int ticketSoportId) {
        this.ticketSoportId = ticketSoportId;
    }

    public String getDescripcionTicket() {
        return descripcionTicket;
    }

    public void setDescripcionTicket(String descripcionTicket) {
        this.descripcionTicket = descripcionTicket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    @Override
    public String toString() {
        return "TicketSoporte: " + ticketSoportId + " | " + status;
    }
    
    
    
}


