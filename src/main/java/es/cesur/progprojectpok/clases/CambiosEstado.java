package es.cesur.progprojectpok.clases;

public enum CambiosEstado {

    ATAQUE,
    DEFENSA,
    ATAQUE_ESPECIAL,
    DEFENSA_ESPECIAL;

    public static CambiosEstado CambioEstadoStringToEnum(String estadosString) {
        return switch (estadosString.toUpperCase()) {
            case "ATAQUE" -> ATAQUE;
            case "DEFENSA" -> DEFENSA;
            case "ATAQUE_ESPECIAL" -> ATAQUE_ESPECIAL;
            case "DEFENSA_ESPECIAL" -> DEFENSA_ESPECIAL;
            default -> throw new IllegalArgumentException("Estado desconocido: " + estadosString);
        };
    }
}
