package com.example.demo.ticket;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;

@Entity
@Table(name = "ticket_table")
public class TicketTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CUIT_cliente",length = 50)
    private String CUIT;

    @Column(name = "fecha_creacion",length = 50)
    private DateFormat fechaDeCreacion;

    @Column(name = "fecha_finalizacion",length = 50)
    private DateFormat fechaDeFinalizacion;

    @Column(name = "descripcion",length = 50)
    private String descripcion;

    @Column(name = "severidad",length = 50)
    private Integer severidad;

    @Column(name = "estado",length = 50)
    private String estado;

    @Column(name = "version_id",length = 50)
    private Integer versionId;

    public TicketTable() {
    }

    public TicketTable(Integer id, String descripcion, Integer severidad,DateFormat fechaDeCreacion,DateFormat fechaDeFinalizacion,String CUIT,String estado,Integer versionId){
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.severidad = severidad;
        this.estado = estado;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeFinalizacion = fechaDeFinalizacion;
        this.CUIT = CUIT;
        this.versionId = versionId;
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

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }
}
