package com.josealfredo.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josealfredo.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{

}
