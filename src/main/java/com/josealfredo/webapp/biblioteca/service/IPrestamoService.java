package com.josealfredo.webapp.biblioteca.service;

import java.util.List;

import com.josealfredo.webapp.biblioteca.model.Prestamo;

public interface IPrestamoService {

    public List<Prestamo> listarPrestamos();

    public Prestamo buscarPrestamosPorId(Long id);

    public Prestamo guardarPrestamos(Prestamo prestamo);

    public void eliminarPrestamos(Prestamo prestamo);
}
