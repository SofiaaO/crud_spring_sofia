package com.so.crud.services;

import com.so.crud.domain.Libro;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.FileSystemResource;
import java.util.List;

public interface LibroService {
    Libro crearLibro(Libro libro);
    List<Libro> obtenerTodos();
    Libro obtenerPorId(Long id);
    List<Libro> buscarPorNombre(String nombre);
    Libro actualizarLibro(Long id, Libro libro);
    void eliminarLibro(Long id);
    ResponseEntity<FileSystemResource> generarReportePorGenero(String genero);
}
