package com.example.demo.producto;

import javax.persistence.Id;
import javax.persistence.Version;
import java.util.List;

public class Producto {
    @Id
    //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // @Column(name = "fase", length = 50)
    private String fase;

    private String nombre;

    private List<Version> versiones;

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

    public void setVersiones(List<Version> versiones) {
        this.versiones = versiones;
    }
}
