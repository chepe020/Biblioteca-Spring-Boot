package com.josealfredo.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josealfredo.webapp.biblioteca.model.Empleado;
import com.josealfredo.webapp.biblioteca.service.EmpleadoService;

@Controller
@RestController
@RequestMapping(value = "")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> listarEmpleado() {
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/empleado")
    public ResponseEntity<Empleado> buscarEmpleado(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(empleadoService.buscarEmpleadoporid(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/empleado")
    public ResponseEntity<Map<String, String>> agregarEmpleado(@RequestBody Empleado empleado) {
        Map<String, String> response = new HashMap<>();

        try {
            if(empleadoService.guardarEmpleados(empleado)){
                response.put("message", "Se agregar Correctamente Empleado");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "DPI duplicado");
                return ResponseEntity.badRequest().body(response);

            }
        } catch (Exception e) {
            response.put("error", "No se pudo agregar Correctamente Empleado");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PutMapping("/empleado")
    public ResponseEntity<Map<String, String>>editarEmpleado(@RequestParam Long id,@RequestBody Empleado empleadoNue){
        Map<String,String> response = new HashMap<>();

        try {
            Empleado empleadovie = empleadoService.buscarEmpleadoporid(id);
            empleadovie.setNombre(empleadoNue.getNombre());
            empleadovie.setApellido(empleadoNue.getApellido());
            empleadovie.setTelefono(empleadoNue.getTelefono());
            empleadovie.setDireccion(empleadoNue.getDireccion());
            empleadovie.setDpi(empleadoNue.getDpi());
            empleadoService.guardarEmpleados(empleadovie);
            response.put("message", "Empleado se ha editado con Exito ");
            return ResponseEntity.ok(response);
        } catch (Exception e) {

            response.put("message", "Empleado no se pudo editadar");
            return ResponseEntity.badRequest().body(response);       
        }
    }

    @DeleteMapping("/empleado")
    public ResponseEntity<Map<String, String>>eliminarEmpleado(@RequestParam long id){
        Map<String,String> response = new HashMap<>();

        try {
            Empleado empleado = empleadoService.buscarEmpleadoporid(id);
            empleadoService.eliminarEmpleados(empleado);
            response.put("message", "Empleado Eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Empleado no se pudo Eliminar ");
            return ResponseEntity.badRequest().body(response);
        }


    }


}
