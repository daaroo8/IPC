package model;

import model.enums.Priority;
import model.enums.RangeSelections;
import view.TaskManagerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa el modelo del Gestor de tareas
 */
public class TaskManagerModel {
    private final ArrayList<TaskList> taskLists;
    private final ArrayList<TaskList> taskListsFiltered;
    private final ArrayList<Task> tasksFiltered;
    private final ArrayList<String> categories;
    private int lastPercentage = 50;
    private Task taskEditing = null;
    public static final String FORMAT_DATE_TIME_FORMATTER = "dd/MM/yyyy";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructor para inicializar el modelo de gestión de tareas.
     */
    public TaskManagerModel() {
        taskLists = initTasks();
        taskListsFiltered = new ArrayList<>();
        tasksFiltered = new ArrayList<>();
        categories = new ArrayList<>(Arrays.asList("Escuela", "Trabajo", "Personal"));
    }

    /**
     * Obtiene el último porcentaje de progreso guardado.
     *
     * @return El último porcentaje de progreso.
     */
    public int getLastPercentage() {
        return lastPercentage;
    }

    /**
     * Establece el último porcentaje de progreso.
     *
     * @param lastPercentage El último porcentaje de progreso.
     */
    public void setLastPercentage(int lastPercentage) {
        this.lastPercentage = lastPercentage;
    }

    /**
     * Agrega una nueva categoría a la lista de categorías.
     *
     * @param category La categoría a agregar.
     */
    public void addCategory(String category) {
        categories.add(category);
    }

    /**
     * Obtiene la lista de categorías disponibles.
     *
     * @return La lista de categorías.
     */
    public ArrayList<String> getCategories() {
        return categories;
    }

    /**
     * Obtiene la tarea que está siendo editada.
     *
     * @return La tarea en edición, o null si no hay ninguna tarea en edición.
     */
    public Task getTaskEditing() {
        return taskEditing;
    }

    /**
     * Elimina una tarea de la lista de tareas.
     *
     * @param task La tarea a eliminar.
     */
    public void removeTask(Task task) {
        for (TaskList taskList : taskLists) {
            if (taskList.getTasks().contains(task)) {
                taskList.removeTaskFromTaskList(task);
                return;
            }
        }
    }

    /**
     * Establece la tarea que se está editando.
     *
     * @param taskEditing La tarea a establecer como en edición.
     */
    public void setTaskEditing(Task taskEditing) {
        this.taskEditing = taskEditing;
    }

    /**
     * Verifica si una categoría es válida para agregarla.
     *
     * @param category La categoría a verificar.
     * @return true si la categoría no existe, no está vacía y no supera los 10 caracteres, de lo contrario false.
     */
    public boolean isValidCategoryToAdd(String category) {
        return !categories.contains(category) && !category.isEmpty() && category.length() <= 10;
    }

    /**
     * Verifica si el nombre de la tarea es válido.
     *
     * @param name El nombre a verificar.
     * @return true si el nombre no está vacío y tiene 10 caracteres o menos, de lo contrario false.
     */
    public boolean isValidName(String name) {
        return !name.isEmpty() && name.length() <= 10;
    }

    /**
     * Verifica si la descripción de la tarea es válida.
     *
     * @param description La descripción a verificar.
     * @return true si la descripción tiene 100 caracteres o menos, de lo contrario false.
     */
    public boolean isValidDescription(String description) {
        return description.length() <= 100;
    }

    /**
     * Verifica si la fecha proporcionada es válida.
     *
     * @param date La fecha a verificar.
     * @return true si la fecha no es nula y no es anterior a la fecha actual, de lo contrario false.
     */
    public boolean isValidDate(LocalDate date) {
        return date != null && !date.isBefore(LocalDate.now());
    }

