package model.enums;

/**
 * Enum que representa los niveles de prioridad de una tarea.
 */
public enum Priority {
    LOW, MEDIUM, HIGH;

    /**
     * Devuelve la representación en cadena del nivel de prioridad.
     *
     * @return La representación en cadena del nivel de prioridad: "Baja", "Media" o "Alta".
     */
    @Override
    public String toString() {
        return switch (this) {
            case LOW -> "Baja";
            case MEDIUM -> "Media";
            case HIGH -> "Alta";
        };
    }
}

