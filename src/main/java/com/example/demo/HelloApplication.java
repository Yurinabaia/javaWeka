package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1148, 636);
        stage.getIcons().add(new Image("file:ico.png"));
        stage.setTitle("Algoritmos do WEKA!");
        stage.setScene(scene);

        stage.show();
        //Bloquear Aumento De Tela
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}