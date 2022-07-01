package com.example.demo.ticket;

public class Abierto implements EstadoTicket {
    @Override
    public EstadoTicket abrir() {
        throw new RuntimeException("Imposible abrir un ticket ya abierto");
    }

    @Override
    public EstadoTicket cerrar() {
        return new Cerrado();
    }

    @Override
    public EstadoTicket reabrir() {
        throw new RuntimeException("Imposible reabrir Ticket abierto.");
    }

    @Override
    public EstadoTicket derivar() {
        return null;
    }
}
