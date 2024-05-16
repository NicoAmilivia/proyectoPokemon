package es.cesur.progprojectpok.clases;

/**
 * Representa un movimiento de mejora que puede ser utilizado por un Pokémon durante una batalla.
 * Además de la información básica de un movimiento, contiene información sobre qué estadística del Pokémon será mejorada, la cantidad de mejora y la duración de la mejora.
 *
 * @version 1.0
 * @see Movimiento
 * @see CambiosEstado
 * @author Nico
 * @author Fernando
 */

public class MovimientoMejora extends Movimiento{


    //ATRIBUTOS
    private int numTurnosMejora;
    private CambiosEstado mejora;

    private int cantidadMejora;


    //CONSTRUCTOR CON PARAMETROS

    public MovimientoMejora(int numUsos, String nombre, int numTurnosMejora, CambiosEstado mejora, int cantidadMejora) {
        super(numUsos, nombre);
        this.numTurnosMejora=numTurnosMejora;
        this.mejora= mejora;
        this.cantidadMejora = cantidadMejora;
    }


    //GET Y SET

    public int getNumTurnosMejora() {
        return numTurnosMejora;
    }

    public void setNumTurnosMejora(int numTurnosMejora) {
        this.numTurnosMejora = numTurnosMejora;
    }

    public CambiosEstado getMejora() {
        return mejora;
    }

    public void setMejora(CambiosEstado mejora) {
        this.mejora = mejora;
    }

    public int getCantidadMejora() {
        return cantidadMejora;
    }

    public void setCantidadMejora(int cantidadMejora) {
        this.cantidadMejora = cantidadMejora;
    }


    /**
     * {@inheritDoc}
     * Realiza la mejora de la estadística del Pokémon atacante según la especificación del movimiento.
     *
     * @param atacante El Pokémon que realiza el movimiento de mejora.
     * @param objetivo El Pokémon objetivo, no utilizado en este método.
     * @author Nico
     */
    @Override
    public void usarMovimiento(Pokemon atacante, Pokemon objetivo) {
        super.usarMovimiento(atacante, objetivo);

        switch (this.getMejora()){
            case ATAQUE -> atacante.setAtaque(atacante.getAtaque() + this.getCantidadMejora());

            case DEFENSA -> atacante.setDefensa(atacante.getDefensa() + this.getCantidadMejora());

            case ATAQUE_ESPECIAL -> atacante.setAtaqueEspecial(atacante.getAtaqueEspecial() + this.getCantidadMejora());

            case DEFENSA_ESPECIAL -> atacante.setDefensaEspecial(atacante.getAtaqueEspecial() + this.getCantidadMejora());
        }

    }
}
