package model;

import view.TaskManagerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskManagerModel {
    private final ArrayList<Task> tasks;
    private final ArrayList<Task> tasksFiltered;
    private final ArrayList<String> categories;
    private int lastPercentage = 50;
    private Task taskEditing = null;
    public static final String FORMAT_DATE_TIME_FORMATTER = "dd/MM/yyyy";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TaskManagerModel() {
        tasks = new ArrayList<>();
        tasksFiltered = new ArrayList<>();
        categories = new ArrayList<>(Arrays.asList("Escuela", "Trabajo", "Personal"));
    }

    public int getLastPercentage() {
        return lastPercentage;
    }

    public void setLastPercentage(int lastPercentage) {
        this.lastPercentage = lastPercentage;
    }


    public void addCategory(String category) {
        categories.add(category);
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public Task getTaskEditing() {
        return taskEditing;
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void setTaskEditing(Task taskEditing) {
        this.taskEditing = taskEditing;
    }

    public boolean isValidCategoryToAdd(String category) {
        return !categories.contains(category) && category.length() <= 10;
    }

    public boolean isValidName(String name) {
        return !name.isEmpty() && name.length() <= 10;
    }

    public boolean isValidDescription(String description) {
        return description.length() <= 100;
    }

    /**
     * Consulta si la fecha es válida (posterior a la actual)
     *
     * @return true: en caso de que sea válida
     * false: en caso de que no lo sea
     */
    public boolean isValidDate(LocalDate date) {
        return date != null && !date.isBefore(LocalDate.now());
    }

    public boolean isValidCategory(String category) {
        return category != null && !category.equals(TaskManagerView.SELECT_CATEGORY_PLACEHOLDER);
    }

    public boolean isValidTask(Task task) {
        for (Task t : tasks) {
            if (t.getName().equals(task.getName())) {
                return false;
            }
        }

        return true;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void filterTasks(String searchText) {
        tasksFiltered.clear();
        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(searchText.toLowerCase()) || task.getDescription().toLowerCase().contains(searchText.toLowerCase()))
                tasksFiltered.add(task);
        }
    }

    public ArrayList<Task> getTasksFiltered() {
        return tasksFiltered;
    }
}
