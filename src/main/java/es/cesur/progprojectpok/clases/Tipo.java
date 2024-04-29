package es.cesur.progprojectpok.clases;

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