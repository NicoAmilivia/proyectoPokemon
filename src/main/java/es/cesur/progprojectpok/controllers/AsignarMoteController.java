package es.cesur.progprojectpok.controllers;
import es.cesur.progprojectpok.clases.Pokemon;
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


public class AsignarMoteController {

    @FXML
    private TextField motePokemon;

    @FXML
    private Button asignarMoteButton;

    @FXML
    private Button omitirMoteButton;
    @FXML
    private ImageView imagenPokemonHijo;


    private Pokemon pokemonHijo;

    public void initialize(){
        // Verifica si pokemonHijo no es nulo antes de llamar a mostrarImagenPokemon()
        if (pokemonHijo != null) {
            mostrarImagenPokemon();
        }
    }

    public void mostrarImagenPokemon(){
        int pokemonId = pokemonHijo.getNumPokedex();
        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        imagenPokemonHijo.setImage(image);
    }



    public void setPokemonHijo(Pokemon pokemonHijo) {
        this.pokemonHijo = pokemonHijo;
        mostrarImagenPokemon();
    }



    @FXML
    public void asignarMoteOnAction(){


    }

    @FXML
    public void omitirMoteOnAction(){

    }










}
