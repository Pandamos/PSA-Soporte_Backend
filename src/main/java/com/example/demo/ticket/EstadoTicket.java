package com.example.demo.ticket;

public interface EstadoTicket {
    EstadoTicket abrir();
    EstadoTicket cerrar();
    EstadoTicket derivar();

    String getestadoId();
}
