package com.josealfredo.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josealfredo.webapp.biblioteca.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
    
}
