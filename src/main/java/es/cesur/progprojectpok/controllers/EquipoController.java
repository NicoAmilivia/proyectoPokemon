package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Entrenador;
import es.cesur.progprojectpok.clases.Pokemon;
import es.cesur.progprojectpok.database.DBConnection;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

    @FXML
    private Button aplicarObjetoButton;

    @FXML
    private TextField logEquipo;

    private List<Pokemon> equipo;
    private List<Pokemon> caja;

    private Entrenador entrenador;

    private Pokemon pokemonSeleccionado;


    public void initialize() {
        cargarEquipoDesdeBD();
        cargarCajaDesdeBD();
        equipoListView.setItems(FXCollections.observableArrayList(equipo));
        cajaListView.setItems(FXCollections.observableArrayList(caja));
    }

    @FXML
    private void transferirACaja() {
        if (equipo.size() > 1) {
            Pokemon seleccionado = equipoListView.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                equipo.remove(seleccionado);
                caja.add(seleccionado);
                int idPokemon = seleccionado.getIdPokemon();
                actualizarBD(1, idPokemon);
                equipoListView.setItems(FXCollections.observableArrayList(equipo));
                cajaListView.setItems(FXCollections.observableArrayList(caja));
            }
        }else
            logEquipo.appendText("El equipo no puede quedar vacio\n");
    }

    @FXML
    private void transferirAEquipo() {
        if (equipo.size() < 6) {
            Pokemon seleccionado = cajaListView.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                caja.remove(seleccionado);
                equipo.add(seleccionado);
                int idPokemon = seleccionado.getIdPokemon();
                actualizarBD(0, idPokemon);
                equipoListView.setItems(FXCollections.observableArrayList(equipo));
                cajaListView.setItems(FXCollections.observableArrayList(caja));
            }
        }else
            logEquipo.appendText("El equipo no puede superar los 6 Pokemons\n");
    }

    private void cargarEquipoDesdeBD() {
        equipo = cargarPokemonesDesdeBD(0);
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
                Pokemon pokemon = new Pokemon(nombre, numPokedex, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad, nivel, experiencia, vitalidad, idPokemon);
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

    private void actualizarBD(int caja, int idPokemon) {

        String sql = "UPDATE POKEMON SET CAJA = ? WHERE ID_POKEMON = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setInt(1, caja);
            statement.setInt(2, idPokemon);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Pokémon actualizado en la base de datos correctamente.");
            } else {
                System.out.println("No se pudo actualizar el Pokémon en la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el Pokémon en la base de datos: " + e.getMessage());
        }
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
            MenuController menuController = fxmlLoader.getController();
            menuController.setEntrenador(entrenador);
            menuStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    @FXML
    public void aplicarObjetoOnAction() {

        if (hayPokemonSeleccionado()) {

            pokemonSeleccionado=getPokemonSeleccionado();
            Stage stage = (Stage) aplicarObjetoButton.getScene().getWindow();
            stage.close();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/mochila-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 590, 600);
                Stage menuStage = new Stage();
                menuStage.setTitle("Mochila");
                menuStage.setScene(scene);
                MochilaController mochilaController = fxmlLoader.getController();
                mochilaController.setEntrenadorandPokemon(entrenador,pokemonSeleccionado);
                menuStage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Maneja el error apropiadamente
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else {
            logEquipo.appendText("No has seleccionado ningun Pokemon\n");
        }
    }


    public Pokemon getPokemonSeleccionado() {

        Pokemon pokemonSeleccionado = equipoListView.getSelectionModel().getSelectedItem();

        if (pokemonSeleccionado == null) {
            pokemonSeleccionado = cajaListView.getSelectionModel().getSelectedItem();
        }

        return pokemonSeleccionado;
    }

    public boolean hayPokemonSeleccionado() {
        return equipoListView.getSelectionModel().getSelectedItem() != null || cajaListView.getSelectionModel().getSelectedItem() != null;
    }

}