    /**
     * Verifica si una tarea es válida para ser agregada.
     *
     * @param task La tarea a verificar.
     * @return true si no existe otra tarea con el mismo nombre, de lo contrario false.
     */
    public boolean isValidTask(Task task) {
        for (TaskList taskList : taskLists) {
            for (Task t : taskList.getTasks()) {
                if (t.getName().equals(task.getName().trim())) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Agrega una nueva tarea a la lista de tareas.
     *
     * @param task La tarea a agregar.
     */
    public void addTask(Task task) {
        for (TaskList taskList : taskLists) {
            if (taskList.getName().equals(task.getSubtask())) {
                taskList.addTaskToTaskList(task);
            }
        }
    }

    /**
     * Obtiene la lista de tareas.
     *
     * @return La lista de tareas.
     */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> allTasks = new ArrayList<>();
        for (TaskList taskList : taskLists) {
            allTasks.addAll(taskList.getTasks());
        }

        return allTasks;
    }

    public ArrayList<TaskList> getTaskLists() {
        return taskLists;
    }

    public TaskList getTaskListByName(String name) {
        for (TaskList taskList : taskLists) {
            if (taskList.getName().equals(name)) {
                return taskList;
            }
        }

        return null;
    }

    public void deleteTaskList(TaskList taskList) {
        taskLists.remove(taskList);
    }

    public void addTaskList(TaskList taskList) {
        taskLists.add(taskList);
    }

    /**
     * Filtra las tareas según los criterios especificados.
     *
     * @param searchText                Texto de búsqueda en el nombre o descripción.
     * @param priorityFilterValue       Valor del filtro de prioridad.
     * @param percentageFilterMode      Modo de filtro de porcentaje.
     * @param percentageFilterValue     Valor del filtro de porcentaje.
     * @param creationChooserFilterMode Modo de filtro de fecha de creación.
     * @param creationChooserDate       Fecha seleccionada para el filtro de creación.
     * @param deadlineChooserFilterMode Modo de filtro de fecha límite.
     * @param deadlineChooserDate       Fecha seleccionada para el filtro de fecha límite.
     * @param selectCategoryFilterValue Categoría seleccionada para filtrar.
     * @param selectSubTaskFilterValue  Subtarea seleccionada para filtrar.
     */
    public void filterTasks(
            String searchText,
            String priorityFilterValue,
            RangeSelections percentageFilterMode, int percentageFilterValue,
            RangeSelections creationChooserFilterMode, LocalDate creationChooserDate,
            RangeSelections deadlineChooserFilterMode, LocalDate deadlineChooserDate,
            String selectCategoryFilterValue,
            String selectSubTaskFilterValue
    ) {
        tasksFiltered.clear();
        for (TaskList taskList : taskLists) {
            for (Task task : taskList.getTasks()) {
                if ((task.getName().toLowerCase().contains(searchText.toLowerCase()) || task.getDescription().toLowerCase().contains(searchText.toLowerCase())) &&
                        (priorityFilterValue == null || task.getPriority().toString().equals(priorityFilterValue)) &&
                        filterTaskByPercentage(percentageFilterMode, percentageFilterValue, task.getPercentage()) &&
                        filterTaskByCreationChooser(creationChooserFilterMode, creationChooserDate, task.getCreationDate()) &&
                        filterTaskByDeadlineChooser(deadlineChooserFilterMode, deadlineChooserDate, task.getDeadline()) &&
                        (selectCategoryFilterValue == null || task.getCategory().equals(selectCategoryFilterValue)) &&
                        (selectSubTaskFilterValue == null || task.getSubtask().equals(selectSubTaskFilterValue))
                ) {
                    tasksFiltered.add(task);
                }
            }
        }
    }

    public void filterLists(String searchText) {
        taskListsFiltered.clear();
        for (TaskList taskList : taskLists) {
            if ((taskList.getName().toLowerCase().contains(searchText.toLowerCase()))) {
                taskListsFiltered.add(taskList);
            }
        }
    }

    public ArrayList<TaskList> getTaskListsFiltered() {
        return taskListsFiltered;
    }

    /**
     * Filtra una tarea según el porcentaje y el modo de filtro seleccionado.
     *
     * @param percentageFilterMode  El modo de filtro de porcentaje (hasta, desde, ninguno).
     * @param percentageFilterValue El valor de porcentaje para filtrar.
     * @param taskPercentage        El porcentaje de la tarea a evaluar.
     * @return true si la tarea cumple con el criterio de filtro, de lo contrario false.
     */
    private boolean filterTaskByPercentage(RangeSelections percentageFilterMode, int percentageFilterValue, int taskPercentage) {
        return switch (percentageFilterMode) {
            case UNTIL -> percentageFilterValue >= taskPercentage;
            case SINCE -> percentageFilterValue <= taskPercentage;
            case NO -> true;
        };
    }

    /**
     * Filtra una tarea según la fecha de creación y el modo de filtro seleccionado.
     *
     * @param creationChooserFilterMode El modo de filtro de fecha de creación (hasta, desde, ninguno).
     * @param creationChooserDate       La fecha de referencia para filtrar.
     * @param creationDate              La fecha de creación de la tarea a evaluar.
     * @return true si la tarea cumple con el criterio de filtro, de lo contrario false.
     */
    private boolean filterTaskByCreationChooser(RangeSelections creationChooserFilterMode, LocalDate creationChooserDate, LocalDate creationDate) {
        return switch (creationChooserFilterMode) {
            case UNTIL -> !creationDate.isAfter(creationChooserDate);
            case SINCE -> !creationDate.isBefore(creationChooserDate);
            case NO -> true;
        };
    }

    /**
     * Filtra una tarea según la fecha límite y el modo de filtro seleccionado.
     *
     * @param deadlineChooserFilterMode El modo de filtro de fecha límite (hasta, desde, ninguno).
     * @param deadlineChooserDate       La fecha de referencia para filtrar.
     * @param deadline                  La fecha límite de la tarea a evaluar.
     * @return true si la tarea cumple con el criterio de filtro, de lo contrario false.
     */
    private boolean filterTaskByDeadlineChooser(RangeSelections deadlineChooserFilterMode, LocalDate deadlineChooserDate, LocalDate deadline) {
        return switch (deadlineChooserFilterMode) {
            case UNTIL -> !deadline.isAfter(deadlineChooserDate);
            case SINCE -> !deadline.isBefore(deadlineChooserDate);
            case NO -> true;
        };
    }

    public ArrayList<Task> getTasksFiltered() {
        return tasksFiltered;
    }

    public ArrayList<TaskList> initTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new Task("TE 1", "Realizar un análisis de una aplicación.", Priority.HIGH, LocalDate.of(2025,3,5), 50, "Escuela", "IPC"));
        tasks.add(new Task("Lectura", "Leer un artículo sobre el uso de deshacer para el tratamiento de errores.", Priority.LOW, LocalDate.of(2025,3,11), 100, "Escuela", "IPC"));
        tasks.add(new Task("Boceto", "Realizar un boceto de la práctica 2.", Priority.HIGH, LocalDate.of(2025,3,30), 25, "Escuela", "IPC"));
        tasks.add(new Task("TE 2", "Realizar una aplicación web.", Priority.MEDIUM, LocalDate.of(2025,4,28), 100, "Escuela", "IPC"));

        return new ArrayList<>(List.of(new TaskList("IPC", tasks)));
    }
}
