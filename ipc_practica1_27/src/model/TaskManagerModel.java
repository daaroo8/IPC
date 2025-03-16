package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskManagerModel {
    private final ArrayList<Task> tasks;
    private final ArrayList<String> categories;
    private int lastPercentage = 50;
    private Task taskEditing = null;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TaskManagerModel() {
        tasks = new ArrayList<>();
        categories = new ArrayList<>(Arrays.asList("Seleccionar opción", "Escuela", "Trabajo", "Personal"));
        // crear un arraylist a partir de un array

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
     * Consulta si la fecha sea valida (posterior a la actual)
     *
     * @return true: en caso de que sea válida
     * false: en caso de que no lo sea
     */
    public boolean isValidDate(LocalDate date) {
        return date != null && date.isAfter(LocalDate.now());
    }

    public boolean isValidCategory(String category) {
        // TODO: quitar el magic string
        return category != null && !category.equals("Seleccionar opción");
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
}
