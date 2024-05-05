package es.cesur.progprojectpok.controllers;
import es.cesur.progprojectpok.clases.Pokemon;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.fxml.FXML;
import es.cesur.progprojectpok.controllers.CrianzaController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class AsignarMoteController {

    @FXML
    private TextField motePokemon;

    @FXML
    private Button asignarMoteButton;

    @FXML
    private ImageView imagenPokemonHijo;

    private CrianzaController crianzaController;

    private Pokemon pokemonHijo;

    private Pokemon machoSeleccionado;

    public void initialize() {
        // Verifica si pokemonHijo no es nulo antes de llamar a mostrarImagenPokemon()
        if (pokemonHijo != null) {
            mostrarImagenPokemon();
        }
    }



    public void mostrarImagenPokemon() {
        int pokemonId = pokemonHijo.getNumPokedex();
        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        imagenPokemonHijo.setImage(image);
    }


    public void setPokemonHijo(Pokemon pokemonHijo) {
        this.pokemonHijo = pokemonHijo;
        if (pokemonHijo != null) {
            mostrarImagenPokemon();
        }else{
            System.err.println("El pokemon es null");
        }

    }


    public void setMachoSeleccionado(Pokemon machoSeleccionado) {
        this.machoSeleccionado =machoSeleccionado;
    }

@FXML
    public void asignarMoteOnAction() throws SQLException {

    String mote = motePokemon.getText();


    if (mote.isEmpty()) {
        mote = String.valueOf(machoSeleccionado.getNombre());
    }

    pokemonHijo.setNombre(mote);


    try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT MAX(ID_POKEMON) FROM POKEMON";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {

                int ultimoIdPokemon = resultSet.getInt(1);
                pokemonHijo.setIdPokemon(ultimoIdPokemon);


            } else {

            }

        actualizarMoteEnBD(mote);
        Stage stage = (Stage) asignarMoteButton.getScene().getWindow();
        stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void actualizarMoteEnBD(String mote) throws SQLException {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "UPDATE POKEMON SET MOTE = ? WHERE ID_POKEMON = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, mote);
                statement.setInt(2, pokemonHijo.getIdPokemon());
                System.out.println(pokemonHijo.getIdPokemon() + " " + mote);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("No se pudo actualizar el mote del Pokémon hijo");
                } else {
                    pokemonHijo.setNombre(mote);
                    System.out.println("Mote actualizado correctamente a: " + mote);;
                }
            }
        }



    }



    @FXML
    public void omitirMoteOnAction(){

    }



}
