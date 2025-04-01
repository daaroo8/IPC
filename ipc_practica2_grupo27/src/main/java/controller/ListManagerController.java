package controller;

import model.ListManagerModel;
import view.ListManagerView;

public class ListManagerController {
    private final ListManagerView view;
    private final ListManagerModel model;

    public ListManagerController(ListManagerView view) {
        this.view = view;
        this.model = new ListManagerModel();
    }

    public void handleAddListItem() {
        if (view.getNewTaskListTextValue() == null || view.getNewTaskListTextValue().isEmpty())
            return;

        model.addTaskList(view.getNewTaskListTextValue());

        view.closeAddListDialog();
        view.updateTasksList(model.getTaskLists());
        view.restartAddTaskListInput();
    }
}
