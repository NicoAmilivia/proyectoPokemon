package es.cesur.progprojectpok.clases;

public class Entrenador {

    //ATRIBUTOS
    private int id;
    private String nombre;
    private int numPokeDollars;


    //CONSTRUCTOR CON PARAMETROS
    public Entrenador(int id, String nombre, int numPokeDollars) {
        this.id = id;
        this.nombre = nombre;
        this.numPokeDollars = numPokeDollars;
    }



    //GET Y SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumPokeDollars() {
        return numPokeDollars;
    }

    public void setNumPokeDollars(int numPokeDollars) {
        this.numPokeDollars = numPokeDollars;
    }
}



