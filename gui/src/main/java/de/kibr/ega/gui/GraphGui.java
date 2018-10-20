package de.kibr.ega.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class GraphGui extends Application {

    public static void main(String[] args) {
        Application.launch(GraphGui.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/markup/layout.fxml"));
        loader.setResources(ResourceBundle.getBundle("labels"));
        stage.setTitle(loader.getResources().getString("application.title"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
