package com.example.demo.ticket;



import com.example.demo.ticket.estado.*;
import com.example.demo.ticket.estado.EstadoTicket;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;



public class Ticket {

    private EstadoTicket estadoTicket;
    private Version version;

    private TicketTable ticketTable;

    public Ticket(){
        this.version = version;
        this.ticketTable = new TicketTable();
        this.estadoTicket = new Abierto();
    }

    public Version getVersion() {
        return this.version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    //public void setearEstado(){
     //   ticketTable.setEstado(estadoTicket.getestadoId());
    //}


    //public void cerrarTicket(){
    //   estadoTicket = new Cerrado();
    //   ticketTable.setEstado(estadoTicket.getestadoId());
    //}

   public void abrirTicket(){
        ticketTable.setEstado(estadoTicket.getestadoId());
    }
}
