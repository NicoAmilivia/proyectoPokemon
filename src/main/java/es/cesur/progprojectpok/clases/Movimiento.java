package es.cesur.progprojectpok.clases;


/**
 * Representa un movimiento que puede ser utilizado por un Pokémon durante una batalla.
 * Contiene información sobre el nombre del movimiento y su número de usos.
 *
 * @version 1.0
 * @see Pokemon
 * @author Nico
 * @author Fernando
 */
public class Movimiento {

    //ATRIBUTOS
    private int numUsos;

    private String nombre;


    //CONSTRUCTOR

    public Movimiento(int numUsos, String nombre) {
        this.numUsos = numUsos;
        this.nombre = nombre;
    }


    //GET Y SET

    public int getNumUsos() {
        return numUsos;
    }

    public void setNumUsos(int numUsos) {
        this.numUsos = numUsos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }





    /**
     * Método para ejecutar el movimiento sobre un Pokémon atacante y un Pokémon objetivo.
     * Este método debe ser sobreescrito por cada tipo de movimiento específico.
     *
     * @param atacante El Pokémon que realiza el movimiento.
     * @param objetivo El Pokémon que recibe el movimiento.
     * @author Nico
     */

    public void usarMovimiento(Pokemon atacante, Pokemon objetivo){
        //Metodo vacio para ser sobreescrito en cada tipo de ataque
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "numUsos=" + numUsos +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
