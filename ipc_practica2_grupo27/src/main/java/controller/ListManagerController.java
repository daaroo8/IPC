package controller;

import main.Main;
import model.TaskList;
import model.TaskManagerModel;
import view.ListManagerView;

import java.util.ArrayList;

public class ListManagerController {
    private final ListManagerView view;
    private final TaskManagerModel model;

    public ListManagerController(ListManagerView view) {
        this.view = view;
        this.model = Main.getTaskManagerModel();
    }

    public void handleAddListItem() {
        if (view.getNewTaskListTextValue() == null || view.getNewTaskListTextValue().isEmpty())
            return;

        model.addTaskList(new TaskList(view.getNewTaskListTextValue()));

        view.closeAddListDialog();
        view.updateTasksList(model.getTaskLists());
        view.restartAddTaskListInput();
    }

    public ArrayList<TaskList> getTaskLists() {
        return model.getTaskLists();
    }
}
