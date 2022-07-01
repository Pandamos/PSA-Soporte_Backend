package com.example.demo.ticket;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table
public class Ticket {
    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )

    private Integer id;
    private String titulo;
    private String descripcion;
    private Integer severidad;
    private DateFormat fechaVencimiento;
    private Integer responsableId;
    private Integer clienteId;

    private EstadoTicket estado;

    public Ticket(Integer id, String titulo, String descripcion, Integer severidad, DateFormat fechaVencimiento, Integer idResponsable, Integer idCliente) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.severidad = severidad;
        this.fechaVencimiento = fechaVencimiento;
        this.responsableId = idResponsable;
        this.clienteId = idCliente;
        this.estado = new Abierto();
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

    public Integer getSeveridad() {
        return severidad;
    }

    public void setSeveridad(Integer severidad) {
        this.severidad = severidad;
    }

     public Integer getResponsableId() {
        return this.responsableId;
    }

    public void setResponsableId(Integer responsableId) {
        this.responsableId = responsableId;
    }

    public EstadoTicket getEstado(){
        return this.estado;
    }

    public void setEstado(EstadoTicket estado){
        this.estado = estado;
    }

    public DateFormat getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    public void setFechaVencimiento(DateFormat fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }
}
