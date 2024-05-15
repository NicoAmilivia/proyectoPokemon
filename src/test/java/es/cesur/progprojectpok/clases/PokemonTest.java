package es.cesur.progprojectpok.clases;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {

    Pokemon pokemon = new Pokemon();
    @Test
    void randomSex() {
        char sexo= pokemon.randomSex();
        assertTrue(sexo == 'H' || sexo == 'M');
    }


}