package com.josealfredo.webapp.biblioteca.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.josealfredo.webapp.biblioteca.BibliotecaApplication;
import com.josealfredo.webapp.biblioteca.controller.FXController.IndexController;
import com.josealfredo.webapp.biblioteca.controller.FXController.MenuCategoriaController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
    private ConfigurableApplicationContext applicationContext;
    private Stage stage;
    private Scene scene;

    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(BibliotecaApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Biblioteca Spring");
        indexView();
        stage.show();
    }

    public Initializable switchScene(String fxmlName , int width, int height) throws IOException{
        Initializable resultado = null;
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(applicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" +fxmlName));

        scene = new Scene((AnchorPane) loader.load(archivo), width, height);
        stage.setScene(scene);
        stage.sizeToScene();

        resultado = (Initializable)loader.getController();
        return resultado;
    }


    public void indexView(){
        try {
            IndexController indexView = (IndexController)switchScene("index.fxml", 1200, 750);
            indexView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menuCategoriaTemplates(){
        try {
            MenuCategoriaController menuCategoriaTemplates = (MenuCategoriaController)switchScene("MenuCategorias.fxml",1000,650);
            menuCategoriaTemplates.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
