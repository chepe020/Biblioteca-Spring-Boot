package com.josealfredo.webapp.biblioteca.service;

import java.util.List;

import com.josealfredo.webapp.biblioteca.model.Empleado;

public interface IEmpleadoService {
   
    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleadoporid(Long id);

    public void guardarEmpleados(Empleado empleado);
    
    public void eliminarEmpleados(Empleado empleado);
}
