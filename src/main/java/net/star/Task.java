package net.star;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private final String task_name;
    private Boolean completed;
    public static List<Task> tasks = new ArrayList<>();

    public Task(String taskName) {
        this.task_name = taskName;
        this.completed = false;
        tasks.add(this);
    }

    public void setCompleted(){
        completed = true;
    }
    public void cancelCompletion(){
        completed = false;
    }


    public String getTaskName() {
        return task_name;
    }

    public Boolean getCompletion() {
        return completed;
    }
}
