package es.cesur.progprojectpok.clases;


/**
 * Enumeración que representa los posibles estados en los que puede encontrarse un Pokemon durante un combate.
 *
 * @version 1.0
 * @author Nico
 */
public enum Estado {
    PARALIZADO,
    QUEMADO,
    ENVENENADO,
    DORMIDO,
    CONGELADO,
    HELADO,
    SOMNIOLENTO,
    DEBILITADO,
    CONFUSO;





    /**
     * Convierte una cadena de texto en un valor de la enumeración Estado.
     *
     * @param estadoString la cadena de texto que representa el estado
     * @return el valor de la enumeración correspondiente al estado especificado
     * @throws IllegalArgumentException si la cadena de texto no coincide con ningún estado conocido
     */
    public static Estado EstadoStringToEnum(String estadoString) {
        return switch (estadoString.toUpperCase()) {
            case "PARALIZADO" -> PARALIZADO;
            case "QUEMADO" -> QUEMADO;
            case "ENVENENADO" -> ENVENENADO;
            case "DORMIDO" -> DORMIDO;
            case "CONGELADO" ->CONGELADO;
            case "HELADO" -> HELADO;
            case "SOMNIOLENTO" -> SOMNIOLENTO;
            case "DEBILITADO" -> DEBILITADO;
            case "CONFUSO" -> CONFUSO;
            default -> throw new IllegalArgumentException("Estado desconocido: " + estadoString);
        };
    }
}
