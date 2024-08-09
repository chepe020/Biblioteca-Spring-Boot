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

import com.josealfredo.webapp.biblioteca.model.Cliente;
import com.josealfredo.webapp.biblioteca.service.ClienteService;




@Controller
@RestController
@RequestMapping(value = "")
public class ClienteController {

    @Autowired
    ClienteService clienteService;
    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return clienteService.listaClientes();
    }

    @GetMapping("/cliente")
    public ResponseEntity<Cliente> buscarCliente(@RequestParam long dpi){
        try {
            return ResponseEntity.ok(clienteService.buscarClienteporId(dpi));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<Map<String,String>> agregarClientes(@RequestBody Cliente cliente){
        Map<String, String> response = new HashMap<>();

        try {
            clienteService.guardarCliente(cliente);
            response.put("message","Se agregar Correctamente Cliente");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error","No se pudo agregar Correctamente");

            return ResponseEntity.badRequest().body(response);
        }
    }


    @PutMapping("/cliente")
    public ResponseEntity<Map<String, String>> editarClientes(@RequestParam Long dpi,@RequestBody Cliente clienteNue){
        Map<String,String> response = new HashMap<>();
        try {
            Cliente clienteVie = clienteService.buscarClienteporId(dpi);
            clienteVie.setNombre(clienteNue.getNombre());
            clienteVie.setApellido(clienteNue.getApellido()); 
            clienteVie.setTelefono(clienteNue.getTelefono()); 
            clienteVie.setDireccion(clienteNue.getDireccion()); 
            clienteVie.setNit(clienteNue.getNit()); 
            clienteService.guardarCliente(clienteVie);
            response.put("message", "Cliente se ha editado con Exito ");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Cliente no se pudo editadar");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @DeleteMapping("/cliente")
    public ResponseEntity<Map<String, String>> eliminarCliente(@RequestParam Long dpi){
        Map<String,String> response = new HashMap<>();

        try{
            Cliente cliente = clienteService.buscarClienteporId(dpi);
            clienteService.eliminarCliente(cliente);
            response.put("message", "Cliente Eliminado con exito");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message", "Cliente no se pudo Eliminar ");
            return ResponseEntity.badRequest().body(response);

        }
    }

}
