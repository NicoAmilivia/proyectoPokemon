package es.cesur.progprojectpok.clases;

public class MovimientoMejora extends Movimiento{


    //ATRIBUTOS
    private int numTurnosMejora;
    private CambioEstado mejora;


    //CONSTRUCTOR CON PARAMETROS

    public MovimientoMejora(int numUsos, String nombre, int numTurnosMejora, CambioEstado mejora) {
        super(numUsos, nombre);
        this.numTurnosMejora=numTurnosMejora;
        this.mejora= mejora;
    }


    //GET Y SET

    public int getNumTurnosMejora() {
        return numTurnosMejora;
    }

    public void setNumTurnosMejora(int numTurnosMejora) {
        this.numTurnosMejora = numTurnosMejora;
    }

    public CambioEstado getMejora() {
        return mejora;
    }

    public void setMejora(CambioEstado mejora) {
        this.mejora = mejora;
    }
}
