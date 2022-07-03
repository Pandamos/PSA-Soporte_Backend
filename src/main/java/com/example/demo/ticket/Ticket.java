package com.example.demo.ticket;



import com.example.demo.ticket.estado.Abierto;
import com.example.demo.ticket.estado.EstadoTicket;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "tickets")
public class Ticket implements Serializable {

    private EstadoTicket estadoTicket;
    private Version version;

    private TicketTable ticketTable;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CUIT_cliente",length = 50)
    private String CUIT;

    @Column(name = "fecha_creacion",length = 50)
    private DateFormat fechaDeCreacion;

    @Column(name = "fecha_finalizacion",length = 50)
    private DateFormat fechaDeFinalizacion;

    //@Column(name = "estado",length = 50)
    //private String estado;

    @Column(name = "descripcion",length = 50)
    private String descripcion;

    @Column(name = "severidad",length = 50)
    private Integer severidad;


    public Ticket(Integer id, String descripcion, Integer severidad,DateFormat fechaDeCreacion,DateFormat fechaDeFinalizacion,String CUIT){
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.severidad = severidad;
        //this.estadoTicket = new Abierto();
       // this.estado = estadoTicket.getestadoId();
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeFinalizacion = fechaDeFinalizacion;
        this.CUIT = CUIT;
    }

    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Version getVersion() {
        return this.version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Integer getSeveridad() {
        return severidad;
    }

    public void setSeveridad(Integer severidad) {
        this.severidad = severidad;
    }

    public DateFormat getFechaDeFinalizacion() {
        return fechaDeFinalizacion;
    }

    public void setFechaDeFinalizacion(DateFormat fechaDeFinalizacion) {
        this.fechaDeFinalizacion = fechaDeFinalizacion;
    }

    public DateFormat getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(DateFormat fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String CUIT) {
        this.CUIT = CUIT;
    }

    //public void cerrarTicket(){
    //    this.estado = null;//new Cerrado();
    //}

   // public void reabrirTicket(){
   //     this.estado = null;//new Abierto();
   // }
}
