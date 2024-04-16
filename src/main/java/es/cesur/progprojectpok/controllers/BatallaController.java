package es.cesur.progprojectpok.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

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
    private Button huirBatallaButton;

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
            e.printStackTrace(); // Maneja el error apropiadamente
        }
    }
}





