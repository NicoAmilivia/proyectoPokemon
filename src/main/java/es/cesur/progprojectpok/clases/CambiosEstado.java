package es.cesur.progprojectpok.clases;

/**
 * Enumeración que representa los posibles cambios de estado en un Pokemon.
 * Los cambios de estado pueden ser de tipo ataque, defensa, ataque especial o defensa especial.
 *
 * @version 1.0
 * @author Nico
 */

public enum CambiosEstado {

    ATAQUE,
    DEFENSA,
    ATAQUE_ESPECIAL,
    DEFENSA_ESPECIAL;

    /**
     * Convierte una cadena de texto en un valor de la enumeración CambiosEstado.
     *
     * @param estadosString la cadena de texto que representa el estado
     * @return el valor de la enumeración correspondiente al estado especificado
     * @throws IllegalArgumentException si la cadena de texto no coincide con ningún estado conocido
     */

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
