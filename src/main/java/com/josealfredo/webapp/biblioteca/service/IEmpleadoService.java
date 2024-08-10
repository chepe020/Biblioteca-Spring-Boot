package com.josealfredo.webapp.biblioteca.service;

import java.util.List;

import com.josealfredo.webapp.biblioteca.model.Empleado;

public interface IEmpleadoService {
   
    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleadoporid(Long id);

    public Boolean guardarEmpleados(Empleado empleado);
    
    public void eliminarEmpleados(Empleado empleado);

    public Boolean verificarDpiDublicado(Empleado empleadoNuevo);
}
