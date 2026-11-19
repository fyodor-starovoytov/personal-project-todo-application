package net.star;

import java.io.IOException;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.setTitle("MyApp");
        stage.show();
    }

    @Override
    public void stop() throws Exception {

        IO.println("Saving: " + Task.tasks.stream().map(Task::getTaskName).toList());
        JsonReader.writeFile(Task.tasks.toArray(new Task[0]));

        super.stop();
    }
}
