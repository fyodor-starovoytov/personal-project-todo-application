package net.star;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        onLoad();

        taskAddField.requestFocus();

        taskAddField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) addTask();
        });

        addButton.setOnAction(event -> addTask());
    }

    private void addTask() {
        String taskText = taskAddField.getText().trim();
        if (taskText.isEmpty() || taskText.equalsIgnoreCase("add task")) {
            taskAddField.requestFocus();
            return;
        }

        Task currentTask = new Task(taskText);

        createTaskRow(currentTask);

        taskAddField.clear();
        taskAddField.requestFocus();
    }
    /**
     * Calls loads previous session saved in user_data.json.
     * If such is not empty, inputs all instances into {@link incompleteTasks} and {@link completeTasks} corresponding
     * the value of {@link Task#getCompletion()}.
     * */
    private void onLoad() {
        Task[] tasks = JsonReader.loadPreviousSession();
        if (tasks == null) {
            return;
        }

        for (Task task : tasks) {
            if (task == null || task.getTaskName().strip().isBlank()) {
                continue;
            }
            Task.tasks.add(task);
            createTaskRow(task);
        }
    }


    /**
     * Adds task on {@link #addButton} or {@link taskAddField} action.
     * Listener moves tasks between {@link incompleteTasks} and {@link completeTasks} corresponding their
     * isSelected value.
     * */
    private void createTaskRow(Task task) {
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(task.getCompletion());

        Label label = new Label(task.getTaskName().strip());
        HBox row = new HBox(10, checkBox, label);

        checkBox.selectedProperty().addListener((observable, oldValue, isSelected) -> {
            if (isSelected) {
                incompleteTasks.getChildren().remove(row);
                completeTasks.getChildren().add(row);
                task.setCompleted();
            } else {
                completeTasks.getChildren().remove(row);
                incompleteTasks.getChildren().add(row);
                task.cancelCompletion();
            }
        });

        if (task.getCompletion()) {
            completeTasks.getChildren().add(row);
        } else {
            incompleteTasks.getChildren().add(row);
        }
    }
}