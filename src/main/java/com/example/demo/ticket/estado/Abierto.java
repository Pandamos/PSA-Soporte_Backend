package com.example.demo.ticket.estado;

public class Abierto implements EstadoTicket{

    @Override
    public EstadoTicket abrir() {
        throw new RuntimeException("Imposible abrir o reabrir un ticket ya abierto");
    }

    @Override
    public EstadoTicket cerrar() {
        return new Cerrado();
    }


    public String getEstadoString(){
        return "abierto";
    }

}
