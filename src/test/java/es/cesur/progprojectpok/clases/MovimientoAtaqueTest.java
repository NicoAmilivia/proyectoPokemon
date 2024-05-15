package es.cesur.progprojectpok.clases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovimientoAtaqueTest {

    private Pokemon atacante;
    private Pokemon objetivo;
    private MovimientoAtaque movimientoAtaque;

    @BeforeEach
    void setUp() {
        atacante = new Pokemon("Pikachu", 25, 55, 40, 50, 50, 90, 5, 100, 35, 1);
        objetivo = new Pokemon("Charmander", 4, 52, 43, 60, 50, 65, 5, 100, 39, 2);
        movimientoAtaque = new MovimientoAtaque(15, "Impactrueno", Tipo.ELECTRICO, 40);
    }

    @Test
    void usarMovimiento() {
        int vidaInicialObjetivo = objetivo.getVidaActual();
        movimientoAtaque.usarMovimiento(atacante, objetivo);

        int danioEsperado = (int) Math.round(atacante.getAtaque() * (movimientoAtaque.getPotenciaAtaque() / 100.0));
        int vidaEsperada = vidaInicialObjetivo - danioEsperado;

        assertEquals(vidaEsperada, objetivo.getVidaActual());
    }
}
