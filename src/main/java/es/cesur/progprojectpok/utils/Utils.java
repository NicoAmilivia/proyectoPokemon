package es.cesur.progprojectpok.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static final String PATH_BASE_SOURCES = "src/main/resources/es/cesur/progprojectpok";

    /**
     * Genera un número aleatorio entero entre dos números dados, ambos inclusive.
     * Si el mínimo es mayor que el máximo, devuelve nulo.
     *
     * @param min El límite inferior del rango (incluido).
     * @param max El límite superior del rango (incluido).
     * @return Un número aleatorio entero entre {@code min} y {@code max}, ambos inclusive.
     */
    public static Integer generarNumeroAleatorio(int min, int max) {
        // Asegura que min <= max para evitar IllegalArgumentException en ThreadLocalRandom.current().nextInt()
        if (min > max) {
            return null;
        }

        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }






}
