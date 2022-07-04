package com.example.demo.producto;

import java.util.List;

public class Producto {
    private Integer id;
    private String fase;

    private String nombre;

    private List<VersionProducto> versiones;

    public Producto(Integer id, String fase, String nombre) {
        this.fase = fase;
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return id;
    }

    public void setId(Integer codigo) {
        this.id = codigo;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {this.fase = fase;
    }

    public void setVersiones(List<VersionProducto> versiones) {
        this.versiones = versiones;
    }

}
