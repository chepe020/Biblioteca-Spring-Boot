package com.josealfredo.webapp.biblioteca.service;

import java.util.List;

import com.josealfredo.webapp.biblioteca.model.Categoria;

public interface ICategoriaService {
    
    public List<Categoria> listarCategorias();
    
    public Categoria buscarCategoriaPorId(Long id);

    public Categoria guardarCategoria(Categoria categoria);

    public void eliminarCategoria(Categoria categoria);
}
