package es.cesur.progprojectpok.clases;

import javax.swing.text.Element;
import java.util.List;

public class Pokemon {


    //ATRIBUTOS
    private String nombre;
    private int vitalidad;
    private int ataque;
    private int defensa;
    private int nivel;
    private int fertilidad;
    private int velocidad;
    private int experiencia;
    private char sexo;
    private int numPokedex;
    private Estado estado;
    private Tipo tipo1;
    private Tipo tipo2;

    private int ataqueEspecial;
    private int defensaEspecial;
    private static final int VALOR_INICIAL_FERT = 5;

    private int idPokemon;

    private int vidaActual;

    private List<Movimiento> movimientos;


    //CONSTRUCTOR CON PARAMETROS
    public Pokemon(String nombre, int numPokedex) {
        this.nombre = nombre;
        this.vitalidad = (int) (Math.random() * 10 + 1);
        this.ataque = (int) (Math.random() * 10 + 1);
        this.defensa = (int) (Math.random() * 10 + 1);
        this.ataqueEspecial = (int) (Math.random() * 10 + 1);
        this.defensaEspecial = (int) (Math.random() * 10 + 1);
        this.velocidad = (int) (Math.random() * 10 + 1);
        this.nivel = 1;
        this.experiencia = 0;
        this.sexo = randomSex();
        this.numPokedex = numPokedex;
        this.fertilidad = VALOR_INICIAL_FERT;
    }

    public Pokemon(String nombre, int numPokedex, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad, int nivel, int experiencia, int vitalidad, int idPokemon){
        this.nombre = nombre;
        this.numPokedex = numPokedex;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.vitalidad = vitalidad;
        this.idPokemon = idPokemon;
    }


    public Pokemon(String nombre, int numPokedex, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad, int nivel, int experiencia, int vitalidad, int idPokemon,int fertilidad){
        this.nombre = nombre;
        this.numPokedex = numPokedex;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.vitalidad = vitalidad;
        this.idPokemon = idPokemon;
        this.fertilidad=fertilidad;
    }

    public Pokemon(int numPokedex, String nombre, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad, int nivel, int vitalidad, int vidaActual, int idPokemon, int experiencia){
        this.nombre = nombre;
        this.numPokedex = numPokedex;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
        this.nivel = nivel;
        this.vitalidad = vitalidad;
        this.vidaActual = vidaActual;
        this.idPokemon = idPokemon;
        this.experiencia = experiencia;
    }


    public char randomSex() {
        char sexo;
        int sexoBinario = (int) (Math.random() * 2);
        if (sexoBinario == 0) sexo = 'H';
        else sexo = 'M';
        return sexo;
    }

    //CONSTRUCTOR POR DEFECTO

    public Pokemon() {
    }


    //GET AND SET

    public String getNombre() {
        return nombre;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVitalidad() {
        return vitalidad;
    }

    public void setVitalidad(int vitalidad) {
        this.vitalidad = vitalidad;
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getFertilidad() {
        return fertilidad;
    }

    public void setFertilidad(int fertilidad) {
        this.fertilidad = fertilidad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getNumPokedex() {
        return numPokedex;
    }

    public void setNumPokedex(int numPokedex) {
        this.numPokedex = numPokedex;
    }

    public Tipo getTipo1() {
        return tipo1;
    }

    public void setTipo1(Tipo tipo1) {
        this.tipo1 = tipo1;
    }

    public Tipo getTipo2() {
        return tipo2;
    }

    public void setTipo2(Tipo tipo2) {
        this.tipo2 = tipo2;
    }


    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }


    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public static Tipo TipoStringToEnum(String tipoString) {
        return switch (tipoString.toUpperCase()) {
            case "AGUA" -> Tipo.AGUA;
            case "BICHO" -> Tipo.BICHO;
            case "DRAGÓN" -> Tipo.DRAGON;
            case "ELÉCTRICO" -> Tipo.ELECTRICO;
            case "FANTAMAS" -> Tipo.FANTASMA;
            case "FUEGO" -> Tipo.FUEGO;
            case "HIELO" -> Tipo.HIELO;
            case "LUCHA" -> Tipo.LUCHA;
            case "NORMAL" -> Tipo.NORMAL;
            case "PLANTA" -> Tipo.PLANTA;
            case "PSÍQUICO" -> Tipo.PSIQUICO;
            case "ROCA" -> Tipo.ROCA;
            case "TIERRA" -> Tipo.TIERRA;
            case "VENENO" -> Tipo.VENENO;
            case "VOLADOR" -> Tipo.VOLADOR;
            case "NULL" -> Tipo.NULL;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "nombre='" + nombre + '\'' +
                ", vitalidad=" + vitalidad +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", nivel=" + nivel +
                ", velocidad=" + velocidad +
                ", experiencia=" + experiencia +
                ", ataqueEspecial=" + ataqueEspecial +
                ", defensaEspecial=" + defensaEspecial +
                '}';
    }






}