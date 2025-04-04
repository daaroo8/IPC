package model;

import java.util.ArrayList;

public class TaskList {

    public String name;
    public ArrayList<Task> tasks;

    public TaskList(String name) {
        if (name == null)
            throw new IllegalArgumentException("name cannot be null");
        this.name = name;
        tasks = new ArrayList<>();
    }

    public TaskList(String name, ArrayList<Task> tasks) {
        if (name == null)
            throw new IllegalArgumentException("name cannot be null");
        if (tasks == null)
            throw new IllegalArgumentException("tasks cannot be null");

        this.name = name;
        this.tasks = tasks;
    }

    public void addTaskToTaskList(Task task) {
        tasks.add(task);
    }

    public void removeTaskFromTaskList(Task task) {
        if (task == null)
            throw new IllegalArgumentException("task cannot be null");

        tasks.remove(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTaskFromTaskList(int index) {

        return tasks.get(index);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
