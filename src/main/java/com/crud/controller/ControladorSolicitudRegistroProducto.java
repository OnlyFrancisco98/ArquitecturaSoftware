package com.crud.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.dto.ProductoRespuesta;
import com.crud.dto.SolicitudActualizarProducto;
import com.crud.dto.SolicitudCreacionProducto;
import com.crud.service.RegistradorDeProductos;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ControladorSolicitudRegistroProducto {

    public static final String MENSAJE_CREACION_EXITOSA = "Se registró el producto exitosamente";
    public static final String MENSAJE_ACTUALIZACION_EXITOSA = "El producto se actualizó exitosamente";
    public static final String MENSAJE_ELIMINACION_EXITOSA = "El producto se eliminó exitosamente";

    private final RegistradorDeProductos registroProductoServicio;

    public ControladorSolicitudRegistroProducto(RegistradorDeProductos registroProductoServicio) {
        this.registroProductoServicio = registroProductoServicio;
    }

    @GetMapping
    public ResponseEntity<List<ProductoRespuesta>> obtenerInventarioCompleto() {

        List<ProductoRespuesta> listaProductos = registroProductoServicio.listarTodoElInventario();

        // Retorna 200 OK con la lista en formato JSON
        return ResponseEntity.ok(listaProductos);
    }

    @PostMapping
    public ResponseEntity<String> registrarNuevoProducto(@Valid @RequestBody SolicitudCreacionProducto solicitud) {
        registroProductoServicio.crearProducto(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(MENSAJE_CREACION_EXITOSA);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProducto(@PathVariable Long id,
            @Valid @RequestBody SolicitudActualizarProducto solicitud) {

        registroProductoServicio.actualizarProducto(id, solicitud);
        return ResponseEntity.ok(MENSAJE_ACTUALIZACION_EXITOSA);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {

        registroProductoServicio.eliminarProducto(id);

        return ResponseEntity.ok(MENSAJE_ELIMINACION_EXITOSA);
    }
}
