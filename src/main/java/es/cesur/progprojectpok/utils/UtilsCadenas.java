package es.cesur.progprojectpok.utils;

public class UtilsCadenas {


    public static String anadirFilaTexto(String textoOriginal, String nuevaFila) {
        return textoOriginal.concat(System.lineSeparator() + nuevaFila);
    }

    public static String modificarLineaPorInicio(String textoOriginal, String cadenaBusqueda, int x, String nuevaLinea) {
        String[] lineas = textoOriginal.split(System.lineSeparator());
        StringBuilder textoModificado = new StringBuilder();

        for (String linea : lineas) {
            if (linea.length() >= x && linea.substring(0, x).equals(cadenaBusqueda)) {
                textoModificado.append(nuevaLinea);
            } else {
                textoModificado.append(linea);
            }
            textoModificado.append(System.lineSeparator());
        }

        return textoModificado.toString();
    }

    public static String leerLineaPorInicio(String textoOriginal, String cadenaBusqueda, int x) {
        String[] lineas = textoOriginal.split(System.lineSeparator());

        for (String linea : lineas) {
            if (linea.length() >= x && linea.substring(0, x).equals(cadenaBusqueda)) {
                return linea;
            }
        }

        return null; // No se encontró la línea
    }


    public static String modificarCampoLinea(String textoOriginal, String separador, int posicionCampo, String nuevoValor) {
        String[] lineas = textoOriginal.split(System.lineSeparator());
        StringBuilder textoModificado = new StringBuilder();

        for (String linea : lineas) {
            String[] campos = linea.split(separador);
            if (campos.length >= posicionCampo) {
                campos[posicionCampo - 1] = nuevoValor; // Las posiciones inician en 1 para el usuario
                String lineaModificada = String.join(separador, campos);
                textoModificado.append(lineaModificada);
            } else {
                textoModificado.append(linea);
            }
            textoModificado.append(System.lineSeparator());
        }

        return textoModificado.toString();
    }


}
