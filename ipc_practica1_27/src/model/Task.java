package model;

import model.enums.PRIORITY;
import model.enums.STATUS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa una tarea
 */
public class Task {

    /**
     * Obtiene el nombre de la tarea.
     *
     * @return El nombre de la tarea.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la descripción de la tarea.
     *
     * @return La descripción de la tarea.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtiene la prioridad de la tarea.
     *
     * @return La prioridad de la tarea.
     */
    public PRIORITY getPriority() {
        return priority;
    }

    /**
     * Obtiene la fecha de creación de la tarea.
     *
     * @return La fecha de creación de la tarea.
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Obtiene la fecha límite de la tarea.
     *
     * @return La fecha límite de la tarea.
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Obtiene el porcentaje de progreso de la tarea.
     *
     * @return El porcentaje de progreso de la tarea.
     */
    public int getPercentage() {
        return percentage;
    }

    /**
     * Obtiene la categoría de la tarea.
     *
     * @return La categoría de la tarea.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Obtiene la subtarea asociada a la tarea.
     *
     * @return La subtarea asociada a la tarea.
     */
    public String getSubtask() {
        return subtask;
    }

    /**
     * Establece el nombre de la tarea.
     *
     * @param name El nombre de la tarea.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Establece la descripción de la tarea.
     *
     * @param description La descripción de la tarea.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Establece la prioridad de la tarea.
     *
     * @param priority La prioridad de la tarea.
     */
    public void setPriority(PRIORITY priority) {
        this.priority = priority;
    }

    /**
     * Establece la fecha límite de la tarea.
     *
     * @param deadline La fecha límite de la tarea.
     */
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    /**
     * Establece la categoría de la tarea.
     *
     * @param category La categoría de la tarea.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Establece la subtarea asociada a la tarea.
     *
     * @param subtask La subtarea asociada a la tarea.
     */
    public void setSubtask(String subtask) {
        this.subtask = subtask;
    }

    private String name;
    private String description;
    private PRIORITY priority;
    private final LocalDate creationDate;
    private LocalDate deadline;
    private int percentage;
    private String category;
    private String subtask;

    /**
     * Constructor para crear una nueva tarea.
     *
     * @param name El nombre de la tarea.
     * @param description La descripción de la tarea.
     * @param priority La prioridad de la tarea.
     * @param deadline La fecha límite de la tarea.
     * @param percentage El porcentaje de progreso de la tarea.
     * @param category La categoría de la tarea.
     * @param subtask La subtarea asociada a la tarea.
     * @throws IllegalArgumentException Si el porcentaje no está entre 0 y 100.
     */
    public Task(String name, String description, PRIORITY priority, LocalDate deadline, int percentage, String category, String subtask) {
        if (isInvalidPercentage(percentage))
            throw new IllegalArgumentException("percentage must be between 0 and 100");


        this.name = name;
        this.description = description;
        this.priority = priority;
        this.creationDate = LocalDate.now();
        this.deadline = deadline;
        this.percentage = percentage;
        this.category = category;
        this.subtask = subtask;
    }

    /**
     * Establece el porcentaje de progreso de la tarea.
     *
     * @param percentage El porcentaje de progreso de la tarea.
     * @throws IllegalArgumentException Si el porcentaje no está entre 0 y 100.
     */
    public void setPercentage(int percentage) {
        if (isInvalidPercentage(percentage))
            throw new IllegalArgumentException("percentage must be between 0 and 100");

        this.percentage = percentage;
    }

    /**
     * Obtiene el estado actual de la tarea según su porcentaje de progreso.
     *
     * @return El estado de la tarea: NOT_STARTED, IN_PROGRESS o COMPLETED.
     */
    public STATUS getStatus() {
        if (percentage == 0)
            return STATUS.NOT_STARTED;

        if (percentage == 100)
            return STATUS.COMPLETED;

        return STATUS.IN_PROGRESS;
    }

    /**
     * Verifica si el porcentaje es inválido (fuera del rango 0-100).
     *
     * @param percentage El porcentaje a verificar.
     * @return true si el porcentaje es menor que 0 o mayor que 100, de lo contrario false.
     */
    private boolean isInvalidPercentage(int percentage) {
        return (percentage < 0 || percentage > 100);
    }

    /**
     * Devuelve una representación en cadena de la tarea con su nombre, fecha límite y estado.
     *
     * @return La representación en cadena de la tarea.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%-10s | %-10s | %-11s", name, deadline.format(formatter), getStatus());
    }

}
