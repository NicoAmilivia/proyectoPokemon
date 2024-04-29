package es.cesur.progprojectpok.clases;

public class Combate {

    //ATRIBUTOS
    private static final int MAX_POKEMON_ENTRENADOR =6;
    private Turno turno;

    private Entrenador ganador;

    private  Entrenador jugador;

    private Entrenador rival;

    private int pokemonKOjugador;

    private int pokemonKOrival;


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

    public Entrenador getGanador() {
        return ganador;
    }

    public void setGanador(Entrenador ganador) {
        this.ganador = ganador;
    }

    public Entrenador getJugador() {
        return jugador;
    }

    public void setJugador(Entrenador jugador) {
        this.jugador = jugador;
    }

    public Entrenador getRival() {
        return rival;
    }

    public void setRival(Entrenador rival) {
        this.rival = rival;
    }

    public int getPokemonKOjugador() {
        return pokemonKOjugador;
    }

    public void setPokemonKOjugador(int pokemonKOjugador) {
        this.pokemonKOjugador = pokemonKOjugador;
    }

    public int getPokemonKOrival() {
        return pokemonKOrival;
    }

    public void setPokemonKOrival(int pokemonKOrival) {
        this.pokemonKOrival = pokemonKOrival;
    }
}
