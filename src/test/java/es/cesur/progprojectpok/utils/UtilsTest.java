package es.cesur.progprojectpok.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void generarNumeroAleatorio() {
    }

    @Test
    void generarNumeroAleatorioEntre() {
        int numAleatorioGen = Utils.generarNumeroAleatorio(1,2);
        assertTrue(numAleatorioGen >= 1);
        assertTrue(numAleatorioGen <= 2);

        numAleatorioGen = Utils.generarNumeroAleatorio(-2,-1);
        assertTrue(numAleatorioGen >= -2);
        assertTrue(numAleatorioGen <= -1);

    }


    @Test
    void generarNumeroAleatorioIgual() {
        int numAleatorioGen = Utils.generarNumeroAleatorio(1,1);
        assertEquals(1,numAleatorioGen);

        numAleatorioGen = Utils.generarNumeroAleatorio(-1,-1);
        assertEquals(-1,numAleatorioGen);

        numAleatorioGen = Utils.generarNumeroAleatorio(1000,1000);
        assertEquals(1000,numAleatorioGen);
    }

    @Test
    void generarNumeroAleatorioNulos() {
        Integer numAleatorioGen = Utils.generarNumeroAleatorio(2,1);
        assertNull(numAleatorioGen);



    }
}