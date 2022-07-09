package com.example.demo.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.DataAmount;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.sql.Date;

@Entity
@Table(name = "ticket_table")
public class TicketTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "CUIT_cliente",length = 50)
    private String cuit;
    @Column(name = "estado",length = 400)
    private String estado;
    @Column(name = "titulo",length = 400)
    private String titulo;
    @Column(name = "descripcion",length = 400)
    private String descripcion;
    @Column(name = "fecha_creacion",length = 50)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String fechaDeCreacion;
    @Column(name = "fecha_finalizacion",length = 50)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String fechaDeFinalizacion;
    @Column(name = "severidad",length = 50)
    private Integer severidad;
    @Column(name = "version_id",length = 50)
    private Integer versionId;
    @Column(name = "legajo_responsable",length = 50)
    private Integer legajoResponsable;

    public TicketTable() {
    }

    public TicketTable(Integer id, String descripcion, Integer severidad,String fechaDeCreacion,String fechaDeFinalizacion,String CUIT,String estado,Integer versionId, String titulo, Integer legajoResponsable){
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.severidad = severidad;
        this.estado = estado;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeFinalizacion = fechaDeFinalizacion;
        this.cuit = CUIT;
        this.versionId = versionId;
        this.titulo = titulo;
        this.legajoResponsable = legajoResponsable;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

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

    public String getFechaDeFinalizacion() {
        return fechaDeFinalizacion;
    }

    public void setFechaDeFinalizacion(String fechaDeFinalizacion) {
        this.fechaDeFinalizacion = fechaDeFinalizacion;
    }
    public String getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(String fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

}
