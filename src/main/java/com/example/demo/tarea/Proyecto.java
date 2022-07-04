package com.example.demo.tarea;

public class Proyecto {

    private Integer proyectoId;

    private String nombre;

    private String estado;

    private String fechaInicio;

    private String fechaFin;

    private String descripcion;

    private long legajoLider;

    public Proyecto(){

    }

    public Integer getProyectoId(){
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId){
        this.proyectoId = proyectoId;
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
