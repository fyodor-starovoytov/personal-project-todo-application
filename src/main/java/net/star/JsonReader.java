package net.star;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class JsonReader {

    /**
     * Looks for userdata.json file, if there is no such, creates an empty one.
     * Calls {@link readFile()}.
     * */
    public static Task[] loadPreviousSession(){
        boolean flag = new File("src/main/resources/net/star/tasks/user_data.json").exists();
        if (!flag){
            writeFile(null);
        }

        return readFile();
    }

    /**
     * Reads the user_data.json file, returning an array of {@link Task}
     * */
    public static Task[] readFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/net/star/tasks/user_data.json")){};

            Gson gsonObject = new Gson();
            Task[] tasks = gsonObject.fromJson(
                    reader,
                    Task[].class
            );

            reader.close();
            return tasks;
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(@Nullable Task[] tasks) {
        try (FileWriter writer = new FileWriter("src/main/resources/net/star/tasks/user_data.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            if (tasks == null){
                return;
            }
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
