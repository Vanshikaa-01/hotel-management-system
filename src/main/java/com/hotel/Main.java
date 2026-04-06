
package com.hotel;

import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
            Objects.requireNonNull(getClass().getResource("/ui.fxml"), "Cannot find /ui.fxml")
        );
        Parent root = loader.load();

        Scene scene = new Scene(root, 860, 650);
        scene.getStylesheets().add(
            Objects.requireNonNull(getClass().getResource("/style.css"), "Cannot find /style.css")
                   .toExternalForm()
        );

        stage.setTitle("Hotel Management System");
        stage.setMinWidth(720);
        stage.setMinHeight(520);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); 
    }
}
