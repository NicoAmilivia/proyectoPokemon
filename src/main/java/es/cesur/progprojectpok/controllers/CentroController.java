package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    @FXML
    private Text mote1;

    @FXML
    private Text mote2;

    @FXML
    private Text mote3;

    @FXML
    private Text mote4;

    @FXML
    private Text mote5;

    @FXML
    private Text mote6;

    private boolean mensajeMostrado = false;

    public void initialize() {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT pd.IMAGEN, p.VIDA_ACTUAL, p.VITALIDAD, p.MOTE " +
                    "FROM POKEMON p " +
                    "INNER JOIN POKEDEX pd ON p.NUM_POKEDEX = pd.NUM_POKEDEX " +
                    "WHERE p.CAJA = 0 " +
                    "ORDER BY p.NUM_POKEDEX";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            int i = 1;

            while (resultSet.next() && i <= 6) {
                String imageUrl = resultSet.getString("IMAGEN");
                int vidaActual = resultSet.getInt("VIDA_ACTUAL");
                int vidaMaxima = resultSet.getInt("VITALIDAD");
                String motePokemon = resultSet.getString("MOTE");
                Image image = new Image(getClass().getResource(imageUrl).toExternalForm());

                ImageView pokemonImageView = switch (i) {
                    case 1 -> pokemon1;
                    case 2 -> pokemon2;
                    case 3 -> pokemon3;
                    case 4 -> pokemon4;
                    case 5 -> pokemon5;
                    case 6 -> pokemon6;
                    default -> null;
                };

                ProgressBar pokemonProgressBar = switch (i) {
                    case 1 -> progressPK1;
                    case 2 -> progressPK2;
                    case 3 -> progressPK3;
                    case 4 -> progressPK4;
                    case 5 -> progressPK5;
                    case 6 -> progressPK6;
                    default -> null;
                };

                Text moteText = switch (i) {
                    case 1 -> mote1;
                    case 2 -> mote2;
                    case 3 -> mote3;
                    case 4 -> mote4;
                    case 5 -> mote5;
                    case 6 -> mote6;
                    default -> null;
                };

                if (pokemonImageView != null && pokemonProgressBar != null && moteText != null) {
                    pokemonImageView.setImage(image);
                    pokemonProgressBar.setProgress((double) vidaActual / vidaMaxima);
                    moteText.setText(motePokemon);
                }

                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void recuperarOnAction() {

                try (Connection connection = DBConnection.getConnection()) {
                    // Actualizar la vida de los Pokémon en la caja a su valor máximo en la base de datos
                    String sqlUpdate = "UPDATE POKEMON SET VIDA_ACTUAL = VITALIDAD WHERE CAJA = 0";
                    PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);
                    int rowsAffected = updateStatement.executeUpdate();


                        // Actualizar las barras de progreso solo para los Pokémon en la caja
                        String sqlSelect = "SELECT VIDA_ACTUAL, VITALIDAD FROM POKEMON WHERE CAJA = 0 ORDER BY NUM_POKEDEX";
                        PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
                        ResultSet resultSet = selectStatement.executeQuery();

                        int i = 1;
                        while (resultSet.next() && i <= 6) {
                            int vidaActual = resultSet.getInt("VIDA_ACTUAL");
                            int vidaMaxima = resultSet.getInt("VITALIDAD");

                            ProgressBar pokemonProgressBar = switch (i) {
                                case 1 -> progressPK1;
                                case 2 -> progressPK2;
                                case 3 -> progressPK3;
                                case 4 -> progressPK4;
                                case 5 -> progressPK5;
                                case 6 -> progressPK6;
                                default -> null;
                            };

                            if (pokemonProgressBar != null) {
                                pokemonProgressBar.setProgress((double) vidaActual / vidaMaxima);
                            }

                            i++;
                        }

                        // Mostrar el mensaje solo una vez
                        if (!mensajeMostrado) {
                            logCentro.appendText("¡Pokémons recuperados! ✔");
                            mensajeMostrado = true;
                        }

                } catch (SQLException e) {
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
            e.printStackTrace();
        }
    }
}