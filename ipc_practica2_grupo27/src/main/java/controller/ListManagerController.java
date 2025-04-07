package controller;

import main.Main;
import model.Task;
import model.TaskList;
import model.TaskManagerModel;
import view.ListManagerView;

import java.lang.reflect.Array;
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

    /**
     * Maneja el evento de escritura en el campo de búsqueda.
     */
    public void handleKeyTypedSearchInputEvent(char newCharacter) {
        String newTextFilterValue = view.getSearchTextFieldValue();

        if (Character.isLetter(newCharacter) || Character.isDigit(newCharacter)) {
            newTextFilterValue += newCharacter;
        }

        model.filterLists(newTextFilterValue);
        view.updateTasksList(model.getTaskListsFiltered());
    }

    public void handleDeleteList() {
        TaskList taskListToDelete = view.getSelectedTaskList();

        if (taskListToDelete == null) return;

        model.deleteTaskList(taskListToDelete);
        view.updateTasksList(model.getTaskLists());
    }

    public void handleSelectListEvent() {
        TaskList taskListToSelect = view.getSelectedTaskList();

        if (taskListToSelect == null) return;

        view.updateTaskListInformation(taskListToSelect);
    }

    public void handleToCompleteTaskEvent(Task task) {
        task.setPercentage(100);

        TaskList taskList = model.getTaskListByName(task.getSubtask());

        ArrayList<Task> pendingTasks = taskList.getPendingTasks();
        ArrayList<Task> completedTasks = taskList.getCompletedTasks();

        view.setPendingCount(pendingTasks.size());
        view.setCompletedCount(completedTasks.size());

        view.updatePendingPanel(pendingTasks);
        view.updateCompletedPanel(completedTasks);
    }
}
