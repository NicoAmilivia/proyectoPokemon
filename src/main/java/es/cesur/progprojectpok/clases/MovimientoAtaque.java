package es.cesur.progprojectpok.clases;

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



    //Metodo de ataque

    @Override
    public void usarMovimiento(Pokemon atacante, Pokemon objetivo) {
        super.usarMovimiento(atacante, objetivo);

        int danio = (int) Math.round(atacante.getAtaque() * ((double) potenciaAtaque / 100));

        objetivo.setVidaActual(objetivo.getVidaActual() - danio);
    }
}
