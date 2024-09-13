package com.josealfredo.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.josealfredo.webapp.biblioteca.model.Categoria;
import com.josealfredo.webapp.biblioteca.service.CategoriaService;
import com.josealfredo.webapp.biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;


@Component
public class MenuCategoriaController implements Initializable{
    
    @FXML
    Button btnRegresar,btnGuardar,btnVaciar,btnEliminar,btnBuscar;
    @FXML
    TableView tblCategorias;
    @FXML
    TableColumn cold,colNombreCategorias;
    @FXML
    TextField tfId,tfNombreCategorias,tfBuscar;


    @Setter
    private Main stage;

    @Autowired
    CategoriaService categoriaService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.indexView();
        }else if(event.getSource() == btnGuardar){
            if(tfId.getText().isBlank()){
                agregarCategorias();
            }else{
                editarCategorias();
            }
        }else if(event.getSource() == btnVaciar){
            limpiarFor();
        }else if(event.getSource() == btnEliminar){
            eliminarCategoria();
        }else if(event.getSource() == btnBuscar){
            buscarCategoria();
        } 
    }

    public void cargarDatos(){
        tblCategorias.setItems(listarCategoria());
        cold.setCellValueFactory(new PropertyValueFactory<Categoria, Long>("id"));//modelo id
        colNombreCategorias.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombreCategoria"));//modelo nombreCategoria
        
    }

    public void cargarFormEditar(){
        Categoria categoria = (Categoria)tblCategorias.getSelectionModel().getSelectedItem();
        if(categoria != null){
            tfId.setText(Long.toString(categoria.getId()));
            tfNombreCategorias.setText(categoria.getNombreCategoria());
        }
    }

    public void limpiarFor(){
        tfId.clear();
        tfNombreCategorias.clear();
    }

    public ObservableList<Categoria> listarCategoria(){
        return FXCollections.observableList(categoriaService.listarCategorias());
    }

    public void agregarCategorias(){
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(tfNombreCategorias.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void editarCategorias(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoria.setNombreCategoria(tfNombreCategorias.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void eliminarCategoria(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoriaService.eliminarCategoria(categoria);
        cargarDatos();
    }

    public void buscarCategoria(){
        Long id = Long.parseLong(tfBuscar.getText()); 
        Categoria categoria = categoriaService.buscarCategoriaPorId(id); 

        


    }
}





