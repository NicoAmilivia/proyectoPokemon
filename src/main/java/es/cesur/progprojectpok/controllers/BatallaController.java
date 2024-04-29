package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Pokemon;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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



    public void initialize() {
        cargarEquipoDesdeBD();
        crearEquipoRival();

        Pokemon primerPokemon = equipo.get(0);
        Pokemon primerPokemonRival = equipoRival.get(0);
        int pokemonId = primerPokemon.getNumPokedex();

        //Cargar la imagen del primer Pokemon del jugador
        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonJugador.setImage(image);

        //Cargar la imagen del primer Pokemon del rival
        pokemonId = primerPokemonRival.getNumPokedex();
        String imageUrlRival = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image imageRival = new Image(getClass().getResource(imageUrlRival).toExternalForm());
        pokemonRival.setImage(imageRival);

        //Cargar nombre y nivel del primer pokemon del jugador
        String nomPokemon = primerPokemon.getNombre();
        nomPokemonJugador.setText(nomPokemon);

        int nivPokemon = primerPokemon.getNivel();
        nivPokemonJugador.setText("LVL " + nivPokemon);

        //Cargar nombre y nivel del primer pokemon del rival
        nomPokemon = primerPokemonRival.getNombre();
        nomPokemonRival.setText(nomPokemon);

        nivPokemon = primerPokemonRival.getNivel();
        nivPokemonRival.setText("LVL " + nivPokemon);
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
                int experiencia = resultSet.getInt("EXPERIENCIA");
                int vitalidad = resultSet.getInt("VITALIDAD");
                int idPokemon = resultSet.getInt("ID_POKEMON");
                Pokemon pokemon = new Pokemon(nombre, numPokedex, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad, nivel, experiencia, vitalidad, idPokemon);
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
                    Pokemon pokemonAleatorio = new Pokemon(nombrePokemon, numPokedex, 0, 0, 0, 0, 0, 1, 0, 0, 0);
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


    @FXML
    private void ataqueOnAction1(ActionEvent event) {
        log.appendText("Se ha utilizado ataque1\n");
    }

    @FXML
    private void ataqueOnAction2(ActionEvent event) {
        log.appendText("Se ha utilizado ataque2\n");
    }

    @FXML
    private void ataqueOnAction3(ActionEvent event) {
        log.appendText("Se ha utilizado ataque3\n");
    }

    @FXML
    private void ataqueOnAction4(ActionEvent event) {
        log.appendText("Se ha utilizado ataque4\n");
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





