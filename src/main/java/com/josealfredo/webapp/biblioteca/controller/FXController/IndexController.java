package com.josealfredo.webapp.biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.josealfredo.webapp.biblioteca.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.fxml.Initializable;
import lombok.Setter;


@Component
public class IndexController implements Initializable{

    @Setter
    private Main stage;

    @FXML
    MenuItem btnMenuCategorias;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        
    }

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnMenuCategorias){
            stage.menuCategoriaTemplates();
        }else{

        }
    }
    
}
