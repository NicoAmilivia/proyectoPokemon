package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.image.ImageView;






public class CentroController {

    @FXML
    private Button irMenuFromCentroButton;

    @FXML
    private TextField logCentro;

    @FXML
    private ImageView pokemon1;

    @FXML
    private ImageView pokemon2;

    @FXML
    private ImageView pokemon3;

    @FXML
    private ImageView pokemon4;

    @FXML
    private ImageView pokemon5;

    @FXML
    private ImageView pokemon6;

    @FXML
    private ProgressBar progressPK1;

    @FXML
    private ProgressBar progressPK2;

    @FXML
    private ProgressBar progressPK3;

    @FXML
    private ProgressBar progressPK4;

    @FXML
    private ProgressBar progressPK5;

    @FXML
    private ProgressBar progressPK6;


    private boolean mensajeMostrado = false;


    public void initialize(){
        try{

            Connection connection = DBConnection.getConnection();
            String sql = "SELECT pd.IMAGEN " +
                    "FROM POKEMON p " +
                    "INNER JOIN POKEDEX pd ON p.NUM_POKEDEX = pd.NUM_POKEDEX " +
                    "WHERE p.CAJA = 0;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();





            int i = 1;

            while (resultSet.next()) {

                //Obtener la imagen del resultado de la consulta
                String imageUrl = resultSet.getString("IMAGEN");


                //Crear un objeto Image a partir de la URL obtenida


                Image image = new Image(getClass().getResource(imageUrl).toExternalForm());

                if (i == 1) {
                    pokemon1.setImage(image);
                } else if (i == 2) {
                    pokemon2.setImage(image);
                } else if (i == 3) {
                    pokemon3.setImage(image);
                } else if (i == 4) {
                    pokemon4.setImage(image);
                } else if (i == 5) {
                    pokemon5.setImage(image);
                } else if (i == 6) {
                    pokemon6.setImage(image);
                }



                i++;


            }


        } catch(SQLException e){

            e.printStackTrace();

        }


    }








    @FXML

    private void irMenuFromCentroOnAction() {

        Stage stage = (Stage) irMenuFromCentroButton.getScene().getWindow();
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
    public void recuperarOnAction() {
        try {
            // Obtener la conexión a la base de datos
            Connection connection = DBConnection.getConnection();

            // Consulta SQL para obtener la vida máxima de cada Pokémon
            String sql = "SELECT ID_POKEMON, VIDA_ACTUAL FROM POKEMON WHERE CAJA = 0";

            // Preparar la declaración SQL
            PreparedStatement statement = connection.prepareStatement(sql);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Actualizar la vida de cada Pokémon
            while (resultSet.next()) {
                int numPokedex = resultSet.getInt("ID_POKEMON");
                int vidaActual = resultSet.getInt("VIDA_ACTUAL");

                // Actualizar la vida del Pokémon en la base de datos
                actualizarVidaActual(numPokedex);
            }

            // Cerrar la conexión y liberar recursos
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try{

            Connection connection = DBConnection.getConnection();
            String sql = "SELECT pd.IMAGEN " +
                    "FROM POKEMON p " +
                    "INNER JOIN POKEDEX pd ON p.NUM_POKEDEX = pd.NUM_POKEDEX " +
                    "WHERE p.CAJA = 0;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();





            int i = 1;

            while (resultSet.next()) {


                if (i == 1) {
                   progressPK1.setProgress(1.0);
                } else if (i == 2) {
                    progressPK2.setProgress(1.0);
                } else if (i == 3) {
                    progressPK3.setProgress(1.0);
                } else if (i == 4) {
                    progressPK4.setProgress(1.0);
                } else if (i == 5) {
                    progressPK5.setProgress(1.0);
                } else if (i == 6) {
                    progressPK6.setProgress(1.0);
                }



                i++;


            }


        } catch(SQLException e){

            e.printStackTrace();

        }




        if (!mensajeMostrado) {
            // Mostrar el mensaje solo si no se ha mostrado antes
            logCentro.appendText("¡Pokémons recuperados! ✔");
            mensajeMostrado = true; // Marcar el mensaje como mostrado
        }






    }

    // Método para actualizar la vida máxima de un Pokémon en la base de datos
    private void actualizarVidaActual(int id_Pokemon) {
        try {
            // Obtener la conexión a la base de datos
            Connection connection = DBConnection.getConnection();

            // Consulta SQL para obtener la vitalidad del Pokémon
            String sqlSelect = "SELECT VITALIDAD FROM POKEMON WHERE CAJA = 0 AND ID_POKEMON = ?";
            PreparedStatement statementSelect = connection.prepareStatement(sqlSelect);
            statementSelect.setInt(1, id_Pokemon);
            ResultSet resultSet = statementSelect.executeQuery();

            int vidaActual = 0;
            if (resultSet.next()) {
                vidaActual = resultSet.getInt("VITALIDAD");
            }

            // Consulta SQL para actualizar la vida actual del Pokémon
            String sqlUpdate = "UPDATE POKEMON SET VIDA_ACTUAL = ? WHERE ID_POKEMON = ?";
            PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdate);
            statementUpdate.setInt(1, vidaActual);
            statementUpdate.setInt(2, id_Pokemon);

            // Ejecutar la actualización
            int rowsAffected = statementUpdate.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("La vida del Pokémon número " + id_Pokemon + " ha sido restablecida al máximo.");
            } else {
                System.out.println("No se pudo restablecer la vida del Pokémon número " + id_Pokemon + ".");
            }

            // Cerrar la conexión y liberar recursos
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
