package main;

import model.TaskManagerModel;
import view.ViewManager;

public class Main {
    private static ViewManager manager;
    private static TaskManagerModel model;

    public static ViewManager getViewManager() {
        return manager;
    }

    public static TaskManagerModel getTaskManagerModel() {
        return model;
    }

    public static void main(String[] args) {
        manager = new ViewManager();
        model = new TaskManagerModel();
    }
}
