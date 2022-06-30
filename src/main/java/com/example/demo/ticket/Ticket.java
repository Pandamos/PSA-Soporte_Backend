package com.example.demo.ticket;

import javax.persistence.*;

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
    private String descripcion;
    private Integer severidad;

    //constructors
    public Ticket() {
    }

    public Ticket(Integer id, String descripcion, Integer severidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.severidad = severidad;
    }

    public Ticket(String descripcion, Integer severidad) {
        this.descripcion = descripcion;
        this.severidad = severidad;
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
}
