package es.cesur.progprojectpok.clases;

/**
 * Enumeración que representa los posibles tipos de un Pokemon.
 * Cada tipo tiene ventajas y desventajas contra otros tipos en combate.
 *
 * @version 1.0
 * @author Nico
 */
public enum Tipo {
    AGUA,
    BICHO,
    DRAGON,
    ELECTRICO,
    FANTASMA,
    FUEGO,
    HIELO,
    LUCHA,
    NORMAL,
    PLANTA,
    PSIQUICO,
    ROCA,
    TIERRA,
    VENENO,
    VOLADOR,
    NULL;



    /**
     * Convierte una cadena de texto en un valor de la enumeración Tipo.
     *
     * @param tipoString la cadena de texto que representa el tipo
     * @return el valor de la enumeración correspondiente al tipo especificado
     * @throws IllegalArgumentException si la cadena de texto no coincide con ningún tipo conocido
     */
    public static Tipo TipoStringToEnum(String tipoString) {
        return switch (tipoString.toUpperCase()) {
            case "AGUA" -> AGUA;
            case "BICHO" -> BICHO;
            case "DRAGON" -> DRAGON;
            case "ELECTRICO" -> ELECTRICO;
            case "FANTASMA" -> FANTASMA;
            case "FUEGO" -> FUEGO;
            case "HIELO" -> HIELO;
            case "LUCHA" -> LUCHA;
            case "NORMAL" -> NORMAL;
            case "PLANTA" -> PLANTA;
            case "PSIQUICO" -> PSIQUICO;
            case "ROCA" -> ROCA;
            case "TIERRA" -> TIERRA;
            case "VENENO" -> VENENO;
            case "VOLADOR" -> VOLADOR;
            default -> NULL;
        };
    }

}