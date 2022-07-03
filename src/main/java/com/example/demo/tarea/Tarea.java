package com.example.demo.tarea;


public class Tarea {
    private String descripcion;
    private Integer empleados; //seguro?
    private String estado;
    private Integer id;
    private Integer idProyecto;
    private Integer idTicket;
    private String nombre;

    public Tarea(String descripcion, Integer empleados, //seguro?,
                String estado, Integer id, Integer idProyecto, Integer idTicket, String nombre) {
        this.descripcion = descripcion;
        this.empleados = empleados;
        this.estado = estado;
        this.id = id;
        this.idProyecto = idProyecto;
        this.idTicket = idTicket;
        this.nombre = nombre;
    }

}