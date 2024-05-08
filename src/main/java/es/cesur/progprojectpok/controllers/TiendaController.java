package es.cesur.progprojectpok.controllers;
import es.cesur.progprojectpok.clases.Entrenador;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.stage.Modality;
public class TiendaController {

    public static final String PESA = "Pesa";
    public static final String CHALECO = "Chaleco";
    public static final String PLUMA = "Pluma";
    public static final String BASTON = "Baston";
    public static final String PILAS = "Pilas";
    public static final String ETER = "Eter";
    public static final String ANILLO_UNICO = "anilloUnico";
    public static final String POKEBALL = "pokeball";
    @FXML
    private Button irMenuFromTiendaButton;

    @FXML private Text pokedollarsText;

    @FXML
    private TextArea logTienda;

    @FXML
    private ImageView imagenPesa;
    @FXML
    private ImageView imagenPluma;
    @FXML
    private ImageView imagenChaleco;
    @FXML
    private ImageView imagenBaston;
    @FXML
    private ImageView imagenPila;
    @FXML
    private ImageView imagenEter;
    @FXML
    private ImageView imagenAnillo;
    @FXML
    private ImageView imagenPokeball;
    @FXML
    private Text precioPesa;
    @FXML
    private Text precioPluma;
    @FXML
    private Text precioChaleco;
    @FXML
    private Text precioBaston;
    @FXML
    private Text precioPila;
    @FXML
    private Text precioEter;
    @FXML
    private Text precioAnillo;
    @FXML
    private Text precioPokeball;


    private Entrenador entrenador;



    public void initialize() {
        // Configurar eventos para la imagen de la pesa
        configureMouseEvents(imagenPesa);
        mostrarPrecioObjeto(PESA);

        // Configurar eventos para la imagen de la pluma
        configureMouseEvents(imagenPluma);
        mostrarPrecioObjeto(PLUMA);

        // Configurar eventos para la imagen del chaleco
        configureMouseEvents(imagenChaleco);
        mostrarPrecioObjeto(CHALECO);

        // Configurar eventos para la imagen del bastón
        configureMouseEvents(imagenBaston);
        mostrarPrecioObjeto(BASTON);

        // Configurar eventos para la imagen de la pila
        configureMouseEvents(imagenPila);
        mostrarPrecioObjeto(PILAS);

        // Configurar eventos para la imagen del éter
        configureMouseEvents(imagenEter);
        mostrarPrecioObjeto(ETER);

        // Configurar eventos para la imagen del anillo
        configureMouseEvents(imagenAnillo);
        mostrarPrecioObjeto(ANILLO_UNICO);

        // Configurar eventos para la imagen de la pokeball
        configureMouseEvents(imagenPokeball);
        mostrarPrecioObjeto(POKEBALL);
    }


    public void mostrarPrecioObjeto(String nombreObjeto){
        int precioObjeto = obtenerPrecioObjeto(nombreObjeto);
        switch (nombreObjeto){
            case PESA:
                precioPesa.setText(String.valueOf(precioObjeto) + "$");
                break;
            case PLUMA:
                precioPluma.setText(String.valueOf(precioObjeto)+ "$");
                break;
            case CHALECO:
                precioChaleco.setText(String.valueOf(precioObjeto)+ "$");
                break;
            case BASTON:
                precioBaston.setText(String.valueOf(precioObjeto)+ "$");
                break;
            case PILAS:
                precioPila.setText(String.valueOf(precioObjeto)+ "$");
                break;
            case ETER:
                precioEter.setText(String.valueOf(precioObjeto) + "$");
                break;
            case ANILLO_UNICO:
                precioAnillo.setText(String.valueOf(precioObjeto) + "$");
                break;
            case POKEBALL:
                precioPokeball.setText(String.valueOf(precioObjeto) + "$");
                break;
        }
    }

    private void configureMouseEvents(ImageView imageView) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GRAY);
        dropShadow.setWidth(10);
        dropShadow.setHeight(10);

        imageView.setOnMouseEntered(event -> {
            imageView.setScaleX(1.1);
            imageView.setScaleY(1.1);
            imageView.setEffect(dropShadow);
        });

        imageView.setOnMouseExited(event -> {
            imageView.setScaleX(1.0);
            imageView.setScaleY(1.0);
            imageView.setEffect(null); // Eliminar el efecto de sombra
        });
    }



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
     showConfirmationDialog(PESA);
    }

    @FXML
    public void plumaPush(MouseEvent mouseEvent) {
        showConfirmationDialog(PLUMA);
    }

    @FXML
    public void chalecoPush(MouseEvent mouseEvent) {
        showConfirmationDialog(CHALECO);
    }
    @FXML
    public void bastonPush(MouseEvent mouseEvent) {
        showConfirmationDialog(BASTON);
    }

    @FXML
    public void pilaPush(MouseEvent mouseEvent) {
        showConfirmationDialog(PILAS);
    }

    @FXML
    public void EterPush(MouseEvent mouseEvent) {
        showConfirmationDialog(ETER);
    }

    @FXML
    public void anilloUnicoPush(MouseEvent mouseEvent) {
        showConfirmationDialog(ANILLO_UNICO);
    }

    @FXML
    public void pokeballPush(MouseEvent mouseEvent) {
        showConfirmationDialog(POKEBALL);
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



    private void showConfirmationDialog(String nombreObjeto) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/confirmacionCompra.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Confirmar compra");
            stage.initModality(Modality.APPLICATION_MODAL);

            int precioObjeto = obtenerPrecioObjeto(nombreObjeto);

            ConfirmarCompraController controller = loader.getController();
            controller.setNombrePrecioObjeto(nombreObjeto,precioObjeto);




            // Mostrar la ventana y esperar a que se cierre
            stage.showAndWait();

            // Comprobar si la compra fue confirmada
            if (controller.isCompraConfirmada()) {
                objetoPush(nombreObjeto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

