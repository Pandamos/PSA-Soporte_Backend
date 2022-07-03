package com.example.demo.empleado;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Empleado {
    @JsonProperty("legajo")
    public int legajo;
    @JsonProperty("Nombre")
    public String Nombre;
    @JsonProperty("Apellido")
    public String Apellido;
}

