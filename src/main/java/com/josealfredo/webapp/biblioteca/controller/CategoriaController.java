package com.josealfredo.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josealfredo.webapp.biblioteca.model.Categoria;
import com.josealfredo.webapp.biblioteca.service.CategoriaService;

@Controller
@RestController
@RequestMapping(value = "categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;
    @GetMapping("/")
    public List<Categoria> listarCategorias() {  //Lista de Categoria

        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaporid(@PathVariable Long id){
        try{
            return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id)); // Buscar Categoria
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> agregarCategoria(@RequestBody Categoria categoria) {
        Map<String, String> response = new HashMap<>();

        try {
            categoriaService.guardarCategoria(categoria);
            response.put("message", "Se agrego Corecatmente");              // Agregar Categoria

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "Hubo un error al Agregar");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarCategorias(@PathVariable Long id, @RequestBody Categoria categoriaNue){
        Map<String,String> response = new HashMap<>();
        try{
            Categoria categoriaVie = categoriaService.buscarCategoriaPorId(id);
            categoriaVie.setNombreCategoria(categoriaNue.getNombreCategoria());
            categoriaService.guardarCategoria(categoriaVie);
            response.put("message", " Categoria se ha Editado con Exito --- :)");
            return ResponseEntity.ok(response);
        }catch(Exception e){                                                                        // Editar Categoria
            response.put("message", "La Categoria no se puedo Editar --- :(");
            return ResponseEntity.badRequest().body(response);
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@PathVariable Long id){
        Map<String,String> response = new HashMap<>();
        try{
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);                                      // Eliminar Categoria
            response.put("message", "Categoria Elimina con Exito --- :) ");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message", "La Categoria no se pudo Eliminar --- :( ");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
