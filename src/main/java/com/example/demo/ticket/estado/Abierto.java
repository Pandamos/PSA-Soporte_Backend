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

    @Override
    public EstadoTicket derivar() {
        return null;
    }

    public String getestadoId(){
        return "abierto";
    }

    @Override
    public EstadoTicket cambiarEstado(String estado) {
        if(estado.equals("abrir")){
            throw new RuntimeException("Imposible abrir un ticket ya abierto");
        }
        else {
            return new Cerrado();
        }
    }
}
