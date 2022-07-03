package com.example.demo.cliente;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cliente {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("razon social")
    public String razon_social;
    @JsonProperty("CUIT")
    public String CUIT;
}