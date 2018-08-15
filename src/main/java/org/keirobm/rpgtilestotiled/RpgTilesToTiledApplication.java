package org.keirobm.rpgtilestotiled;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.keirobm.rpgtilestotiled.controllers.MainController;

import java.io.IOException;

public class RpgTilesToTiledApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/main.fxml"));
        final Parent root = fxmlLoader.load();

        final MainController mainController = fxmlLoader.getController();
        mainController.passStage(primaryStage);

        primaryStage.setTitle("RPG Tiles To Tiled Map Editor");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
