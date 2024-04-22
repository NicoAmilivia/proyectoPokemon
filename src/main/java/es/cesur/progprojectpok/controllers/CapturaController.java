package es.cesur.progprojectpok.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class CapturaController {

    @FXML
    private ImageView pokemonImageView;
    @FXML
    private Button irMenuFromCapturarButton;

    @FXML
    private Button capturarNuevo;

    public void initialize() {
        //Generar un número aleatorio para el ID del Pokémon
        Random random = new Random();
        int pokemonId = random.nextInt(151) + 1;

        // Cargar la imagen del Pokémon según el ID generado
        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonImageView.setImage(image);
    }

    @FXML
    private void irMenuFromCapturarOnAction() {

        Stage stage = (Stage) irMenuFromCapturarButton.getScene().getWindow();
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

    @FXML
    private void capturarNuevoOnAction(){
        Random random = new Random();
        int pokemonId = random.nextInt(151) + 1;

        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonImageView.setImage(image);
    }
}
