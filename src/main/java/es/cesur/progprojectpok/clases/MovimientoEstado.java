package es.cesur.progprojectpok.clases;

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
