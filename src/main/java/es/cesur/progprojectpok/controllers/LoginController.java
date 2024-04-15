package es.cesur.progprojectpok.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;


public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private void handleLoginButtonAction() {
        // Aquí deberías verificar el inicio de sesión del usuario

        // Si el inicio de sesión es exitoso, entonces cierra la ventana de login
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();

        // Ahora carga la nueva vista del menú principal
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage menuStage = new Stage();
            menuStage.setTitle("Menu");
            menuStage.setScene(scene);
            menuStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Maneja el error apropiadamente
        }
    }
}
