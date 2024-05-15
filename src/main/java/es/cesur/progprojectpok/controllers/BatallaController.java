package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.*;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BatallaController {
    public static final String URL_IMAGEN = "/es/cesur/progprojectpok/images/pokemon/%03d.png";
    public static final String NUM_USOS = "NUM_USOS";
    public static final String NOM_MOVIMIENTO = "NOM_MOVIMIENTO";
    public static final String TIPO = "TIPO";
    public static final String TURNOS = "TURNOS";
    public static final String ATAQUE = "ATAQUE";
    public static final String ESTADO = "ESTADO";
    public static final String MEJORA = "MEJORA";
    public static final String HA_UTILIZADO = " ha utilizado ";
    public static final String NO_ES_TU_TURNO = "No es tu turno \n";
    public static final String EL_COMBATE_HA_FINALIZADO = "El combate ha finalizado.\n";
    public static final String USOS = "Usos: ";
    @FXML
    private Button ataque1;
    @FXML
    private Button ataque2;
    @FXML
    private Button ataque3;
    @FXML
    private Button ataque4;

    @FXML
    private TextArea log;

    @FXML
    private ImageView pokemonJugador;

    @FXML
    private ImageView pokemonRival;

    @FXML
    private Button huirBatallaButton;

    private List<Pokemon> equipo;

    private List<Pokemon> equipoRival;

    @FXML
    private Text nomPokemonJugador;

    @FXML
    private Text nivPokemonJugador;

    @FXML
    private Text nomPokemonRival;

    @FXML
    private Text nivPokemonRival;

    @FXML
    private ProgressBar progressJugador;

    @FXML
    private ProgressBar progressRival;

    private List<Movimiento> movimientosPokemon;

    @FXML
    private Button cambiarPokemon;

    private boolean turnoJugador;

    private Pokemon pokemonRivalEnCombate; //variable para almacenar el pokemon rival actualmente en combate
    private  Pokemon pokemonJugadorEnCombate; //Variable para almacenar nuestro pokemon en combate

    private static final int TIEMPO_DE_ESPERA = 2000;

    private Entrenador entrenador;

    @FXML
    private Text usosAt1;

    @FXML
    private Text usosAt2;

    @FXML
    private Text usosAt3;

    @FXML
    private Text usosAt4;

    private int indicePokemonCambiar;

    Random rd = new Random();




    public void initialize() {
        cargarEquipoDesdeBD();

        setPokemonJugadorEnCombate(equipo.get(0));

        crearEquipoRival();

        setPokemonRivalEnCombate(equipoRival.get(0));



        Pokemon primerPokemon = equipo.get(0);

        int pokemonId = primerPokemon.getNumPokedex();

        //Cargar la imagen del primer Pokemon del jugador
        String imageUrl = String.format(URL_IMAGEN, pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonJugador.setImage(image);

        //Cargar la imagen del primer Pokemon del rival
        pokemonId = pokemonRivalEnCombate.getNumPokedex();
        String imageUrlRival = String.format(URL_IMAGEN, pokemonId);
        Image imageRival = new Image(getClass().getResource(imageUrlRival).toExternalForm());
        pokemonRival.setImage(imageRival);

        //Cargar nombre y nivel del primer pokemon del jugador
        String nomPokemon = primerPokemon.getNombre();
        nomPokemonJugador.setText(nomPokemon);

        int nivPokemon = primerPokemon.getNivel();
        nivPokemonJugador.setText("LVL " + nivPokemon);

        //Cargar nombre y nivel del primer pokemon del rival
        nomPokemon = pokemonRivalEnCombate.getNombre();
        nomPokemonRival.setText(nomPokemon);

        nivPokemon = pokemonRivalEnCombate.getNivel();
        nivPokemonRival.setText("LVL " + nivPokemon);

        //Barra progreso jugador
        double vidaActual = primerPokemon.getVidaActual();
        double vidaTotal = primerPokemon.getVitalidad();

        double porcentajeVida = vidaActual / vidaTotal;

        progressJugador.setProgress(porcentajeVida);

        //Barra progreso rival
        double vidaActualRival = pokemonRivalEnCombate.getVidaActual();
        double vidaTotalRival = pokemonRivalEnCombate.getVitalidad();

        double porcentajeVidaRival = vidaActualRival / vidaTotalRival;

        progressRival.setProgress(porcentajeVidaRival);

        //Cargar ataques

        pokemonId = primerPokemon.getIdPokemon();
        cargarMovimientosDesdeBD(pokemonId);

        //Cargar movimientos en botones

        actualizarBotonesAtaque();

        actualizarNumUsos();

        comprobarInicio();

    }


    private List<Pokemon> cargarPokemonesDesdeBD(int cajaId) {
        List<Pokemon> pokemones = new ArrayList<>();

        String sql = "SELECT * FROM POKEMON WHERE CAJA = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, cajaId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("MOTE");
                int numPokedex = resultSet.getInt("NUM_POKEDEX");
                int ataque = resultSet.getInt(ATAQUE);
                int defensa = resultSet.getInt("DEFENSA");
                int ataqueEspecial = resultSet.getInt("AT_ESPECIAL");
                int defensaEspecial = resultSet.getInt("DEF_ESPECIAL");
                int velocidad = resultSet.getInt("VELOCIDAD");
                int nivel = resultSet.getInt("NIVEL");
                int vitalidad = resultSet.getInt("VITALIDAD");
                int idPokemon = resultSet.getInt("ID_POKEMON");
                int vidaActual = resultSet.getInt("VIDA_ACTUAL");
                int experiencia = resultSet.getInt("EXPERIENCIA");


                Pokemon pokemon = new Pokemon(numPokedex, nombre, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad, nivel, vitalidad, vidaActual, idPokemon, experiencia);
                pokemones.add(pokemon);

            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los Pokémon desde la base de datos: " + e.getMessage());
            e.printStackTrace();
        }


        return pokemones;
    }


    private void cargarEquipoDesdeBD() {
        equipo = cargarPokemonesDesdeBD(0);
    }


    //Generar equipo rival
    private void crearEquipoRival() {
        equipoRival = new ArrayList<>();

        String sql = "SELECT NOM_POKEMON FROM POKEDEX WHERE NUM_POKEDEX = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (int i = 0; i < 6; i++) {
                int numPokedex = rd.nextInt(151) + 1;

                int vida = rd.nextInt(30);

                statement.setInt(1, numPokedex);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String nombrePokemon = resultSet.getString("NOM_POKEMON");
                    Pokemon pokemonAleatorio = new Pokemon(numPokedex,nombrePokemon,rd.nextInt(30), rd.nextInt(30), rd.nextInt(30), rd.nextInt(30), rd.nextInt(30), pokemonJugadorEnCombate.getNivel(), vida,  vida, 0,10);
                    pokemonAleatorio.setVidaActual(pokemonAleatorio.getVitalidad());
                    cargarMovimientosRival(pokemonAleatorio);
                    equipoRival.add(pokemonAleatorio);
                } else {
                    System.out.println("No se encontró el Pokémon con el ID de Pokédex: " + numPokedex);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al crear el equipo del rival: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Método para cargar los movimientos del Pokémon desde la base de datos
    private void cargarMovimientosDesdeBD(int idPokemon) {
        movimientosPokemon = new ArrayList<>();

        String sql = "SELECT M.* " +
                "FROM MOVIMIENTOS M " +
                "INNER JOIN MOVIMIENTOS_POKEMON MP " +
                "ON M.ID_MOVIMIENTO = MP.ID_MOVIMIENTO " +
                "WHERE MP.ID_POKEMON = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idPokemon);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String tipoMovimiento = resultSet.getString("TIPO_MOVIMIENTO");
                Movimiento movimiento;

                movimiento = getMovimiento(tipoMovimiento, resultSet);

                movimientosPokemon.add(movimiento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void actualizarBotonesAtaque() {
        if (movimientosPokemon.size() >= 1) {
            ataque1.setText(movimientosPokemon.get(0).getNombre());
            ataque1.setDisable(false);
        } else {
            ataque1.setDisable(true);
            ataque1.setText("");
        }

        if (movimientosPokemon.size() >= 2) {
            ataque2.setText(movimientosPokemon.get(1).getNombre());
            ataque2.setDisable(false);
        } else {
            ataque2.setDisable(true);
            ataque2.setText("");
        }

        if (movimientosPokemon.size() >= 3) {
            ataque3.setText(movimientosPokemon.get(2).getNombre());
            ataque3.setDisable(false);
        } else {
            ataque3.setDisable(true);
            ataque3.setText("");
        }

        if (movimientosPokemon.size() >= 4) {
            ataque4.setText(movimientosPokemon.get(3).getNombre());
            ataque4.setDisable(false);
        } else {
            ataque4.setDisable(true);
            ataque4.setText("");
        }
    }



    @FXML
    private void ataqueOnAction1(ActionEvent event) {

        equipoRivalSinPokemones();
        equipoJugadorSinPokemones();
        Movimiento movimiento = movimientosPokemon.get(0);

        if (equipoRivalSinPokemones() || equipoJugadorSinPokemones()){
            log.appendText(EL_COMBATE_HA_FINALIZADO);
        } else if (!equipoRivalSinPokemones() && !equipoJugadorSinPokemones()) {

            if (turnoJugador && movimiento.getNumUsos() > 0) {

                if (movimiento instanceof MovimientoAtaque) {
                    //Si es un MovimientoAtaque, llamar al método usarMovimiento
                    ((MovimientoAtaque) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                } else if (movimiento instanceof MovimientoMejora) {

                    ((MovimientoMejora) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                }

                log.appendText(pokemonJugadorEnCombate.getNombre() + HA_UTILIZADO + movimientosPokemon.get(0).getNombre() + "\n");
                turnoJugador = false;

                combrobarVidaPokemon();

                verificarGanador();


                actualizarBarraProgresoRival();

                reducirUsos(movimiento);
                actualizarNumUsos();

                if (movimiento.getNumUsos() <= 0){
                    ataque1.setDisable(true);
                }

                esperarTurnoRival();

            } else if (!turnoJugador) {
                log.appendText(NO_ES_TU_TURNO);
            }
        }
    }

    @FXML
    private void ataqueOnAction2(ActionEvent event) {
        equipoRivalSinPokemones();
        equipoJugadorSinPokemones();

        Movimiento movimiento = movimientosPokemon.get(1);


        if (equipoRivalSinPokemones() || equipoJugadorSinPokemones()){
            log.appendText(EL_COMBATE_HA_FINALIZADO);
        } else if (!equipoRivalSinPokemones() && !equipoJugadorSinPokemones()) {

            if (turnoJugador && movimiento.getNumUsos() > 0) {

                if (movimiento instanceof MovimientoAtaque) {
                    //Si es un MovimientoAtaque, llamar al método usarMovimiento
                    ((MovimientoAtaque) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                } else if (movimiento instanceof MovimientoMejora) {

                    ((MovimientoMejora) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                }

                log.appendText(pokemonJugadorEnCombate.getNombre() + HA_UTILIZADO + movimientosPokemon.get(1).getNombre() + "\n");
                turnoJugador = false;

                combrobarVidaPokemon();

                verificarGanador();


                actualizarBarraProgresoRival();

                reducirUsos(movimiento);
                actualizarNumUsos();

                if (movimiento.getNumUsos() <= 0){
                    ataque1.setDisable(true);
                }

                esperarTurnoRival();
            } else if (!turnoJugador) {
                log.appendText(NO_ES_TU_TURNO);
            }
        }
    }

    @FXML
    private void ataqueOnAction3(ActionEvent event) {
        equipoRivalSinPokemones();
        equipoJugadorSinPokemones();

        Movimiento movimiento = movimientosPokemon.get(2);


        if (equipoRivalSinPokemones() || equipoJugadorSinPokemones()){
            log.appendText(EL_COMBATE_HA_FINALIZADO);
        } else if (!equipoRivalSinPokemones() && !equipoJugadorSinPokemones()) {

            if (turnoJugador && movimiento.getNumUsos() > 0) {


                if (movimiento instanceof MovimientoAtaque) {
                    //Si es un MovimientoAtaque, llamar al método usarMovimiento
                    ((MovimientoAtaque) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                } else if (movimiento instanceof MovimientoMejora) {

                    ((MovimientoMejora) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                }

                log.appendText(pokemonJugadorEnCombate.getNombre() + HA_UTILIZADO + movimientosPokemon.get(2).getNombre() + "\n");
                turnoJugador = false;

                combrobarVidaPokemon();

                verificarGanador();


                actualizarBarraProgresoRival();

                reducirUsos(movimiento);
                actualizarNumUsos();

                if (movimiento.getNumUsos() <= 0){
                    ataque1.setDisable(true);
                }

                esperarTurnoRival();
            } else if (!turnoJugador) {
                log.appendText(NO_ES_TU_TURNO);
            }
        }
    }

    @FXML
    private void ataqueOnAction4(ActionEvent event) {

        equipoRivalSinPokemones();
        equipoJugadorSinPokemones();

        Movimiento movimiento = movimientosPokemon.get(3);


        if (equipoRivalSinPokemones() || equipoJugadorSinPokemones()){
            log.appendText(EL_COMBATE_HA_FINALIZADO);
        } else if (!equipoRivalSinPokemones() && !equipoJugadorSinPokemones()) {

            if (turnoJugador && movimiento.getNumUsos() > 0) {


                if (movimiento instanceof MovimientoAtaque) {
                    //Si es un MovimientoAtaque, llamar al método usarMovimiento
                    ((MovimientoAtaque) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                } else if (movimiento instanceof MovimientoMejora) {

                    ((MovimientoMejora) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                }

                log.appendText(pokemonJugadorEnCombate.getNombre() + HA_UTILIZADO + movimientosPokemon.get(3).getNombre() + "\n");
                turnoJugador = false;

                combrobarVidaPokemon();

                verificarGanador();


                actualizarBarraProgresoRival();

                reducirUsos(movimiento);
                actualizarNumUsos();

                if (movimiento.getNumUsos() <= 0){
                    ataque1.setDisable(true);
                }

                esperarTurnoRival();
            } else if (!turnoJugador) {
                log.appendText(NO_ES_TU_TURNO);
            }
        }
    }


    private void realizarAccionRival() {

        int indiceMovimientoAleatorio = rd.nextInt(pokemonRivalEnCombate.getMovimientos().size());

        //Selecciona el movimiento aleatorio que va a usar el rival
        Movimiento movimientoRival = pokemonRivalEnCombate.getMovimientos().get(indiceMovimientoAleatorio);

        if (movimientoRival instanceof MovimientoAtaque) {
            ((MovimientoAtaque) movimientoRival).usarMovimiento(pokemonRivalEnCombate, pokemonJugadorEnCombate);
        }else if (movimientoRival instanceof MovimientoMejora) {

            ((MovimientoMejora) movimientoRival).usarMovimiento(pokemonRivalEnCombate, pokemonJugadorEnCombate);
        }

        log.appendText(pokemonRivalEnCombate.getNombre() + HA_UTILIZADO + movimientoRival.getNombre() + "\n");

        combrobarVidaPokemonJugador();

        verificarGanador();

        actualizarBarraProgresoJugador();

        turnoJugador = true; //Cambiar el turno al jugador después de que el rival haya realizado su movimiento
    }

    private void comprobarInicio(){
        if (equipo.get(0).getVelocidad() > equipoRival.get(0).getVelocidad()){
            turnoJugador = true;
            log.appendText("¡Es tu turno! ¡Realiza el primer ataque!\n");
        }else {
            turnoJugador = false;
            log.appendText("¡Es el turno del rival! ¡Prepárate!\n");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {

                        realizarAccionRival();

                    });
                }
            }, TIEMPO_DE_ESPERA);
        }
    }

    private void setPokemonRivalEnCombate(Pokemon pokemon) {
        this.pokemonRivalEnCombate = pokemon;
    }

    public Pokemon getPokemonRivalEnCombate() {
        return pokemonRivalEnCombate;
    }

    public Pokemon getPokemonJugadorEnCombate() {
        return pokemonJugadorEnCombate;
    }

    public void setPokemonJugadorEnCombate(Pokemon pokemonJugadorEnCombate) {
        this.pokemonJugadorEnCombate = pokemonJugadorEnCombate;
    }

    private void actualizarBarraProgresoRival() {
        if (pokemonRivalEnCombate != null) {
            double vidaActualRival = pokemonRivalEnCombate.getVidaActual();
            double vidaTotalRival = pokemonRivalEnCombate.getVitalidad();

            double porcentajeVidaRival = vidaActualRival / vidaTotalRival;

            progressRival.setProgress(porcentajeVidaRival);
        }
    }

    private void actualizarBarraProgresoJugador() {
        if (pokemonJugadorEnCombate != null) {
            double vidaActual = pokemonJugadorEnCombate.getVidaActual();
            double vidaTotal = pokemonJugadorEnCombate.getVitalidad();

            double porcentajeVidaRival = vidaActual/ vidaTotal;

            progressJugador.setProgress(porcentajeVidaRival);
        }
    }

    public void combrobarVidaPokemon(){

        if (pokemonRivalEnCombate.getVidaActual() <= 0){

            darExperiencia(pokemonJugadorEnCombate, pokemonRivalEnCombate);

            int indiceActual = equipoRival.indexOf(pokemonRivalEnCombate);
            if (indiceActual != -1 && indiceActual < equipoRival.size() - 1) {
                Pokemon siguientePokemon = equipoRival.get(indiceActual + 1);
                setPokemonRivalEnCombate(siguientePokemon);

                cambiarInfoPokemonRival();
            }
        }

    }

    public void combrobarVidaPokemonJugador(){

        if (pokemonJugadorEnCombate.getVidaActual() <= 0){
            int indiceActual = equipo.indexOf(pokemonJugadorEnCombate);
            if (indiceActual != -1 && indiceActual < equipo.size() - 1) {
                Pokemon siguientePokemon = equipo.get(indiceActual + 1);
                setPokemonJugadorEnCombate(siguientePokemon);

                cambiarInfoPokemon();
            }
        }

    }


    private void cambiarInfoPokemon() {

        int pokemonId = pokemonJugadorEnCombate.getNumPokedex();
        String imageUrl = String.format(URL_IMAGEN, pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonJugador.setImage(image);

        String nomPokemon = pokemonJugadorEnCombate.getNombre();
        nomPokemonJugador.setText(nomPokemon);

        int nivPokemon = pokemonJugadorEnCombate.getNivel();
        nivPokemonJugador.setText("LVL " + nivPokemon);

        cargarMovimientosDesdeBD(pokemonJugadorEnCombate.getIdPokemon());

        actualizarBarraProgresoJugador();

        actualizarBotonesAtaque();

        actualizarNumUsos();

    }

    private  void cambiarInfoPokemonRival(){
        int pokemonId = pokemonRivalEnCombate.getNumPokedex();
        String imageUrl = String.format(URL_IMAGEN, pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonRival.setImage(image);

        String nomPokemon = pokemonRivalEnCombate.getNombre();
        nomPokemonRival.setText(nomPokemon);

        int nivPokemon = pokemonRivalEnCombate.getNivel();
        nivPokemonRival.setText("LVL " + nivPokemon);
    }

    private boolean equipoRivalSinPokemones() {
        for (Pokemon pokemon : equipoRival) {
            if (pokemon.getVidaActual() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean equipoJugadorSinPokemones() {
        for (Pokemon pokemon : equipo) {
            if (pokemon.getVidaActual() > 0) {
                return false;
            }
        }
        return true;
    }

    private void verificarGanador() {
        if (equipoRivalSinPokemones()) {

            log.appendText("¡Felicidades! Has ganado la batalla.\n");
        } else if (equipoJugadorSinPokemones()) {

            log.appendText("¡Mala Suerte! Has perdido la batalla.\n");
        }
    }

    @FXML
    private void cambiarPokemonOnAction(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/elegirPokemonCombate-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 590, 600);
            ElegirPokemonCombateController elegirPokemonCombateController = fxmlLoader.getController();
            elegirPokemonCombateController.setBatallaController(this);
            Stage menuStage = new Stage();
            menuStage.setTitle("Elegir Pokemon");
            menuStage.setScene(scene);

            menuStage.setOnHidden(event -> {
                cambiarInfoPokemon(); //Llamada al método cambiarInfoPokemon() al volver de la otra pantalla
                cargarMovimientosDesdeBD(pokemonJugadorEnCombate.getIdPokemon());
                actualizarBotonesAtaque();
                actualizarNumUsos();

                equipo.set(indicePokemonCambiar, pokemonJugadorEnCombate);

            });

            menuStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void cargarMovimientosRival(Pokemon pokemon) {
        List<Movimiento> movimientos = new ArrayList<>();

        String sql = "SELECT * FROM MOVIMIENTOS ORDER BY RAND() LIMIT 4";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String tipoMovimiento = resultSet.getString("TIPO_MOVIMIENTO");
                Movimiento movimiento;

                movimiento = getMovimiento(tipoMovimiento, resultSet);

                movimientos.add(movimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pokemon.setMovimientos(movimientos);
    }

    private static Movimiento getMovimiento(String tipoMovimiento, ResultSet resultSet) throws SQLException {
        Movimiento movimiento;
        switch (tipoMovimiento) {
            case ATAQUE:
                movimiento = new MovimientoAtaque(
                        resultSet.getInt(NUM_USOS),
                        resultSet.getString(NOM_MOVIMIENTO),
                        Tipo.TipoStringToEnum(resultSet.getString(TIPO)),
                        resultSet.getInt("POTENCIA")
                );
                break;
            case ESTADO:
                movimiento = new MovimientoEstado(
                        resultSet.getInt(NUM_USOS),
                        resultSet.getString(NOM_MOVIMIENTO),
                        Estado.EstadoStringToEnum(resultSet.getString(ESTADO)),
                        resultSet.getInt(TURNOS)
                );
                break;
            case MEJORA:
                movimiento = new MovimientoMejora(
                        resultSet.getInt(NUM_USOS),
                        resultSet.getString(NOM_MOVIMIENTO),
                        resultSet.getInt(TURNOS),
                        CambiosEstado.CambioEstadoStringToEnum(resultSet.getString(MEJORA)),
                        resultSet.getInt("CANT_MEJORA")
                );
                break;
            default:
                throw new IllegalArgumentException("Tipo de movimiento desconocido: " + tipoMovimiento);
        }
        return movimiento;
    }


    private void esperarTurnoRival() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    realizarAccionRival();

                    verificarGanador();

                    turnoJugador = true;
                });
            }
        }, TIEMPO_DE_ESPERA);
    }


    private void darExperiencia(Pokemon pokemon, Pokemon rival){

        int experienciaGanada = (pokemon.getNivel() + rival.getNivel() * 10) / 4;

        pokemon.setExperiencia(pokemon.getExperiencia() + experienciaGanada);

        actualizarExperienciaBD(pokemon);

        comprobarSubidaNivel(pokemon);

    }


    public void actualizarExperienciaBD(Pokemon pokemon) {
        String sql = "UPDATE POKEMON SET EXPERIENCIA = ? WHERE ID_POKEMON = ?";


        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pokemon.getExperiencia());
            statement.setInt(2, pokemon.getIdPokemon());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar la experiencia del Pokemon en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    private void huirBatallaOnAction() {

        Stage stage = (Stage) huirBatallaButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 590, 600);
            Stage menuStage = new Stage();
            menuStage.setTitle("Menu");
            menuStage.setScene(scene);
            MenuController menuController = fxmlLoader.getController();
            menuController.setEntrenador(entrenador);
            menuStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void comprobarSubidaNivel(Pokemon pokemon) {
        int experienciaRequerida = pokemon.getNivel() * 10;

        if (pokemon.getExperiencia() >= experienciaRequerida) {

            pokemon.setNivel(pokemon.getNivel() + 1);

            actualizarNivelBD(pokemon);

            int experienciaRestante = pokemon.getExperiencia() - experienciaRequerida;
            pokemon.setExperiencia(experienciaRestante);

            actualizarExperienciaBD(pokemon);

            subirEstadisticas(pokemon);

            log.appendText(pokemon.getNombre() + " ha subido de nivel.\n");

            comprobarAprenderMov(pokemon);
        }
    }


    public void actualizarNivelBD(Pokemon pokemon) {

        String sql = "UPDATE POKEMON SET NIVEL = ? WHERE ID_POKEMON = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pokemon.getNivel());
            statement.setInt(2, pokemon.getIdPokemon());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el nivel del Pokemon en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void subirEstadisticas(Pokemon pokemon){



        String sql = "UPDATE POKEMON SET ATAQUE = ?, AT_ESPECIAL = ?, DEFENSA = ?, DEF_ESPECIAL = ?, VITALIDAD = ?, VELOCIDAD = ? WHERE ID_POKEMON = ?";


        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pokemon.getAtaque() + rd.nextInt(5) );
            statement.setInt(2, pokemon.getAtaqueEspecial() + rd.nextInt(5) );
            statement.setInt(3, pokemon.getDefensa() + rd.nextInt(5) );
            statement.setInt(4, pokemon.getDefensaEspecial() + rd.nextInt(5) );
            statement.setInt(5, pokemon.getVitalidad() + rd.nextInt(5) );
            statement.setInt(6, pokemon.getVelocidad() + rd.nextInt(5) );
            statement.setInt(7, pokemon.getIdPokemon());


            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar las estadisticas del Pokemon en la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void actualizarNumUsos() {
        if (movimientosPokemon.size() >= 1) {
            usosAt1.setText(USOS + String.valueOf(movimientosPokemon.get(0).getNumUsos()));
        }else {
            usosAt1.setText("");
        }

        if (movimientosPokemon.size() >= 2) {
            usosAt2.setText(USOS + String.valueOf(movimientosPokemon.get(1).getNumUsos()));
        }else {
            usosAt2.setText("");
        }

        if (movimientosPokemon.size() >= 3) {
            usosAt3.setText(USOS + String.valueOf(movimientosPokemon.get(2).getNumUsos()));
        }else {
            usosAt3.setText("");
        }

        if (movimientosPokemon.size() >= 4) {
            usosAt4.setText(USOS + String.valueOf(movimientosPokemon.get(3).getNumUsos()));
        }else {
            usosAt4.setText("");
        }
    }

    private void reducirUsos(Movimiento movimiento){

        movimiento.setNumUsos(movimiento.getNumUsos() - 1);

    }


    private void comprobarAprenderMov(Pokemon pokemon){

        if ((pokemon.getNivel() % 3) == 0){

            String sql = "SELECT * FROM MOVIMIENTOS " +
                    "WHERE NOT EXISTS (" +
                    "    SELECT *" +
                    "    FROM MOVIMIENTOS_POKEMON MP" +
                    "    WHERE MP.ID_MOVIMIENTO = MOVIMIENTOS.ID_MOVIMIENTO AND" +
                    "    MP.ID_POKEMON = ?" +
                    ")" +
                    "ORDER BY RAND() LIMIT 1";

            String sql2 = "INSERT INTO MOVIMIENTOS_POKEMON (ID_MOVIMIENTO, ID_POKEMON, ACTIVO) VALUES (?, ?, ?)";



            try(Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                PreparedStatement statement2 = connection.prepareStatement(sql2)){

                statement.setInt(1, pokemon.getIdPokemon());

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {

                    int numMovimiento= resultSet.getInt("ID_MOVIMIENTO");

                    statement2.setInt(1, numMovimiento);
                    statement2.setInt(2, pokemon.getIdPokemon());
                    statement2.setString(3, "N");

                    statement2.executeUpdate();

                }

                log.appendText(pokemon.getNombre() + " ha aprendido un nuevo movimiento.\n");

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    public int getIndicePokemonCambiar() {
        return indicePokemonCambiar;
    }

    public void setIndicePokemonCambiar(int indicePokemonCambiar) {
        this.indicePokemonCambiar = indicePokemonCambiar;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
}





