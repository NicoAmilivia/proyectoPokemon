package es.cesur.progprojectpok.clases;

public enum Estado {
    PARALIZADO,
    QUEMADO,
    ENVENENADO,
    DORMIDO,
    CONGELADO,
    HELADO,
    SOMNOLIENTO,
    DEBILITADO,
    CONFUSO;




    public static Estado EstadoStringToEnum(String estadoString) {
        return switch (estadoString.toUpperCase()) {
            case "PARALIZADO" -> PARALIZADO;
            case "QUEMADO" -> QUEMADO;
            case "ENVENENADO" -> ENVENENADO;
            case "DORMIDO" -> DORMIDO;
            case "CONGELADO" ->CONGELADO;
            case "HELADO" -> HELADO;
            case "SOMNOLIENTO" -> SOMNOLIENTO;
            case "DEBILITADO" -> DEBILITADO;
            case "CONFUSO" -> CONFUSO;
            default -> throw new IllegalArgumentException("Estado desconocido: " + estadoString);
        };
    }
}
