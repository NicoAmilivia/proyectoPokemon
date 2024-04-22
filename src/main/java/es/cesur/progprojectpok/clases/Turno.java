package es.cesur.progprojectpok.clases;

public class Turno {


    //ATRIBUTOS
    private int numCombate;

    private int numTurnoDelCombate;


    //CONSTRUCTOR CON PARAMETROS
    public Turno(int numCombate, int numTurnoDelCombate) {
        this.numCombate = numCombate;
        this.numTurnoDelCombate = numTurnoDelCombate;
    }


    //GET Y SET
    public int getNumCombate() {
        return numCombate;
    }

    public void setNumCombate(int numCombate) {
        this.numCombate = numCombate;
    }

    public int getNumTurnoDelCombate() {
        return numTurnoDelCombate;
    }

    public void setNumTurnoDelCombate(int numTurnoDelCombate) {
        this.numTurnoDelCombate = numTurnoDelCombate;
    }
}
