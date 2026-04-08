package org.example.proyectointermodulardistribucionesbarquero.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/proyectointermodulardistribucionesbarquero/main-view.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Distribuciones Barquero");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}