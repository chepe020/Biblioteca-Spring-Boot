package com.josealfredo.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josealfredo.webapp.biblioteca.model.Libro;


public interface LibroRepository extends JpaRepository<Libro, Long>{

}
