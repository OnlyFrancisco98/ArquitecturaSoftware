package com.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRespuesta {
    private Long id;
    private String nombre;
    private Double precio;
    private int talla;
    private int stock;
}
