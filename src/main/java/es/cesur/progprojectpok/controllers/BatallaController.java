package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Movimiento;
import es.cesur.progprojectpok.clases.MovimientoAtaque;
import es.cesur.progprojectpok.clases.Pokemon;
import es.cesur.progprojectpok.clases.Tipo;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.collections.FXCollections;
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

public class BatallaController {
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


    public void initialize() {
        cargarEquipoDesdeBD();
        crearEquipoRival();
        comprobarInicio();

        setPokemonRivalEnCombate(equipoRival.get(0));
        setPokemonJugadorEnCombate(equipo.get(0));

        Pokemon primerPokemon = equipo.get(0);

        int pokemonId = primerPokemon.getNumPokedex();

        //Cargar la imagen del primer Pokemon del jugador
        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonJugador.setImage(image);

        //Cargar la imagen del primer Pokemon del rival
        pokemonId = pokemonRivalEnCombate.getNumPokedex();
        String imageUrlRival = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
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
    }


    private List<Pokemon> cargarPokemonesDesdeBD(int cajaId) {
        List<Pokemon> pokemones = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM POKEMON WHERE CAJA = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cajaId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("MOTE");
                int numPokedex = resultSet.getInt("NUM_POKEDEX");
                int ataque = resultSet.getInt("ATAQUE");
                int defensa = resultSet.getInt("DEFENSA");
                int ataqueEspecial = resultSet.getInt("AT_ESPECIAL");
                int defensaEspecial = resultSet.getInt("DEF_ESPECIAL");
                int velocidad = resultSet.getInt("VELOCIDAD");
                int nivel = resultSet.getInt("NIVEL");
                int vitalidad = resultSet.getInt("VITALIDAD");
                int idPokemon = resultSet.getInt("ID_POKEMON");
                int vidaActual = resultSet.getInt("VIDA_ACTUAL");

                Pokemon pokemon = new Pokemon(numPokedex, nombre, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad, nivel, vitalidad, vidaActual, idPokemon);
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
        Random random = new Random();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT NOM_POKEMON FROM POKEDEX WHERE NUM_POKEDEX = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; i < 6; i++) {
                int numPokedex = random.nextInt(151) + 1;

                statement.setInt(1, numPokedex);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String nombrePokemon = resultSet.getString("NOM_POKEMON");
                    Pokemon pokemonAleatorio = new Pokemon(numPokedex,nombrePokemon,0, 0, 0, 0, 0, 0, 10,  10, 0);
                    pokemonAleatorio.setVidaActual(pokemonAleatorio.getVitalidad());
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

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT M.* " +
                    "FROM MOVIMIENTOS M " +
                    "INNER JOIN MOVIMIENTOS_POKEMON MP " +
                    "ON M.ID_MOVIMIENTO = MP.ID_MOVIMIENTO " +
                    "WHERE MP.ID_POKEMON = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idPokemon);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Leer los datos del movimiento desde la base de datos
                // Crear un objeto Movimiento con los datos leídos
                Movimiento movimiento = new MovimientoAtaque(
                        resultSet.getInt("TURNOS"),
                        resultSet.getString("NOM_MOVIMIENTO"),
                        Tipo.TipoStringToEnum(resultSet.getString("TIPO")),
                        resultSet.getInt("POTENCIA")
                );
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
        }

        if (movimientosPokemon.size() >= 2) {
            ataque2.setText(movimientosPokemon.get(1).getNombre());
            ataque2.setDisable(false);
        } else {
            ataque2.setDisable(true);
        }

        if (movimientosPokemon.size() >= 3) {
            ataque3.setText(movimientosPokemon.get(2).getNombre());
            ataque3.setDisable(false);
        } else {
            ataque3.setDisable(true);
        }

