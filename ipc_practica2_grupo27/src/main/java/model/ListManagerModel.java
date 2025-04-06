package model;

import java.util.ArrayList;
import java.util.List;

public class ListManagerModel {

    private final ArrayList<TaskList> taskLists;

    public ListManagerModel() {
        taskLists = new ArrayList<>(List.of(new TaskList("IPC")));
    }

    public void addTaskList(TaskList taskList) {
        taskLists.add(taskList);
    }

    public ArrayList<TaskList> getTaskLists() {
        return taskLists;
    }
}
