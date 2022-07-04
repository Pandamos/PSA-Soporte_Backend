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
    private String cuit;

    @Column(name = "fecha_creacion",length = 50)
    private Integer fechaDeCreacion;

    @Column(name = "fecha_finalizacion",length = 50)
    private Integer fechaDeFinalizacion;

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

    public TicketTable(Integer id, String descripcion, Integer severidad,Integer fechaDeCreacion,Integer fechaDeFinalizacion,String CUIT,String estado,Integer versionId){
        super();
        this.id = id;
        this.descripcion = descripcion;
        this.severidad = severidad;
        this.estado = estado;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeFinalizacion = fechaDeFinalizacion;
        this.cuit = CUIT;
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

    public Integer getFechaDeFinalizacion() {
        return fechaDeFinalizacion;
    }

    public void setFechaDeFinalizacion(Integer fechaDeFinalizacion) {
        this.fechaDeFinalizacion = fechaDeFinalizacion;
    }

    public Integer getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Integer fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }
}
