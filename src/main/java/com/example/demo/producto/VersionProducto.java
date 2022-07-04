package com.example.demo.producto;

public class VersionProducto {
    private Integer id;
    private String numero_version;
    private String caracteristicas;
    private Producto producto;
    public VersionProducto(){}

    public VersionProducto(Integer id, String numero_version, Producto producto, String caracteristicas) {
        this.caracteristicas = caracteristicas;
        this.numero_version = numero_version;
        this.producto = producto;
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
