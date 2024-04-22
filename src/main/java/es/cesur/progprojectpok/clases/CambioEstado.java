package es.cesur.progprojectpok.clases;

public class CambioEstado {

    //ATRIBUTOS
    private int vitalidad;
    private int velocidad;
    private int ataque;
    private int defensa;
    private int ataqueEspecial;

    private int defensaEspecial;


    private int probNoAtacar;


    //CONSTRUCTOR CON PARAMETROS
    public CambioEstado(int vitalidad, int velocidad, int ataque, int defensa, int ataqueEspecial, int defensaEspecial,int probNoAtacar) {
        this.vitalidad = vitalidad;
        this.velocidad = velocidad;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.probNoAtacar = probNoAtacar;
    }


    //GET AND SET

    public int getVitalidad() {
        return vitalidad;
    }

    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public int getDefensaEspecial() {
        return defensaEspecial;
    }

    public void setDefensaEspecial(int defensaEspecial) {
        this.defensaEspecial = defensaEspecial;
    }


    public int getProbNoAtacar() {
        return probNoAtacar;
    }

    public void setProbNoAtacar(int probNoAtacar) {
        this.probNoAtacar = probNoAtacar;
    }
}
