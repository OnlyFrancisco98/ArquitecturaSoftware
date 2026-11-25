package com.crud.model;

import com.crud.dto.SolicitudActualizarProducto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registro_de_prendas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRegistro {

    @Id // Se designa la clave primaria a "id"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se indica que el valor del ID se genera automáticamente por
                                                        // la base de datos
    private Long id; // int8 en DB

    @Column(name = "nombre", nullable = false, unique = true) // Se indica la columna de la tabla que mapea este
                                                              // atributo
    private String nombre; // varchar en DB

    @Column(name = "talla", nullable = false, unique = false)
    private Integer talla; // int2 en DB

    @Column(name = "precio", nullable = false, unique = false)
    private Double precio; // float8 en DB

    @Column(name = "stock", nullable = false, unique = false)
    private int stock;

    public void actualizar(SolicitudActualizarProducto solicitud) {
        this.nombre = solicitud.getNombre();
        this.precio = solicitud.getPrecio();
        this.talla = solicitud.getTalla();
        this.stock = solicitud.getStock();
    }

    public void modificarStock(int cantidad) {
        int nuevoStock = this.stock + cantidad;

        this.stock = nuevoStock;
    }
}
