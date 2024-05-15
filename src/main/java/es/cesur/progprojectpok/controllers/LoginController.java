package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Entrenador;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    @FXML
    private TextField invalidCredential;

    private Entrenador entrenador;

    Random rd = new Random();

    @FXML
    private void handleLoginButtonAction() {
        String usernameLog = usernameField.getText();
        String passwordLog = passwordField.getText();

        String sql = "SELECT * FROM entrenador WHERE NOM_ENTRENADOR = ? AND PASS = ?";

        // Realizar la conexión con la base de datos
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {


            preparedStatement.setString(1, usernameLog);
            preparedStatement.setString(2, passwordLog);
            ResultSet resultSet = preparedStatement.executeQuery();



            if (resultSet.next()) {
                entrenador = new Entrenador(
                        resultSet.getInt("ID_ENTRENADOR"),
                        resultSet.getString("NOM_ENTRENADOR"),
                        resultSet.getInt("POKEDOLLARS")
                        );



                // Si el usuario y la contraseña son válidos, cerrar la ventana de inicio de sesión
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                // Ahora cargar la nueva vista del menú principal
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 639, 645);
                Stage menuStage = new Stage();
                menuStage.setTitle("Menu");
                menuStage.setScene(scene);
                MenuController menuController = fxmlLoader.getController();
                menuController.setEntrenador(entrenador);
                menuStage.show();
            } else {
                //Si las credenciales son incorrectas, mostrar un mensaje de error
                invalidCredential.appendText("Credenciales incorrectas. \n");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleSignUpButtonAction() {
        String usernameSign = usernameField.getText();
        String passwordSign = passwordField.getText();

        int pokedollarsRandom = rd.nextInt(201)+800;

        String sql = "INSERT INTO entrenador (nom_entrenador, pass, pokedollars) VALUES (?, ?, ?)";


        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usernameSign);
            statement.setString(2, passwordSign);
            statement.setInt(3, pokedollarsRandom);
            statement.executeUpdate();
            invalidCredential.appendText("Usuario registrado \n");
        } catch (SQLException e) {
            invalidCredential.appendText("Error al registrar usuario en la base de datos \n");
        }
    }








}






