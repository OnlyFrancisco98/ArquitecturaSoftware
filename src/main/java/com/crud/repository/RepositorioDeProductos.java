package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.model.ProductoRegistro;

public interface RepositorioDeProductos extends JpaRepository<ProductoRegistro, Long> {

}