        if (movimientosPokemon.size() >= 4) {
            ataque4.setText(movimientosPokemon.get(3).getNombre());
            ataque4.setDisable(false);
        } else {
            ataque4.setDisable(true);
        }
    }



    @FXML
    private void ataqueOnAction1(ActionEvent event) {

        System.out.println(pokemonJugadorEnCombate.getNombre());
        equipoRivalSinPokemones();

        if (equipoRivalSinPokemones()){
            log.appendText("El combate ha finalizado.\n");
        } else if (!equipoRivalSinPokemones()) {

            if (turnoJugador) {

                Movimiento movimiento = movimientosPokemon.get(0);

                if (movimiento instanceof MovimientoAtaque) {
                    //Si es un MovimientoAtaque, llamar al método usarMovimiento
                    ((MovimientoAtaque) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                }

                log.appendText("Se ha utilizado " + movimientosPokemon.get(0).getNombre() + "\n");
                turnoJugador = false;

                combrobarVidaPokemon();

                verificarGanador();


                actualizarBarraProgresoRival();

                realizarAccionRival();
            } else
                log.appendText("No es tu turno \n");
        }
    }

    @FXML
    private void ataqueOnAction2(ActionEvent event) {
        equipoRivalSinPokemones();

        if (equipoRivalSinPokemones()){
            log.appendText("El combate ha finalizado.\n");
        } else if (!equipoRivalSinPokemones()) {

            if (turnoJugador) {

                Movimiento movimiento = movimientosPokemon.get(1);

                if (movimiento instanceof MovimientoAtaque) {
                    //Si es un MovimientoAtaque, llamar al método usarMovimiento
                    ((MovimientoAtaque) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                }

                log.appendText("Se ha utilizado " + movimientosPokemon.get(1).getNombre() + "\n");
                turnoJugador = false;

                combrobarVidaPokemon();

                verificarGanador();


                actualizarBarraProgresoRival();

                realizarAccionRival();
            } else
                log.appendText("No es tu turno \n");
        }
    }

    @FXML
    private void ataqueOnAction3(ActionEvent event) {
        equipoRivalSinPokemones();

        if (equipoRivalSinPokemones()){
            log.appendText("El combate ha finalizado.\n");
        } else if (!equipoRivalSinPokemones()) {

            if (turnoJugador) {

                Movimiento movimiento = movimientosPokemon.get(2);

                if (movimiento instanceof MovimientoAtaque) {
                    //Si es un MovimientoAtaque, llamar al método usarMovimiento
                    ((MovimientoAtaque) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                }

                log.appendText("Se ha utilizado " + movimientosPokemon.get(2).getNombre() + "\n");
                turnoJugador = false;

                combrobarVidaPokemon();

                verificarGanador();


                actualizarBarraProgresoRival();

                realizarAccionRival();
            } else
                log.appendText("No es tu turno \n");
        }
    }

    @FXML
    private void ataqueOnAction4(ActionEvent event) {

        equipoRivalSinPokemones();

        if (equipoRivalSinPokemones()){
            log.appendText("El combate ha finalizado.\n");
        } else if (!equipoRivalSinPokemones()) {

            if (turnoJugador) {

                Movimiento movimiento = movimientosPokemon.get(3);

                if (movimiento instanceof MovimientoAtaque) {
                    //Si es un MovimientoAtaque, llamar al método usarMovimiento
                    ((MovimientoAtaque) movimiento).usarMovimiento(pokemonJugadorEnCombate, pokemonRivalEnCombate);
                }

                log.appendText("Se ha utilizado " + movimientosPokemon.get(3).getNombre() + "\n");
                turnoJugador = false;

                combrobarVidaPokemon();

                verificarGanador();


                actualizarBarraProgresoRival();

                realizarAccionRival();
            } else
                log.appendText("No es tu turno \n");
        }
    }


    private void realizarAccionRival() {

        //Logica del ataque rival

        turnoJugador = true;
    }

    private void comprobarInicio(){
        if (equipo.get(0).getVelocidad() > equipoRival.get(0).getVelocidad()){
            turnoJugador = true;
        }else
            turnoJugador = false;
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

    public void combrobarVidaPokemon(){

        if (pokemonRivalEnCombate.getVidaActual() == 0){
            int indiceActual = equipoRival.indexOf(pokemonRivalEnCombate);
            if (indiceActual != -1 && indiceActual < equipoRival.size() - 1) {
                Pokemon siguientePokemon = equipoRival.get(indiceActual + 1);
                setPokemonRivalEnCombate(siguientePokemon);

                cambiarInfoPokemonRival();
            }
        }

    }


    private  void cambiarInfoPokemon(){
        int pokemonId = pokemonJugadorEnCombate.getNumPokedex();
        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonJugador.setImage(image);

        String nomPokemon = pokemonJugadorEnCombate.getNombre();
        nomPokemonJugador.setText(nomPokemon);

        int nivPokemon = pokemonJugadorEnCombate.getNivel();
        nivPokemonJugador.setText("LVL " + nivPokemon);

        actualizarBotonesAtaque();
    }

    private  void cambiarInfoPokemonRival(){
        int pokemonId = pokemonRivalEnCombate.getNumPokedex();
        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
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

    private void verificarGanador() {
        if (equipoRivalSinPokemones()) {

            log.appendText("¡Felicidades! Has ganado la batalla.\n");
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
            });

            menuStage.show();
        } catch (IOException e) {
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
            menuStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





