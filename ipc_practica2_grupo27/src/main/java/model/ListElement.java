package model;

import java.util.ArrayList;

public class ListElement {

    public String name;
    public ArrayList<Task> tasks;

    public ListElement(String name) {
        if (name == null)
            throw new IllegalArgumentException("name cannot be null");
        this.name = name;
    }

    public ListElement(String name, ArrayList<Task> tasks) {
        if (name == null)
            throw new IllegalArgumentException("name cannot be null");
        if (tasks == null)
            throw new IllegalArgumentException("tasks cannot be null");

        this.name = name;
        this.tasks = tasks;
    }

    public void addTaskToListElement(Task task) {
        if (tasks == null)
            tasks = new ArrayList<>();

        tasks.add(task);
    }

    public void removeTaskFromListElement(Task task) {
        if (tasks == null)
            throw new IllegalArgumentException("tasks cannot be null");

        tasks.remove(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTaskFromListElement(int index) {

        return tasks.get(index);
    }

    @Override
    public String toString() {
        return name;
    }
}
