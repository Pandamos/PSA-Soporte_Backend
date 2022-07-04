package com.example.demo.ticket;



import com.example.demo.producto.VersionProducto;
import com.example.demo.ticket.estado.*;
import com.example.demo.ticket.estado.EstadoTicket;


public class Ticket {

    private EstadoTicket estadoTicket;
    private VersionProducto version;

    private TicketTable ticketTable;

    public Ticket(TicketTable ticketTable,VersionProducto version){
        this.version = version;
        this.ticketTable = ticketTable;
        this.estadoTicket = new Abierto();
    }


    public VersionProducto getVersion() {
        return this.version;
    }

    public void setVersion(VersionProducto version) {
        ticketTable.setVersionId(version.getId());
    }

    public void setearEstado(){
        ticketTable.setEstado(estadoTicket.getestadoId());
    }
    public void cambiarEstado(String estadoTicket){
        this.estadoTicket = this.estadoTicket.cambiarEstado(estadoTicket);
    }

    public void cerrarTicket(){
       this.estadoTicket = estadoTicket.cerrar();
       ticketTable.setEstado(estadoTicket.getestadoId());
    }

   public void abrirTicket(){
       this.estadoTicket = estadoTicket.abrir();
        ticketTable.setEstado(estadoTicket.getestadoId());
   }




}
