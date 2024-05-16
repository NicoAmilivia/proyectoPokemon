package es.cesur.progprojectpok.clases;


/**
 * Representa un movimiento de ataque que puede ser utilizado por un Pokémon durante una batalla.
 * Además de la información básica de un movimiento, contiene información sobre el tipo del movimiento y su potencia de ataque.
 *
 * @version 1.0
 * @see Movimiento
 * @see Tipo
 * @author Nico
 * @author Fernando
 */

public class MovimientoAtaque extends Movimiento {

    //ATRIBUTO
   private Tipo tipoMovimiento;
   private int potenciaAtaque;


   //CONSTRUCTOR CON PARAMETROS

    public MovimientoAtaque(int numUsos, String nombre,Tipo tipoMovimiento, int potenciaAtaque) {
        super(numUsos, nombre);
        this.tipoMovimiento = tipoMovimiento;
        this.potenciaAtaque = potenciaAtaque;
    }


    //GET Y SET



    public int getPotenciaAtaque() {
        return potenciaAtaque;
    }



    /**
     * {@inheritDoc}
     * Realiza el cálculo del daño infligido al Pokémon objetivo basado en la potencia de ataque del movimiento.
     *
     * @param atacante El Pokémon que realiza el movimiento de ataque.
     * @param objetivo El Pokémon que recibe el ataque.
     * @author Nico
     * @author Fernando
     */

    @Override
    public void usarMovimiento(Pokemon atacante, Pokemon objetivo) {
        super.usarMovimiento(atacante, objetivo);

        int danio = (int) Math.round(atacante.getAtaque() * ((double) potenciaAtaque / 100));

        objetivo.setVidaActual(objetivo.getVidaActual() - danio);
    }
}
