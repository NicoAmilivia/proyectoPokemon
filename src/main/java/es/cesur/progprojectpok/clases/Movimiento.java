package es.cesur.progprojectpok.clases;

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

    @Override
    public String toString() {
        return "Movimiento{" +
                "numUsos=" + numUsos +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
