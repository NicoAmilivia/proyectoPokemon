package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Pokemon;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ElegirPokemonEntrenamientoController {

    @FXML
    private Button irMenuFromEPC;

    @FXML
    private Button elegirPokemonButton;

    @FXML
    private ListView<Pokemon> elegirPokemonList;

    private List<Pokemon> equipo;

    private EntrenamientoController entrenamientoController;



    public void initialize() {
        cargarEquipoDesdeBD();
        elegirPokemonList.setItems(FXCollections.observableArrayList(equipo));
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
                int ataque = resultSet.getInt("ATAQUE");
                int defensa = resultSet.getInt("DEFENSA");
                int ataqueEspecial = resultSet.getInt("AT_ESPECIAL");
                int defensaEspecial = resultSet.getInt("DEF_ESPECIAL");
                int velocidad = resultSet.getInt("VELOCIDAD");
                int nivel = resultSet.getInt("NIVEL");
                int experiencia = resultSet.getInt("EXPERIENCIA");
                int vitalidad = resultSet.getInt("VITALIDAD");
                int idPokemon = resultSet.getInt("ID_POKEMON");
                int vidaActual = resultSet.getInt("VIDA_ACTUAL");
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


    public void setEntrenamientoController(EntrenamientoController entrenamientoController) {
        this.entrenamientoController = entrenamientoController;
    }

    @FXML
    private void elegirPokemonOnAction(){
        Pokemon seleccionado = elegirPokemonList.getSelectionModel().getSelectedItem();

        if (seleccionado != null && entrenamientoController != null) {
            entrenamientoController.setPokemonJugadorEnCombate(seleccionado);
            entrenamientoController.setIndicePokemonCambiar(equipo.indexOf(seleccionado));
        }

        Stage stage = (Stage) irMenuFromEPC.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void irMenuOnAction(){

        Stage stage = (Stage) irMenuFromEPC.getScene().getWindow();
        stage.close();
    }


}
