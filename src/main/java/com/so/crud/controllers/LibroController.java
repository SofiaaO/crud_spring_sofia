package com.so.crud.controllers;

import com.so.crud.domain.Libro;
import com.so.crud.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> obtenerTodos() {
        List<Libro> libros = libroService.obtenerTodos();  // Llama al servicio para obtener todos los libros
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerPorId(@PathVariable Long id) {
        Libro libro = libroService.obtenerPorId(id);  // Llama al servicio para obtener el libro por ID
        return libro != null ? ResponseEntity.ok(libro) : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar/{titulo}")
    public ResponseEntity<List<Libro>> buscarPorNombre(@PathVariable String titulo) {
        List<Libro> libros = libroService.buscarPorNombre(titulo);  // Llama al servicio para buscar libros por nombre
        return ResponseEntity.ok(libros);
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody final Libro libro) {
        Libro creado = libroService.crearLibro(libro);  // Llama al servicio para crear un libro
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody final Libro libro) {
        Libro actualizado = libroService.actualizarLibro(id, libro);  // Llama al servicio para actualizar un libro
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);  // Llama al servicio para eliminar un libro
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/generar-reporte")
    public ResponseEntity<FileSystemResource> generarReportePorGenero(@RequestParam String genero) {
        System.out.println("Genero recibido: " + genero);
        if (genero == null || genero.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return libroService.generarReportePorGenero(genero);  // Llama al servicio para generar un reporte
    }
}

