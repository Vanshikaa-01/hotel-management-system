
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
            // FIX: null-safe resource loading — gives a clear error if path is wrong
            Objects.requireNonNull(getClass().getResource("/ui.fxml"), "Cannot find /ui.fxml")
        );
        Parent root = loader.load();

        // FIX: explicit scene size instead of relying on FXML pref sizes
        Scene scene = new Scene(root, 860, 650);
        scene.getStylesheets().add(
            Objects.requireNonNull(getClass().getResource("/style.css"), "Cannot find /style.css")
                   .toExternalForm()
        );

        stage.setTitle("Hotel Management System");
        // FIX: prevent window from being resized too small
        stage.setMinWidth(720);
        stage.setMinHeight(520);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); // FIX: was launch() — must pass args for JavaFX module system
    }
}
