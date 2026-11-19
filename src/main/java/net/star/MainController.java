package net.star;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label savedTask;
    @FXML
    private TextField addTask;
    @FXML
    private Button addButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addTask.requestFocus();

        addButton.setOnAction(event -> {
            String taskText = addTask.getText().trim();
            if (taskText.isEmpty()) return;

            String currentText = savedTask.getText();
            if (currentText.equals("Your tasks will appear here.")) {
                savedTask.setText( " - " + taskText);
            } else {
                savedTask.setText(currentText + "\n - " + taskText);
            }

            addTask.clear();
        });
    }
}
