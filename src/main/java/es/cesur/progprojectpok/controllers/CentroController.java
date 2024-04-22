package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public void recuperarOnAction(){

    }




}
