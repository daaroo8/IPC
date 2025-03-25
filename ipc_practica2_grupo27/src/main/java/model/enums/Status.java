package model.enums;

/**
 * Enum que representa los estados de una tarea.
 */
public enum Status {
    NOT_STARTED, IN_PROGRESS, COMPLETED;

    /**
     * Devuelve la representación en cadena del estado de la tarea.
     *
     * @return La representación en cadena del estado de la tarea: "Sin empezar", "En progreso" o "Completada".
     */
    @Override
    public String toString() {
        return switch (this) {
            case NOT_STARTED -> "Sin empezar";
            case IN_PROGRESS -> "En progreso";
            case COMPLETED -> "Completada";
        };
    }
}
