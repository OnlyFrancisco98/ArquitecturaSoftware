package com.crud.dto;

import lombok.Data;

@Data
public class SolicitudCreacionProducto {

    private String nombre;
    private Double precio;
    private int talla;
    private int stock;
}
