package model;


import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    public enum PRIORITY {
        LOW, MEDIUM, HIGH;

        @Override
        public String toString() {
            return switch (this) {
                case LOW -> "Baja";
                case MEDIUM -> "Media";
                case HIGH -> "Alta";
            };
        }
    }

    public enum STATUS {
        NOT_STARTED, IN_PROGRESS, COMPLETED;

        @Override
        public String toString() {
            return switch (this) {
                case NOT_STARTED -> "Sin empezar";
                case IN_PROGRESS -> "En progreso";
                case COMPLETED -> "Completada";
            };

        }
    }

    public String getDescription() {
        return description;
    }

    public PRIORITY getPriority() {
        return priority;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getPercentage() {
        return percentage;
    }

    public String getCategory() {
        return category;
    }

    public String getSubtask() {
        return subtask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(PRIORITY priority) {
        this.priority = priority;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubtask(String subtask) {
        this.subtask = subtask;
    }

    private String name;
    private String description;
    private PRIORITY priority;
    private LocalDate creationDate;
    private LocalDate deadline;
    private int percentage;
    private String category;
    private String subtask;

    public Task(String name, String description, PRIORITY priority, LocalDate deadline, int percentage, String category, String subtask) {
        if (!isValidPercentage(percentage))
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


    public void setPercentage(int percentage) {
        if (!isValidPercentage(percentage))
            throw new IllegalArgumentException("percentage must be between 0 and 100");

        this.percentage = percentage;
    }

    public STATUS getStatus() {
        if (percentage == 0)
            return STATUS.NOT_STARTED;

        if (percentage == 100)
            return STATUS.COMPLETED;

        return STATUS.IN_PROGRESS;
    }

    private boolean isValidPercentage(int percentage) {
        return (percentage >= 0 && percentage <= 100);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Font font = new Font("Dialog", Font.PLAIN, 12); // Usa la fuente que estás usando en el JList
        FontMetrics fm = Toolkit.getDefaultToolkit().getFontMetrics(font);

        return String.format("%-10s | %-10s | %-11s", name, deadline.format(formatter), getStatus());
    }

}
