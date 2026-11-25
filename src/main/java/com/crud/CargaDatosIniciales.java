package com.crud;

import java.io.InputStream;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import com.crud.model.ProductoRegistro;
import com.crud.repository.RepositorioDeProductos;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CargaDatosIniciales implements CommandLineRunner {

    private final RepositorioDeProductos repositorio;
    private final ObjectMapper objectMapper;

    public CargaDatosIniciales(RepositorioDeProductos repositorio, ObjectMapper objectMapper) {
        this.repositorio = repositorio;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repositorio.count() == 0) {
            InputStream inputStream = new ClassPathResource("productos.json").getInputStream();
            List<ProductoRegistro> productos = objectMapper.readValue(
                inputStream,
                new TypeReference<List<ProductoRegistro>>() {}
            );
            repositorio.saveAll(productos);
        }
    }
}