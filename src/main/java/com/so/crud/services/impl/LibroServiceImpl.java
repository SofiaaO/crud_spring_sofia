package com.so.crud.services.impl;

import com.so.crud.domain.Libro;
import com.so.crud.mappers.LibroMapper;
import com.so.crud.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroMapper libroMapper;

    @Override
    public Libro crearLibro(Libro libro) {
        return libroMapper.guardar(libro);
    }

    @Override
    public List<Libro> obtenerTodos() {
        return libroMapper.obtenerTodos();
    }

    @Override
    public Libro obtenerPorId(Long id) {
        return libroMapper.obtenerPorId(id);
    }

    @Override
    public List<Libro> buscarPorNombre(String nombre) {
        return libroMapper.buscarPorNombre(nombre);
    }

    @Override
    public Libro actualizarLibro(Long id, Libro libro) {
        return libroMapper.actualizar(id, libro);
    }

    @Override
    public void eliminarLibro(Long id) {
        libroMapper.eliminar(id);
    }

    @Override
    public ResponseEntity<FileSystemResource> generarReportePorGenero(String genero) {
        File directorio = new File("src/main/resources/archivos");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        File archivo = new File(directorio, "reporte_genero.txt");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("Reporte de libros del genero " + genero + "\n\n");
            List<Libro> libros = libroMapper.obtenerTodos().stream()
                    .filter(libro -> libro.getGenero().equalsIgnoreCase(genero))
                    .collect(Collectors.toList());
            for (Libro libro : libros) {
                writer.write(libro.getTitulo() + " - " + libro.getAutor() + " - " + libro.getAnoPublicacion() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        FileSystemResource resource = new FileSystemResource(archivo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + archivo.getName());
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
