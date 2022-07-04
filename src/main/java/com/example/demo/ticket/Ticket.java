package com.example.demo.ticket;



import com.example.demo.producto.VersionProducto;
import com.example.demo.ticket.estado.*;
import com.example.demo.ticket.estado.EstadoTicket;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;



public class Ticket {

    private EstadoTicket estadoTicket;
    private VersionProducto version;

    private TicketTable ticketTable;

    public Ticket(TicketTable ticketTable){
        this.version = version;
        this.ticketTable = ticketTable;
        this.estadoTicket = new Abierto();
    }

    public VersionProducto getVersion() {
        return this.version;
    }

    public void setVersion(VersionProducto version) {
        ticketTable.setVersionId(version.getCodigo_producto());
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
