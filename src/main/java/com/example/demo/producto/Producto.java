package com.example.demo.producto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("fase")
    private String fase;
    @JsonProperty("nombre")
    private String nombre;
    private List<VersionProducto> versiones;

    public Producto(Integer id, String fase, String nombre) {
        this.fase = fase;
        this.id = id;
        this.nombre = nombre;
        this.versiones = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {this.fase = fase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVersiones(List<VersionProducto> versiones) {
        this.versiones = versiones;
    }

}
