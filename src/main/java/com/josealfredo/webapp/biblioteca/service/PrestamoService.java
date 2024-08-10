package com.josealfredo.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josealfredo.webapp.biblioteca.model.Prestamo;
import com.josealfredo.webapp.biblioteca.repository.PrestamoRepository;

@Service
public class PrestamoService implements IPrestamoService{
   
    @Autowired
    private PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
       return prestamoRepository.findAll();
    }

    @Override
    public Prestamo buscarPrestamosPorId(Long id) {
       return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Prestamo guardarPrestamos(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminarPrestamos(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);
    }

}
