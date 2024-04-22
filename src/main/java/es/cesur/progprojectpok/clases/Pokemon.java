package es.cesur.progprojectpok.clases;

public class Pokemon {


    //ATRIBUTOS
    private String nombre;
    private int vitalidad;
    private int ataque;
    private int defensa;
    private int nivel;
    private int fertilidad;
    private Estado estado;
    private Sexo sexo;
    private String rutaImagen;
    private String rutaSonidoCombate;
    public static final int TOTAL_MOVIMIENTOS=4;
    public static final int FACTOR_SUBIDA_NVL=1;
    private int ataqueEspecial;
    private int defensaEspecial;
    private static final int VALOR_INICIAL_FERT =5;
    public static final int CRIANZA_ATTR_MIN_ALEATORIO =1;
    private static final int CRIANZA_ATTR_MAX_ALEATORIO =10;


    //CONSTRUCTOR CON PARAMETROS
    public Pokemon(String nombre, int vitalidad, int ataque, int defensa, int nivel, int fertilidad, Estado estado, Sexo sexo, String rutaImagen, String rutaSonidoCombate, int ataqueEspecial, int defensaEspecial) {
        this.nombre = nombre;
        this.vitalidad = vitalidad;
        this.ataque = ataque;
        this.defensa = defensa;
        this.nivel = nivel;
        this.fertilidad = fertilidad;
        this.estado = estado;
        this.sexo = sexo;
        this.rutaImagen = rutaImagen;
        this.rutaSonidoCombate = rutaSonidoCombate;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
    }


    //CONSTRUCTOR POR DEFECTO

    public Pokemon() {
    }


    //METODOS

    public void subirNivel(int experienciaGanada){

    }


    public void atacarPokemon(Movimiento movimientoAtaque){

    }

    public void comprobarVentajaRival(Pokemon pokemonRival){


    }

    public void aprenderMovimiento(Movimiento movActualASustituir,Movimiento movNuevoPosible){

    }


    //GET AND SET

    public String getNombre() {
        return nombre;
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getRutaSonidoCombate() {
        return rutaSonidoCombate;
    }

    public void setRutaSonidoCombate(String rutaSonidoCombate) {
        this.rutaSonidoCombate = rutaSonidoCombate;
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
}


