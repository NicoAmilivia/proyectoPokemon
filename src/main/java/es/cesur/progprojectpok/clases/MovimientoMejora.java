package es.cesur.progprojectpok.clases;

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
