package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Entrenador;
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

    public static final String VIDA_ACTUAL = "VIDA_ACTUAL";
    public static final String VITALIDAD = "VITALIDAD";
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

    private Entrenador entrenador;

    public void initialize() {
        String sql = "SELECT pd.IMAGEN, p.VIDA_ACTUAL, p.VITALIDAD, p.MOTE " +
                "FROM POKEMON p " +
                "INNER JOIN POKEDEX pd ON p.NUM_POKEDEX = pd.NUM_POKEDEX " +
                "WHERE p.CAJA = 0 " +
                "ORDER BY p.NUM_POKEDEX";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            ResultSet resultSet = statement.executeQuery();

            int i = 1;

            while (resultSet.next() && i <= 6) {
                String imageUrl = resultSet.getString("IMAGEN");
                int vidaActual = resultSet.getInt(VIDA_ACTUAL);
                int vidaMaxima = resultSet.getInt(VITALIDAD);
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
        String sqlCheck = "SELECT VIDA_ACTUAL, VITALIDAD FROM POKEMON WHERE CAJA = 0 ORDER BY NUM_POKEDEX";
        String sqlUpdate = "UPDATE POKEMON SET VIDA_ACTUAL = VITALIDAD WHERE CAJA = 0";

        boolean necesitaRecuperacion = false;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(sqlCheck);
             PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);) {

            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                int vidaActual = resultSet.getInt(VIDA_ACTUAL);
                int vidaMaxima = resultSet.getInt(VITALIDAD);

                if (vidaActual < vidaMaxima) {
                    necesitaRecuperacion = true;
                    break;
                }
            }

            if (necesitaRecuperacion) {

                int rowsAffected = updateStatement.executeUpdate();

                resultSet = checkStatement.executeQuery();

                int i = 1;
                while (resultSet.next() && i <= 6) {
                    int vidaActual = resultSet.getInt(VIDA_ACTUAL);
                    int vidaMaxima = resultSet.getInt(VITALIDAD);

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


                if (!mensajeMostrado) {
                    logCentro.appendText("¡Pokémons recuperados! ✔");
                    mensajeMostrado = true;
                }
            } else {

                logCentro.appendText("Ningun pokemon esta herido");
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
            MenuController menuController = fxmlLoader.getController();
            menuController.setEntrenador(entrenador);
            menuStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
}