package com.josealfredo.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josealfredo.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{
    
}