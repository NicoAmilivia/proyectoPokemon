package es.cesur.progprojectpok.clases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovimientoMejoraTest {

    private Pokemon atacante;
    private MovimientoMejora movimientoMejoraAtaque;
    private MovimientoMejora movimientoMejoraDefensa;
    private MovimientoMejora movimientoMejoraAtaqueEspecial;
    private MovimientoMejora movimientoMejoraDefensaEspecial;

    @BeforeEach
    void setUp() {
        atacante = new Pokemon("Pikachu", 25, 55, 40, 50, 50, 90, 5, 100, 35, 1);
        movimientoMejoraAtaque = new MovimientoMejora(15, "Fortalecer Ataque", 3, CambiosEstado.ATAQUE, 10);
        movimientoMejoraDefensa = new MovimientoMejora(15, "Fortalecer Defensa", 3, CambiosEstado.DEFENSA, 10);
        movimientoMejoraAtaqueEspecial = new MovimientoMejora(15, "Fortalecer Ataque Especial", 3, CambiosEstado.ATAQUE_ESPECIAL, 10);
        movimientoMejoraDefensaEspecial = new MovimientoMejora(15, "Fortalecer Defensa Especial", 3, CambiosEstado.DEFENSA_ESPECIAL, 10);
    }

    @Test
    void usarMovimientoAtaque() {
        int ataqueInicial = atacante.getAtaque();
        movimientoMejoraAtaque.usarMovimiento(atacante, null); // El objetivo no es relevante aquí
        assertEquals(ataqueInicial + 10, atacante.getAtaque(), "El ataque del Pokemon debe haber aumentado en 10 puntos.");
    }

    @Test
    void usarMovimientoDefensa() {
        int defensaInicial = atacante.getDefensa();
        movimientoMejoraDefensa.usarMovimiento(atacante, null); // El objetivo no es relevante aquí
        assertEquals(defensaInicial + 10, atacante.getDefensa(), "La defensa del Pokemon debe haber aumentado en 10 puntos.");
    }

    @Test
    void usarMovimientoAtaqueEspecial() {
        int ataqueEspecialInicial = atacante.getAtaqueEspecial();
        movimientoMejoraAtaqueEspecial.usarMovimiento(atacante, null); // El objetivo no es relevante aquí
        assertEquals(ataqueEspecialInicial + 10, atacante.getAtaqueEspecial(), "El ataque especial del Pokemon debe haber aumentado en 10 puntos.");
    }

    @Test
    void usarMovimientoDefensaEspecial() {
        int defensaEspecialInicial = atacante.getDefensaEspecial();
        movimientoMejoraDefensaEspecial.usarMovimiento(atacante, null); // El objetivo no es relevante aquí
        assertEquals(defensaEspecialInicial + 10, atacante.getDefensaEspecial(), "La defensa especial del Pokemon debe haber aumentado en 10 puntos.");
    }
}
