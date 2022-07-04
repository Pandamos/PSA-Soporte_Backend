package com.example.demo.tarea;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Proyecto {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("fechaInicio")
    private String fechaInicio;
    @JsonProperty("fechaFin")
    private String fechaFin;
    @JsonProperty("descripcion")
    private String descripcion;
    @JsonProperty("legajoLider")
    private long legajoLider;

    public Proyecto(){

    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public long getLegajoLider() {
        return legajoLider;
    }

    public void setLegajoLider(long legajoLider) {
        this.legajoLider = legajoLider;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


}
