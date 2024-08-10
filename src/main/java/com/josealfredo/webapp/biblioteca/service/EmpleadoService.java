package com.josealfredo.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josealfredo.webapp.biblioteca.repository.EmpleadoRepository;
import com.josealfredo.webapp.biblioteca.model.Empleado;

@Service
public class EmpleadoService implements IEmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado buscarEmpleadoporid(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean guardarEmpleados(Empleado empleado) {
        if(!verificarDpiDublicado(empleado)){
            empleadoRepository.save(empleado);
            return true; 
        }else{
            return false;
        }
    }

    @Override
    public void eliminarEmpleados(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }

    @Override
    public Boolean verificarDpiDublicado(Empleado empleadoNuevo) {
        List<Empleado> empleados = listarEmpleados();
        Boolean flag = false;
        for(Empleado empleado : empleados){
            if(empleado.getDpi().equals(empleadoNuevo.getDpi())){
                flag = true;
            }
        }
        return flag;
    }

  

}
