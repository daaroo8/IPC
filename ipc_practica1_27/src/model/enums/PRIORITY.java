package model.enums;

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
