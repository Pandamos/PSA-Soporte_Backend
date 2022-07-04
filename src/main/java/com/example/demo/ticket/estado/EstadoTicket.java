package com.example.demo.ticket.estado;

public interface EstadoTicket {
    EstadoTicket abrir();
    EstadoTicket cerrar();
    EstadoTicket derivar();

    String getestadoId();

    EstadoTicket cambiarEstado(String estado);
}
