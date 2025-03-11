package model;


import java.time.Instant;
import java.time.LocalDate;

public class Task {
    public enum PRIORITY {
        LOW, MEDIUM, HIGH
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

    private String name;
    private String description;
    private PRIORITY priority;
    private LocalDate creation;
    private LocalDate deadline;
    int percentage;
    String category;
    String subtask;

    public Task(String name, String description, PRIORITY priority, LocalDate deadline, int percentage, String category, String subtask) {
        if (!isValidPercentage(percentage))
            throw new IllegalArgumentException("percentage must be between 0 and 100");


        this.name = name;
        this.description = description;
        this.priority = priority;
        this.creation = LocalDate.now();
        this.deadline = deadline;
        this.percentage = percentage;
        this.category = category;
        this.subtask = subtask;
    }


    public void setPercentage(int percentage) {
        if (isValidPercentage(percentage))
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
        return name + "\t" + deadline.toString() + "\t" + getStatus().toString();
    }
}
