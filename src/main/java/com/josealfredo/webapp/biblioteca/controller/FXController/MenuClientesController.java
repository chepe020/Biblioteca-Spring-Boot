package com.josealfredo.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josealfredo.webapp.biblioteca.model.Cliente;
import com.josealfredo.webapp.biblioteca.service.ClienteService;
import com.josealfredo.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;


@Component
public class MenuClientesController implements Initializable{

    @FXML
    Button btnAgregar,btnEditar,btnEliminar,btnRegresar,btnBuscar;
    @FXML
    TableView TblCliente;
    @FXML
    TableColumn colDpi,colNombre,colApellido,colTelefono,colDireccion,colNit;

    ForClienteController forClienteController;

    @Setter
    private Main stage;

    @Autowired
    ClienteService clienteService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            stage.indexView();
        } else if (event.getSource() == btnAgregar) {
            stage.formClienteTemplates(1); 
            cargarDatos();
        } else if (event.getSource() == btnEditar) {
            Cliente clienteSeleccionado = (Cliente) TblCliente.getSelectionModel().getSelectedItem();
            if (clienteSeleccionado != null) {
                forClienteController.cargarForEditar(clienteSeleccionado); 
                stage.formClienteTemplates(2); 
                cargarDatos(); 
            }
        }
    }
    
    
    public void cargarDatos(){
        TblCliente.setItems(listarCliente());
        colDpi.setCellValueFactory(new PropertyValueFactory<Cliente,Long>("dpi"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
        colNit.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nit"));


    }

    
    public ObservableList<Cliente> listarCliente(){
        return FXCollections.observableList(clienteService.listaClientes());    
    }



}
