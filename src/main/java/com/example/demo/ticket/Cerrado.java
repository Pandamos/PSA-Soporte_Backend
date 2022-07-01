package com.example.demo.ticket;

public class Cerrado implements EstadoTicket {
    @Override
    public EstadoTicket abrir() {
        return new Abierto();
    }

    @Override
    public EstadoTicket cerrar() {
        throw new RuntimeException("Imposible cerrar un ticket ya cerrado");
    }

    @Override
    public EstadoTicket reabrir() {
        return new Abierto();
    }

    @Override
    public EstadoTicket derivar() {
        throw new RuntimeException("Imposible derivar un ticket ya cerrado");
    }
}
