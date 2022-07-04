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

    @Override
    public EstadoTicket derivar() {
        throw new RuntimeException("Imposible derivar un ticket ya cerrado");
    }

    public String getestadoId() {
        return "cerrado";
    }

    @Override
    public void cambiarEstado(String estado) {

    }

}
