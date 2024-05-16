package es.cesur.progprojectpok.clases;

/**
 * Representa un movimiento de estado que puede ser utilizado por un Pokémon durante una batalla.
 * Además de la información básica de un movimiento, contiene información sobre el nuevo estado que puede ser aplicado y la duración del estado.
 *
 * @version 1.0
 * @see Movimiento
 * @see Estado
 * @author Nico
 * @author Fernando
 */
public class MovimientoEstado extends Movimiento {

    //ATRIBUTOS
    private Estado nuevoEstado;
    private int numTurnosDuracion;

    //CONSTRUCTOR CON PARAMETROS

    public MovimientoEstado(int numUsos, String nombre,Estado nuevoEstado,int numTurnosDuracion) {
        super(numUsos, nombre);
        this.nuevoEstado = nuevoEstado;
        this.numTurnosDuracion=numTurnosDuracion;
    }

    //GET Y SET
    public Estado getNuevoEstado() {
        return nuevoEstado;
    }

    public void setNuevoEstado(Estado nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }

    public int getNumTurnosDuracion() {
        return numTurnosDuracion;
    }

    public void setNumTurnosDuracion(int numTurnosDuracion) {
        this.numTurnosDuracion = numTurnosDuracion;
    }
}
