package com.example.demo.ticket;

public interface EstadoTicket {
    EstadoTicket abrir();
    EstadoTicket cerrar();

    EstadoTicket reabrir();

    EstadoTicket derivar();
}
