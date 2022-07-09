package com.example.demo.producto;

import antlr.Version;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class VersionProducto implements Serializable {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("numero_version")
    private String numero_version;
    @JsonProperty("caracteristicas")
    private String caracteristicas;
    @JsonProperty("productoId")
    private Integer productoId;

    public VersionProducto(){}
    public VersionProducto(Integer id, String numero_version, Integer productoID, String caracteristicas) {
        this.caracteristicas = caracteristicas;
        this.numero_version = numero_version;
        this.productoId = productoID;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNumero_version() {
        return numero_version;
    }
    public void setNumero_version(String numero_version) {
        this.numero_version = numero_version;
    }
    public String getCaracteristicas() {
        return caracteristicas;
    }
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

}
