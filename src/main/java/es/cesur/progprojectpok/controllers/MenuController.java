package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Entrenador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button irCombateButton;

    @FXML
    private Button irEquipoButton;

    @FXML
    private Button irCapturaButton;

    @FXML
    private Button irCrianzaButton;

    @FXML
    private Button irCentroButton;

    @FXML
    private Button irEntrenamientoButton;

    @FXML
    private Button irTiendaButton;


    private Entrenador entrenador;




    @FXML
    private void irCombateOnAction() {

        Stage stage = (Stage) irCombateButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/batalla-view.fxml"));
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
    private void irEquipoOnAction() {

        Stage stage = (Stage) irEquipoButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/equipo-view.fxml"));
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
    private void irCapturaOnAction() {

        Stage stage = (Stage) irCapturaButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/capturar-view.fxml"));
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
    private void irCrianzaOnAction() {

        Stage stage = (Stage) irCrianzaButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/crianza-view.fxml"));
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
    private void irCentroOnAction() {

        Stage stage = (Stage) irCentroButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/centro-view.fxml"));
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
    private void irEntrenamientoOnAction() {

        Stage stage = (Stage) irEntrenamientoButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/entrenamiento-view.fxml"));
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
    private void irTiendaOnAction(){

        Stage stage = (Stage) irTiendaButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/tienda-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 590, 600);
            Stage menuStage = new Stage();
            menuStage.setTitle("Menu");
            menuStage.setScene(scene);
            TiendaController tiendaController = fxmlLoader.getController();
            tiendaController.setEntrenador(entrenador);
            menuStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Maneja el error apropiadamente
        }



    }

    public void setEntrenador(Entrenador entrenador){
        this.entrenador=entrenador;
    }


}




