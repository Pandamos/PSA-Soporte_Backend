package com.example.demo.ticket.estado;

public class Cerrado implements EstadoTicket{

    @Override
    public EstadoTicket abrir() {
        return new Abierto();
    }

    @Override
    public EstadoTicket cerrar() {
        throw new RuntimeException("Imposible cerrar un ticket ya cerrado");
    }

    public String getEstadoString() {
        return "cerrado";
    }

}
