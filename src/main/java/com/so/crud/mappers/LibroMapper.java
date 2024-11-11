package com.so.crud.mappers;

import com.so.crud.domain.Libro;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LibroMapper {
    private static List<Libro> libros = new ArrayList<>();
    private static Long currentId = 1L;

    public Libro guardar(Libro libro) {
        libro.setId(currentId++);
        libros.add(libro);
        return libro;
    }

    public List<Libro> obtenerTodos() {
        return new ArrayList<>(libros);
    }

    public Libro obtenerPorId(Long id) {
        return libros.stream().filter(libro -> libro.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Libro> buscarPorNombre(String nombre) {
        return libros.stream().filter(libro -> libro.getTitulo().equalsIgnoreCase(nombre)).toList();
    }

    public Libro actualizar(Long id, Libro libroActualizado) {
        Optional<Libro> libroExistente = libros.stream().filter(libro -> libro.getId().equals(id)).findFirst();
        libroExistente.ifPresent(libro -> {
            libro.setTitulo(libroActualizado.getTitulo());
            libro.setAutor(libroActualizado.getAutor());
            libro.setGenero(libroActualizado.getGenero());
            libro.setAnoPublicacion(libroActualizado.getAnoPublicacion());
        });
        return libroExistente.orElse(null);
    }

    public void eliminar(Long id) {
        libros.removeIf(libro -> libro.getId().equals(id));
    }
}