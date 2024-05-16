package es.cesur.progprojectpok.clases;


/**
 * Representa un objeto que puede ser utilizado por un Pokémon durante una batalla.
 * Contiene información sobre bonificaciones, penalizaciones y otros efectos que el objeto puede tener en el Pokémon que lo utiliza.
 *
 * @version 1.0
 * @author Nico
 * @author Fernando
 */
public class Objeto {


    //ATRIBUTOS
    private String bonificacion;

    private String penalizacion;

    private int porcAumentoAtaque;

    private int porcAumentoDefensa;

    private boolean restauraNumMov;


    //CONSTRUCTOR CON PARAMETROS

    public Objeto(String bonificacion, String penalizacion, int porcAumentoAtaque, int porcAumentoDefensa, boolean restauraNumMov) {
        this.bonificacion = bonificacion;
        this.penalizacion = penalizacion;
        this.porcAumentoAtaque = porcAumentoAtaque;
        this.porcAumentoDefensa = porcAumentoDefensa;
        this.restauraNumMov = restauraNumMov;
    }


    //GET Y SET
    public String getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(String bonificacion) {
        this.bonificacion = bonificacion;
    }

    public String getPenalizacion() {
        return penalizacion;
    }

    public void setPenalizacion(String penalizacion) {
        this.penalizacion = penalizacion;
    }

    public int getPorcAumentoAtaque() {
        return porcAumentoAtaque;
    }

    public void setPorcAumentoAtaque(int porcAumentoAtaque) {
        this.porcAumentoAtaque = porcAumentoAtaque;
    }

    public int getPorcAumentoDefensa() {
        return porcAumentoDefensa;
    }

    public void setPorcAumentoDefensa(int porcAumentoDefensa) {
        this.porcAumentoDefensa = porcAumentoDefensa;
    }

    public boolean isRestauraNumMov() {
        return restauraNumMov;
    }

    public void setRestauraNumMov(boolean restauraNumMov) {
        this.restauraNumMov = restauraNumMov;
    }
}
