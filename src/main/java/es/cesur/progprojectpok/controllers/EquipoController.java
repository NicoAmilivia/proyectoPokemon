package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Pokemon;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoController {

    @FXML
    private Button irMenuFromEquipoButton;

    @FXML
    private ListView<Pokemon> equipoListView;

    @FXML
    private ListView<Pokemon> cajaListView;

    private List<Pokemon> equipo;
    private List<Pokemon> caja;


    public void initialize() {
        cargarEquipoDesdeBD();
        cargarCajaDesdeBD();
        equipoListView.setItems(FXCollections.observableArrayList(equipo));
        cajaListView.setItems(FXCollections.observableArrayList(caja));
    }

    @FXML
    private void transferirACaja() {
        Pokemon seleccionado = equipoListView.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            equipo.remove(seleccionado);
            caja.add(seleccionado);
            actualizarBD(seleccionado);
            equipoListView.setItems(FXCollections.observableArrayList(equipo));
            cajaListView.setItems(FXCollections.observableArrayList(caja));
        }
    }

    @FXML
    private void transferirAEquipo() {
        Pokemon seleccionado = cajaListView.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            caja.remove(seleccionado);
            equipo.add(seleccionado);
            actualizarBD(seleccionado);
            equipoListView.setItems(FXCollections.observableArrayList(equipo));
            cajaListView.setItems(FXCollections.observableArrayList(caja));
        }
    }

    private void cargarEquipoDesdeBD() {
        equipo = cargarPokemonesDesdeBD(0);
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
                Pokemon pokemon = new Pokemon(nombre, numPokedex, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad, nivel, experiencia, vitalidad);
                pokemones.add(pokemon);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los Pokémon desde la base de datos: " + e.getMessage());
            e.printStackTrace();
        }

        return pokemones;
    }

    private void cargarCajaDesdeBD() {
        caja = cargarPokemonesDesdeBD(1);
    }

    private void actualizarBD(Pokemon pokemon) {
        // Lógica para actualizar el estado del Pokémon en la base de datos
    }

    @FXML
    private void irMenuFromEquipoOnAction() {
        Stage stage = (Stage) irMenuFromEquipoButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 590, 600);
            Stage menuStage = new Stage();
            menuStage.setTitle("Menu");
            menuStage.setScene(scene);
            menuStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Maneja el error apropiadamente
        }
    }
}
