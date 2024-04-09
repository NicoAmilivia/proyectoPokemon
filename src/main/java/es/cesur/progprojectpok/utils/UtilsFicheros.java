package es.cesur.progprojectpok.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class UtilsFicheros {

    public void guardarArchivoBinario(String rutaArchivo, byte[] datos) {
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            fos.write(datos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void guardarArchivoTexto(String rutaArchivo, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public byte[] leerArchivoBinario(String rutaArchivo) {
        try (FileInputStream fis = new FileInputStream(rutaArchivo)) {
            byte[] data = new byte[fis.available()];
            fis.read(data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String leerArchivoTexto(String rutaArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido.toString();
    }


    public void modificarLineaArchivoTexto(String rutaArchivo, String inicioLinea, String nuevaLinea) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(rutaArchivo));
            List<String> lineasModificadas = lineas.stream()
                    .map(linea -> linea.startsWith(inicioLinea) ? nuevaLinea : linea)
                    .collect(Collectors.toList());
            Files.write(Paths.get(rutaArchivo), lineasModificadas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String recuperarLineaPorInicio(String rutaArchivo, String inicioLinea) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(inicioLinea)) {
                    return linea;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // No se encontró ninguna línea que coincida
    }

}
