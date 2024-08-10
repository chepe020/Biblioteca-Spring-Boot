package com.josealfredo.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josealfredo.webapp.biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo,Long>{

}
