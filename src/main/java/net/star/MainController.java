package net.star;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private VBox incompleteTasks;
    @FXML
    private VBox completeTasks;
    @FXML
    private TextField taskAddField;
    @FXML
    private Button addButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taskAddField.requestFocus();

        taskAddField.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) moveTask();
        });

        addButton.setOnAction(event -> {
            moveTask();
        });

        for (Node node : incompleteTasks.getChildren()) {
            if (node instanceof HBox hbox) {
                for (Node child : hbox.getChildren()) {
                    if (child instanceof CheckBox checkBox && checkBox.isSelected()) {
                        incompleteTasks.getChildren().remove(checkBox);
                        completeTasks.getChildren().add(checkBox);
                    }
                }
            }
        }
    }

    private void moveTask() {
        String taskText = taskAddField.getText().trim();
        if (taskText.isEmpty() || taskText.equalsIgnoreCase("add task")) {
            taskAddField.requestFocus();
            return;
        }

        CheckBox checkBox = new CheckBox();
        Label task = new Label(taskText);
        HBox row = new HBox(10, checkBox, task);

        checkBox.selectedProperty().addListener((observable, oldValue, isSelected) -> {
            if (isSelected) {
                incompleteTasks.getChildren().remove(row);
                completeTasks.getChildren().add(row);
            } else {
                completeTasks.getChildren().remove(row);
                incompleteTasks.getChildren().add(row);
            }
        });

        incompleteTasks.getChildren().add(row);

        taskAddField.clear();
        taskAddField.requestFocus();
    }
}