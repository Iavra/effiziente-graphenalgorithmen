package de.kibr.ega.gui.controller;

import com.google.common.eventbus.EventBus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML GraphController graphController;

    private EventBus eventBus = new EventBus();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventBus.register(graphController);
    }
}
