package com.josealfredo.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josealfredo.webapp.biblioteca.model.Cliente;
import com.josealfredo.webapp.biblioteca.service.ClienteService;
import com.josealfredo.webapp.biblioteca.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.Setter;


@Component
public class ForClienteController implements Initializable{

    @FXML
    Button btnCancelar,btnGuardar;

    @FXML
    TextField tfDpi,tfNombre,tfApellido,tfTelefono,tfDireccion,tfNit,tfBuscar;

    @Setter
    private Main stage;

    @Setter
    private int op;

    @Autowired
    ClienteService clienteService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {

    }
 
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuClienteTemplates();
        }if(event.getSource() == btnGuardar){
            if(op == 1){
                agregarClientes();
                stage.menuClienteTemplates();
            }else if(op == 2){
                editarClientes();
                stage.menuClienteTemplates();
                
            }
        }
    }    

    public void agregarClientes(){
        Cliente cliente = new Cliente();
        cliente.setDpi(Long.parseLong(tfDpi.getText()));
        cliente.setNombre(tfNombre.getText());
        cliente.setApellido(tfApellido.getText());
        cliente.setTelefono(tfTelefono.getText());
        cliente.setDireccion(tfDireccion.getText());
        cliente.setNit(tfNit.getText());
        clienteService.guardarCliente(cliente);

    }

    public void editarClientes(){
        Cliente cliente = clienteService.buscarClienteporId(Long.parseLong(tfDpi.getText()));
        cliente.setDpi(Long.parseLong(tfDpi.getText()));
        cliente.setNombre(tfNombre.getText());
        cliente.setApellido(tfApellido.getText());
        cliente.setTelefono(tfTelefono.getText());
        cliente.setDireccion(tfDireccion.getText());
        cliente.setNit(tfNit.getText());
        clienteService.guardarCliente(cliente);
    }

    public void cargarForEditar(Cliente cliente){

        tfDpi.setText(Long.toString(cliente.getDpi()));
        tfNombre.setText(cliente.getNombre());
        tfApellido.setText(cliente.getApellido());
        tfTelefono.setText(cliente.getTelefono());
        tfDireccion.setText(cliente.getDireccion());
        tfNit.setText(cliente.getNit());
        
    }

}
