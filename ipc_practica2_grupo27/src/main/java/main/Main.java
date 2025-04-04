package main;

import model.TaskManagerModel;
import view.ViewManager;

public class Main {
    private static ViewManager manager;
    private static TaskManagerModel model;
    private static ViewManager principalMenu;

    public static ViewManager getPrincipalMenuView() { return principalMenu; }

    public static ViewManager getViewManager() {
        return manager;
    }

    public static TaskManagerModel getTaskManagerModel() {
        return model;
    }

    public static void main(String[] args) {
        manager = new ViewManager();
        model = new TaskManagerModel();
        principalMenu = new ViewManager();
    }
}
