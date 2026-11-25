package com.crud.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.crud.dto.ProductoRespuesta;
import com.crud.dto.SolicitudActualizarProducto;
import com.crud.dto.SolicitudCreacionProducto;
import com.crud.model.ProductoRegistro;
import com.crud.repository.RepositorioDeProductos;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegistradorDeProductos {
    private final RepositorioDeProductos repositorioDeProductos;

    public List<ProductoRespuesta> listarTodoElInventario() {

        List<ProductoRegistro> entidades = repositorioDeProductos.findAll();

        return entidades.stream()
                .map(entidad -> new ProductoRespuesta(
                        entidad.getId(),
                        entidad.getNombre(),
                        entidad.getPrecio(),
                        entidad.getTalla(),
                        entidad.getStock()))
                .collect(Collectors.toList());
    }

    public RegistradorDeProductos(RepositorioDeProductos repositorioDeProductos) {
        this.repositorioDeProductos = repositorioDeProductos;
    }

    public void crearProducto(SolicitudCreacionProducto solicitud) {

        ProductoRegistro producto = new ProductoRegistro();
        producto.setNombre(solicitud.getNombre());
        producto.setTalla(solicitud.getTalla());
        producto.setPrecio(solicitud.getPrecio());
        producto.setStock(solicitud.getStock());

        repositorioDeProductos.save(producto);
    }

    public void actualizarProducto(Long id, SolicitudActualizarProducto solicitud) {

        ProductoRegistro producto = obtenerProductoExistentePorId(id);
        producto.actualizar(solicitud);

        repositorioDeProductos.save(producto);
    }

    public void eliminarProducto(Long id) {
        obtenerProductoExistentePorId(id);
        repositorioDeProductos.deleteById(id);
    }

    public void modificarStock(Long id, int cantidad) {
        ProductoRegistro producto = obtenerProductoExistentePorId(id);

        producto.modificarStock(cantidad);
        repositorioDeProductos.save(producto);
    }

    private ProductoRegistro obtenerProductoExistentePorId(Long id) {
        ProductoRegistro productoBuscado = repositorioDeProductos.findById(id).orElse(null);
        if (productoBuscado == null) {
            throw new IllegalArgumentException("El producto con ID " + id + " no existe.");
        }

        return productoBuscado;
    }
}

