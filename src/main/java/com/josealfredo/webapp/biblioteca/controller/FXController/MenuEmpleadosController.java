package com.josealfredo.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josealfredo.webapp.biblioteca.service.EmpleadoService;
import com.josealfredo.webapp.biblioteca.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import lombok.Setter;


@Component
public class MenuEmpleadosController implements Initializable{

    @Setter
    private Main stage;

    @Autowired
    EmpleadoService empleadoService;

    @FXML
    Button btnAceptar,btnVaciar,btnEliminar,btnRegresar,btnBuscar;
    @FXML
    TextField tfId,tfNombre,tfApellido,tfTelefono,tfDireccion,tfDPI,tfBuscar;
    @FXML
    TableView tblEmpleados;
    @FXML
    TableColumn colId,colNombre,colApellido,colTelefono,colDireccion,colDPI;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
        @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            stage.indexView();
        }
    }    
}
