package de.kibr.ega.gui.component;

import javax.swing.*;
import java.util.ResourceBundle;

public class MainFrame extends JFrame {
    private ResourceBundle resourceBundle;

    public MainFrame() {
        resourceBundle = ResourceBundle.getBundle("labels");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setTitle(resourceBundle.getString("application.title"));
    }
}
