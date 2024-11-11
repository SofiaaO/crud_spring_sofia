package com.so.crud.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Libro {
    private Long id;
    private String titulo;
    private String autor;
    private String genero;
    private int anoPublicacion;
}