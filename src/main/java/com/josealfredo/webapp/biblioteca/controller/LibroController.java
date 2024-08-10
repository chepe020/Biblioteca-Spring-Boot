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

import com.josealfredo.webapp.biblioteca.model.Libro;
import com.josealfredo.webapp.biblioteca.service.LibroService;

@Controller
@RestController
@RequestMapping(value = "")
public class LibroController {

    @Autowired
    LibroService libroService;

    @GetMapping("/libros")
    public ResponseEntity <List<Libro>>listarLibros(){
        try {
            return ResponseEntity.ok(libroService.listarLibros());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/libro")
    public ResponseEntity<Libro>buscarLibros(@RequestParam Long id){
        try {
            return ResponseEntity.ok(libroService.buscarLibro(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/libro")
    public ResponseEntity<Map<String,String>>agregarLibros(@RequestBody Libro libro){
        Map <String,String> response = new HashMap<>();
        try {
            libroService.guardarLibro(libro);
            response.put("message", "Libro creado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el Libro");
            return ResponseEntity.badRequest().body(response);

        }
    }

    @PutMapping("/libro")
    public ResponseEntity<Map<String,String>>editarLibros(@RequestParam Long id,@RequestBody Libro libroNue){
        Map<String,String> response = new HashMap<>();

        try {
            Libro libroVie = libroService.buscarLibro(id);
            libroVie.setIsbn(libroNue.getIsbn());
            libroVie.setNombre(libroNue.getNombre());
            libroVie.setSinopsis(libroNue.getSinopsis());
            libroVie.setAutor(libroNue.getAutor());
            libroVie.setEditorial(libroNue.getEditorial());
            libroVie.setDisponibilidad(libroNue.getDisponibilidad());
            libroVie.setNumeroEstanteria(libroNue.getNumeroEstanteria());
            libroVie.setCluster(libroNue.getCluster());
            libroVie.setCategoria(libroNue.getCategoria());
            libroService.guardarLibro(libroVie);
            response.put("message", "Libros se ha editado con Exito ");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Libros no se pudo editadar");
            return ResponseEntity.badRequest().body(response); 
        }
    }

    @DeleteMapping("/libro")
    public ResponseEntity<Map<String,String>>eliminarLibros(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();

        try {
            Libro libro = libroService.buscarLibro(id);
            libroService.eliminarLibro(libro);
            response.put("message", "Libro Eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {

            response.put("message", "Libro no se pudo Eliminar ");
            return ResponseEntity.badRequest().body(response);
        }

    }



}
