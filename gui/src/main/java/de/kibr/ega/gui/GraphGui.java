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
        ResourceBundle bundle = ResourceBundle.getBundle("labels");
        Parent root = FXMLLoader.load(getClass().getResource("/layout.fxml"), bundle);
        stage.setTitle(bundle.getString("application.title"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
