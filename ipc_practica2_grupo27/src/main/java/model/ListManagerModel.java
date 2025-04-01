package model;

import java.util.ArrayList;
import java.util.Arrays;

public class ListManagerModel {

    private final ArrayList<String> taskLists;

    public ListManagerModel() {
        taskLists = new ArrayList<>(Arrays.asList("IPC"));
    }

    public void addTaskList(String task) {
        taskLists.add(task);
    }

    public ArrayList<String> getTaskLists() {
        return taskLists;
    }
}
