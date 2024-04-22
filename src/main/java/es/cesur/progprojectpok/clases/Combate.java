package es.cesur.progprojectpok.clases;

public class Combate {

    //ATRIBUTOS
    private static final int MAX_POKEMON_ENTRENADOR =6;

    private Turno turno;


    //CONSTRUCTOR

    public Combate(Turno turno) {
        this.turno = turno;
    }

    //METODO

    public void combatir (Pokemon pokemon1, Pokemon pokemon2){


    }


    //GET AND SET

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
