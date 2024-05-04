package es.cesur.progprojectpok.clases;

public class MovimientoMejora extends Movimiento{


    //ATRIBUTOS
    private int numTurnosMejora;
    private CambiosEstado mejora;


    //CONSTRUCTOR CON PARAMETROS

    public MovimientoMejora(int numUsos, String nombre, int numTurnosMejora, CambiosEstado mejora) {
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

    public CambiosEstado getMejora() {
        return mejora;
    }

    public void setMejora(CambiosEstado mejora) {
        this.mejora = mejora;
    }
}
