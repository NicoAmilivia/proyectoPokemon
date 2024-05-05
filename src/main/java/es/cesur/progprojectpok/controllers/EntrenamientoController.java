package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Entrenador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EntrenamientoController {


    @FXML
    private Button irMenuFromEntrenamientoButton;

    private Entrenador entrenador;

    @FXML
    private void irMenuFromEntrenamientoOnAction() {

        Stage stage = (Stage) irMenuFromEntrenamientoButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 590, 600);
            Stage menuStage = new Stage();
            menuStage.setTitle("Menu");
            menuStage.setScene(scene);
            MenuController menuController =  fxmlLoader.getController();
            menuController.setEntrenador(entrenador);
            menuStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Maneja el error apropiadamente
        }
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
}



