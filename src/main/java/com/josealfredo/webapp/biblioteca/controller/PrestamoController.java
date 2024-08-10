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

import com.josealfredo.webapp.biblioteca.model.Prestamo;
import com.josealfredo.webapp.biblioteca.service.PrestamoService;

@Controller
@RestController
@RequestMapping(value = "")
public class PrestamoController {
    @Autowired
    PrestamoService prestamoService;

    @GetMapping("/prestamos")
    public ResponseEntity<List<Prestamo>> listarPrestamos() {
        try {
            return ResponseEntity.ok(prestamoService.listarPrestamos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/prestamo")
    public ResponseEntity<Prestamo> buscarPrestamoporId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(prestamoService.buscarPrestamosPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/prestamo")
    public ResponseEntity<Map<String, String>> agregarPrestamos(@RequestBody Prestamo prestamo) {
        Map<String, String> response = new HashMap<>();

        try {
            prestamoService.guardarPrestamos(prestamo);
            response.put("message", "Se agrego Corectamente Prestamos");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "error");
            response.put("err", "Hubo un error al Agregar Prestamos");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/prestamo")
    public ResponseEntity<Map<String,String>> editarPrestamos(@RequestParam Long id,@RequestBody Prestamo prestamoNue){
        Map<String,String> response = new HashMap<>();
        try {
            Prestamo prestamoVie = prestamoService.buscarPrestamosPorId(id);
            prestamoVie.setFechaDePrestamo(prestamoNue.getFechaDeDevolucion());
            prestamoVie.setFechaDeDevolucion(prestamoNue.getFechaDeDevolucion());
            prestamoVie.setVigencia(prestamoNue.getVigencia());
            prestamoVie.setEmpleado(prestamoNue.getEmpleado());
            prestamoVie.setCliente(prestamoNue.getCliente());
            prestamoVie.setLibros(prestamoNue.getLibros());
            prestamoService.guardarPrestamos(prestamoVie);
            response.put("message", " Prestamos se ha Editado con Exito --- :)");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "ERROR");
            response.put("err", "Prestamos no se puedo Editar --- :(");
            return ResponseEntity.badRequest().body(response);
        }        
    }

    @DeleteMapping("/prestamo")
    public ResponseEntity<Map<String,String>> eliminarPrestamos(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();

        try {
            Prestamo prestamo = prestamoService.buscarPrestamosPorId(id);
            prestamoService.eliminarPrestamos(prestamo);
            response.put("message", "Prestamo Elimina con Exito --- :) ");
            return ResponseEntity.ok(response);
        } catch (Exception e) {

            response.put("message", "ERROR");
            response.put("err", "Prestamo no se pudo Eliminar --- :( ");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
