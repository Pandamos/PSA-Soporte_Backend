package com.example.demo.ticket.estado;

public interface EstadoTicket {
    EstadoTicket abrir();
    EstadoTicket cerrar();

    String getEstadoString();
}
