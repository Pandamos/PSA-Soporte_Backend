package com.example.demo.tarea;


import java.text.DateFormat;

public class Tarea {
    private String descripcion;
    private Integer empleados; //seguro?
    private String estado;
    private Integer id;
    private Integer idProyecto;
    private Integer idTicket;
    private String nombre;

    private DateFormat fechaCreacion;

    public Tarea(){

    }

    public Tarea(String descripcion, Integer empleados, DateFormat fechaCreacion,
                String estado, Integer id, Integer idProyecto, Integer idTicket, String nombre) {
        this.descripcion = descripcion;
        this.empleados = empleados;
        this.estado = estado;
        this.id = id;
        this.idProyecto = idProyecto;
        this.idTicket = idTicket;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Integer empleados) {
        this.empleados = empleados;
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

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DateFormat getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(DateFormat fechaCreacion){
        this.fechaCreacion = fechaCreacion;
    }
}