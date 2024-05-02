package es.cesur.progprojectpok.controllers;
import es.cesur.progprojectpok.clases.Entrenador;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class TiendaController {

    @FXML
    private Button irMenuFromTiendaButton;

    @FXML private Text pokedollarsText;

    @FXML
    private TextArea logTienda;
    private Entrenador entrenador;


    //METODO GENERICO PARA TODOS LOS OBJETOS
    public void objetoPush(String nombreObjeto) {
        int precioObjeto = obtenerPrecioObjeto(nombreObjeto);
        int diferencia = entrenador.getNumPokeDollars() - precioObjeto;
        if (diferencia >= 0) {
            try {
                Connection connection = DBConnection.getConnection();
                actualizarPokedollars(entrenador.getId(), diferencia);
                int idObjeto = obtenerIdObjeto(nombreObjeto);
                insertarObjetoEnMochila(entrenador.getId(), idObjeto);
                logTienda.appendText("Has comprado el objeto " + nombreObjeto.toUpperCase() + "!\n");
                pokedollarsText.setText(String.valueOf(diferencia));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            logTienda.appendText("No tienes suficientes pokedollars\n");
        }
    }

    @FXML
    public void mancuernaPush(MouseEvent mouseEvent) {
     objetoPush("Pesa");
    }

    @FXML
    public void plumaPush(MouseEvent mouseEvent) {
        objetoPush("Pluma");
    }

    @FXML
    public void chalecoPush(MouseEvent mouseEvent) {
        objetoPush("Chaleco");
    }
    @FXML
    public void bastonPush(MouseEvent mouseEvent) {
        objetoPush("Baston");
    }

    @FXML
    public void pilaPush(MouseEvent mouseEvent) {
        objetoPush("Pilas");
    }

    @FXML
    public void EterPush(MouseEvent mouseEvent) {
        objetoPush("Eter");
    }

    @FXML
    public void anilloUnicoPush(MouseEvent mouseEvent) {
        objetoPush("anilloUnico");
    }

    @FXML
    public void pokeballPush(MouseEvent mouseEvent) {
        objetoPush("pokeball");
    }

    private void actualizarPokedollars(int idEntrenador, int diferencia) throws SQLException {
        String sql = "UPDATE ENTRENADOR SET POKEDOLLARS = ? WHERE ID_ENTRENADOR = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(sql)) {
            updateStatement.setInt(1, diferencia);
            updateStatement.setInt(2, idEntrenador);
            int rowsAffected= updateStatement.executeUpdate();
            if(rowsAffected>0){
                entrenador.setNumPokeDollars(diferencia);
            }
        }
    }

    private int obtenerIdObjeto(String nombreObjeto) throws SQLException {
        String idObjetoQuery = "SELECT ID_OBJETO FROM OBJETO WHERE NOMBRE = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(idObjetoQuery)) {
            selectStatement.setString(1, nombreObjeto);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("ID_OBJETO");
                }
            }
        }
        return -1;   //Devolver -1 si hay un error
    }

    private void insertarObjetoEnMochila(int idEntrenador, int idObjeto) throws SQLException {
        String insertMochilaQuery = "INSERT INTO MOCHILA (ID_ENTRENADOR, ID_OBJETO, NUM_OBJETO) VALUES (?, ?, 1) " +
                "ON DUPLICATE KEY UPDATE NUM_OBJETO = NUM_OBJETO + 1";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(insertMochilaQuery)) {
            insertStatement.setInt(1, idEntrenador);
            insertStatement.setInt(2, idObjeto);
            insertStatement.executeUpdate();
        }
    }



    private int obtenerPrecioObjeto(String nombreObjeto) {
        // Método para obtener el precio de un objeto de la base de datos
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT precio FROM objeto WHERE nombre = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombreObjeto);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("precio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Devolver -1 si hay un error o si el objeto no existe en la base de datos
    }

    @FXML
    public void irMenuFromTiendaOnAction() {


            Stage stage = (Stage) irMenuFromTiendaButton.getScene().getWindow();
            stage.close();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 590, 600);
                Stage menuStage = new Stage();
                menuStage.setTitle("Menu");
                MenuController Menucontroller = fxmlLoader.getController();
                Menucontroller.setEntrenador(entrenador);
                menuStage.setScene(scene);
                menuStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    public void setEntrenador(Entrenador entrenador){
        this.entrenador=entrenador;
        pokedollarsText.setText(String.valueOf(entrenador.getNumPokeDollars()));

    }





}

