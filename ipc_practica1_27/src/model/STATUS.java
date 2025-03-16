package model;

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
