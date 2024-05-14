package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Entrenador;
import es.cesur.progprojectpok.clases.Pokemon;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.io.File;
import java.sql.*;
import java.util.*;
import java.io.IOException;

public class MochilaController {

    @FXML
    private ImageView objeto1;

    @FXML
    private ImageView objeto2;

    @FXML
    private ImageView objeto3;

    @FXML
    private ImageView objeto4;

    @FXML
    private ImageView objeto5;

    @FXML
    private ImageView objeto6;

    @FXML
    private ImageView objeto7;

    @FXML
    private Text numObjeto1;

    @FXML
    private Text numObjeto2;

    @FXML
    private Text numObjeto3;
    @FXML
    private Text numObjeto4;
    @FXML
    private Text numObjeto5;
    @FXML
    private Text numObjeto6;
    @FXML
    private Text numObjeto7;




    @FXML
    private ImageView imagenObjetoEquipado;


    @FXML
    private Button irEquipoFromMochilaButton;

    @FXML
    private Button desequiparObjetoButton;

    @FXML
    private ImageView imagenPokemonSeleccionado;

    @FXML
    private TextArea logMochila;

    private Entrenador entrenador;

    private Pokemon pokemonSeleccionado;

    public void initialize() {
        configureMouseEvents(objeto1);
        configureMouseEvents(objeto2);
        configureMouseEvents(objeto3);
        configureMouseEvents(objeto4);
        configureMouseEvents(objeto5);
        configureMouseEvents(objeto6);
        configureMouseEvents(objeto7);
        logMochila.setEditable(false);
        logMochila.setWrapText(true);
    }


    @FXML
    public void irEquipoFromMochilaOnAction() {
        Stage stage = (Stage) irEquipoFromMochilaButton.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/equipo-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 590, 600);
            Stage menuStage = new Stage();
            menuStage.setTitle("Mochila");
            menuStage.setScene(scene);
            EquipoController equipoController = fxmlLoader.getController();
            equipoController.setEntrenador(entrenador);
            menuStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Maneja el error apropiadamente
        }
    }


    public void cargarImagenesMochila() {
        List<ImageView> imageViews = Arrays.asList(objeto1, objeto2, objeto3, objeto4, objeto5, objeto6, objeto7);
        List<Text> numObjetos = Arrays.asList(numObjeto1, numObjeto2, numObjeto3, numObjeto4, numObjeto5, numObjeto6, numObjeto7);
        int idEntrenador = entrenador.getId();
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT m.ID_OBJETO, m.NUM_OBJETO, o.URL " +
                    "FROM Objeto o " +
                    "INNER JOIN Mochila m ON o.ID_OBJETO = m.ID_OBJETO " +
                    "WHERE m.ID_ENTRENADOR = ? AND m.ID_OBJETO != 8";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idEntrenador);
            ResultSet resultSet = statement.executeQuery();

            Map<Integer, Pair<String, Integer>> idToUrlAndNumObjetoMap = new HashMap<>();
            while (resultSet.next()) {
                int idObjeto = resultSet.getInt("ID_OBJETO");
                String url = resultSet.getString("URL");
                int numObjeto = resultSet.getInt("NUM_OBJETO");
                idToUrlAndNumObjetoMap.put(idObjeto, new Pair<>(url, numObjeto));
            }

            for (int i = 0; i < imageViews.size(); i++) {
                ImageView imageView = imageViews.get(i);
                Text numObjetoText = numObjetos.get(i);
                int idObjeto = i + 1;
                Pair<String, Integer> urlAndNumObjeto = idToUrlAndNumObjetoMap.get(idObjeto);

                if (urlAndNumObjeto != null) {
                    String url = urlAndNumObjeto.getKey();
                    int numObjeto = urlAndNumObjeto.getValue();

                    // Cargar la imagen
                    if (url != null && !url.isEmpty()) {
                        try {
                            File archivo = new File(url);
                            String rutaAbsoluta = archivo.getAbsolutePath();
                            if (System.getProperty("os.name").startsWith("Windows")) {
                                rutaAbsoluta = rutaAbsoluta.replace("/", "\\");
                            }
                            Image objetoGenerado = new Image("file:\\" + rutaAbsoluta);
                            imageView.setImage(objetoGenerado);

                            // Asignar el número de objetos al Text correspondiente
                            numObjetoText.setText(Integer.toString(numObjeto));

                            imageView.setOnMouseClicked(event -> {
                                aplicarIdObjetoEnBD(idObjeto);
                            });

                        } catch (NullPointerException e) {
                            System.err.println("La imagen no se encuentra en la ruta especificada: " + url);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void aplicarIdObjetoEnBD(int idObjeto) {
        Pair<Boolean, String> objetoEquipado = obtenerObjetoEquipado(pokemonSeleccionado.getIdPokemon());

        if (objetoEquipado.getKey()) {
            logMochila.appendText(pokemonSeleccionado.getNombre() + " ya tiene equipado " + objetoEquipado.getValue().toUpperCase() + " !!!!\n" +
                    "Desequipa " + objetoEquipado.getValue().toUpperCase() + " para poder equipar un nuevo objeto\n");
        } else {
            try (Connection connection = DBConnection.getConnection()) {
                // Restar uno al número de objetos en la mochila
                String updateMochilaSQL = "UPDATE Mochila SET NUM_OBJETO = NUM_OBJETO - 1 WHERE ID_ENTRENADOR = ? AND ID_OBJETO = ?";
                PreparedStatement mochilaStatement = connection.prepareStatement(updateMochilaSQL);
                mochilaStatement.setInt(1, entrenador.getId());
                mochilaStatement.setInt(2, idObjeto);
                mochilaStatement.executeUpdate();

                if (obtenerNumObjetosMochila(idObjeto)==0){
                    eliminarObjetoMochila(idObjeto);
                }
                // Asignar el nuevo objeto al Pokémon seleccionado
                String updatePokemonSQL = "UPDATE POKEMON SET ID_OBJETO = ? WHERE ID_POKEMON = ?";
                PreparedStatement statement = connection.prepareStatement(updatePokemonSQL);
                statement.setInt(1, idObjeto);
                statement.setInt(2, pokemonSeleccionado.getIdPokemon());
                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    logMochila.appendText("Se ha equipado el objeto correctamente\n");
                    cargarImagenObjetoEquipado(idObjeto);
                    // Actualizar el número de objetos en la vista
                    switch (idObjeto) {
                        case 1:
                            numObjeto1.setText(String.valueOf(Integer.parseInt(numObjeto1.getText()) - 1));
                            break;
                        case 2:
                            numObjeto2.setText(String.valueOf(Integer.parseInt(numObjeto2.getText()) - 1));
                            break;
                        case 3:
                            numObjeto3.setText(String.valueOf(Integer.parseInt(numObjeto3.getText()) - 1));
                            break;
                        case 4:
                            numObjeto4.setText(String.valueOf(Integer.parseInt(numObjeto4.getText()) - 1));
                            break;
                        case 5:
                            numObjeto5.setText(String.valueOf(Integer.parseInt(numObjeto5.getText()) - 1));
                            break;
                        case 6:
                            numObjeto6.setText(String.valueOf(Integer.parseInt(numObjeto6.getText()) - 1));
                            break;
                        case 7:
                            numObjeto7.setText(String.valueOf(Integer.parseInt(numObjeto7.getText()) - 1));
                            break;
                        default:
                            break;
                    }
                } else {
                    logMochila.appendText("No se pudo equipar el objeto\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (NumberFormatException n){

            }
        }
    }





    public void setEntrenadorandPokemon(Entrenador entrenador, Pokemon pokemonSeleccionado) throws SQLException {
        this.entrenador = entrenador;
        cargarImagenesMochila();
        this.pokemonSeleccionado = pokemonSeleccionado;
        cargarImagenPokemonSeleccionado();
        cargarImagenObjetoEquipado();
    }


    public void cargarImagenPokemonSeleccionado() throws SQLException {

        try (Connection connection = DBConnection.getConnection()) {

            String sql = "SELECT IMAGEN FROM POKEDEX WHERE NUM_POKEDEX = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pokemonSeleccionado.getNumPokedex());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String imageUrl = resultSet.getString("IMAGEN");
                Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
                imagenPokemonSeleccionado.setImage(image);

            }
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


    public static Pair<Boolean, String> obtenerObjetoEquipado(int idPokemon) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT o.NOMBRE FROM OBJETO o INNER JOIN POKEMON p ON o.ID_OBJETO = p.ID_OBJETO WHERE p.ID_POKEMON = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idPokemon);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nombreObjeto = resultSet.getString("NOMBRE");
                return new Pair<>(true, nombreObjeto); // Devuelve true si encuentra un objeto equipado y su nombre
            } else {
                return new Pair<>(false, ""); // Devuelve false si no encuentra ningún objeto equipado
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Pair<>(false, ""); // Manejar el error apropiadamente
        }
    }




    private void cargarImagenObjetoEquipado() {
        if (pokemonSeleccionado != null) {
            int idPokemon = pokemonSeleccionado.getIdPokemon();
            try (Connection connection = DBConnection.getConnection()) {
                String sql = "SELECT o.URL FROM Pokemon p " +
                        "INNER JOIN Objeto o ON p.ID_OBJETO = o.ID_OBJETO " +
                        "WHERE p.ID_POKEMON = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, idPokemon);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String url = resultSet.getString("URL");
                    if (url != null && !url.isEmpty()) {
                        try {
                            // Intenta cargar la imagen
                            File archivo = new File(url);
                            String rutaAbsoluta = archivo.getAbsolutePath();
                            if (System.getProperty("os.name").startsWith("Windows")) {
                                rutaAbsoluta = rutaAbsoluta.replace("/", "\\");
                            }
                            Image imagenObjeto = new Image("file:\\" + rutaAbsoluta);
                            imagenObjetoEquipado.setImage(imagenObjeto);
                        } catch (NullPointerException e) {

                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void desequiparObjetoOnAction() {
        try (Connection connection = DBConnection.getConnection()) {
            // Obtener el ID del objeto equipado por el Pokémon seleccionado
            Pair<Boolean, String> objetoEquipado = obtenerObjetoEquipado(pokemonSeleccionado.getIdPokemon());

            // Verificar si el Pokémon tiene un objeto equipado
            if (objetoEquipado.getKey()) {
                // Obtener el nombre del objeto equipado
                String nombreObjeto = objetoEquipado.getValue();

                // Obtener el ID del objeto a partir de su nombre
                int idObjeto = obtenerIdObjeto(nombreObjeto);

                // Actualizar la tabla POKEMON para desequipar el objeto
                String updateSQL = "UPDATE POKEMON SET ID_OBJETO = NULL WHERE ID_POKEMON = ?";
                PreparedStatement statement = connection.prepareStatement(updateSQL);
                statement.setInt(1, pokemonSeleccionado.getIdPokemon());
                int rowsUpdated = statement.executeUpdate();


                if (objetoEquipado.getKey()){
                    insertarObjetoEnMochila(entrenador.getId(),idObjeto);
                }else {
                    incrementarNumObjetoEnMochila(idObjeto);
                }



                cargarImagenesMochila();


                // Verificar si se actualizó correctamente
                if (rowsUpdated > 0) {


                    // Limpiar la imagen del objeto equipado en la vista
                    imagenObjetoEquipado.setImage(null);

                    // Mostrar un mensaje en el log
                    logMochila.appendText("Se ha desequipado el objeto del Pokémon " + pokemonSeleccionado.getNombre() + "\n");

                } else {
                    // Mostrar un mensaje en el log si no se encontró ningún objeto equipado
                    logMochila.appendText("No se encontró ningún objeto equipado para el Pokémon " + pokemonSeleccionado.getNombre() + "\n");
                }
            } else {
                // Mostrar un mensaje en el log si el Pokémon no tiene ningún objeto equipado
                logMochila.appendText(pokemonSeleccionado.getNombre() + " no tiene ningún objeto equipado.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void incrementarNumObjetoEnMochila(int idObjeto) {
        try (Connection connection = DBConnection.getConnection()) {
            String updateMochilaSQL = "UPDATE Mochila SET NUM_OBJETO = NUM_OBJETO + 1 WHERE ID_ENTRENADOR = ? AND ID_OBJETO = ?";
            PreparedStatement mochilaStatement = connection.prepareStatement(updateMochilaSQL);
            mochilaStatement.setInt(1, entrenador.getId());
            mochilaStatement.setInt(2, idObjeto);
            mochilaStatement.executeUpdate();

            // Actualizar el número de objetos en la vista
            switch (idObjeto) {
                case 1:
                    try {
                        numObjeto1.setText(String.valueOf(Integer.parseInt(numObjeto1.getText()) + 1));
                    } catch (NumberFormatException e) {

                    }
                    break;
                case 2:
                    try {
                        numObjeto2.setText(String.valueOf(Integer.parseInt(numObjeto2.getText()) + 1));
                    } catch (NumberFormatException e) {

                    }
                    break;
                case 3:
                    try {
                        numObjeto3.setText(String.valueOf(Integer.parseInt(numObjeto3.getText()) + 1));
                    } catch (NumberFormatException e) {

                    }
                    break;
                case 4:
                    try {
                        numObjeto4.setText(String.valueOf(Integer.parseInt(numObjeto4.getText()) + 1));
                    } catch (NumberFormatException e) {

                    }
                    break;
                case 5:
                    try {
                        numObjeto5.setText(String.valueOf(Integer.parseInt(numObjeto5.getText()) + 1));
                    } catch (NumberFormatException e) {

                    }
                    break;
                case 6:
                    try {
                        numObjeto6.setText(String.valueOf(Integer.parseInt(numObjeto6.getText()) + 1));
                    } catch (NumberFormatException e) {

                    }
                    break;
                case 7:
                    try {
                        numObjeto7.setText(String.valueOf(Integer.parseInt(numObjeto7.getText()) + 1));
                    } catch (NumberFormatException e) {

                    }
                    break;
                default:
                    break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    private int obtenerNumObjetosMochila(int idObjeto) throws SQLException {
        Connection connection = DBConnection.getConnection();
        int numObjetos = 0;

        try {
            String sql = "SELECT NUM_OBJETO FROM Mochila WHERE ID_ENTRENADOR = ? AND ID_OBJETO = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, entrenador.getId());
            statement.setInt(2, idObjeto);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                numObjetos = resultSet.getInt("NUM_OBJETO");
            }
        } finally {
            connection.close();
        }

        return numObjetos;
    }



    private int obtenerIdObjeto(String nombreObjeto) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT ID_OBJETO FROM Objeto WHERE NOMBRE = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nombreObjeto);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID_OBJETO");
            } else {
                return -1; // O algún otro valor que indique que no se encontró el objeto
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja el error apropiadamente
            return -1; // O algún otro valor que indique que hubo un error
        }
    }



    private void cargarImagenObjetoEquipado(int idObjeto) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT URL FROM Objeto WHERE ID_OBJETO = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idObjeto);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String url = resultSet.getString("URL");
                if (url != null && !url.isEmpty()) {
                    try {
                        // Intenta cargar la imagen
                        File archivo = new File(url);
                        String rutaAbsoluta = archivo.getAbsolutePath();
                        if (System.getProperty("os.name").startsWith("Windows")) {
                            rutaAbsoluta = rutaAbsoluta.replace("/", "\\");
                        }
                        Image imagenObjeto = new Image("file:\\" + rutaAbsoluta);
                        imagenObjetoEquipado.setImage(imagenObjeto);
                    } catch (NullPointerException e) {
                        // Maneja la excepción
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void eliminarObjetoMochila(int idObjeto){

        try (Connection connection = DBConnection.getConnection()) {
            String deleteObjetoSQL = "DELETE FROM Mochila WHERE ID_ENTRENADOR = ? AND ID_OBJETO = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteObjetoSQL);
            deleteStatement.setInt(1, entrenador.getId());
            deleteStatement.setInt(2, idObjeto);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Manejar el error apropiadamente
        }

        switch (idObjeto) {
            case 1:
                objeto1.setImage(null);
                numObjeto1.setText("");
                break;
            case 2:
                objeto2.setImage(null);
                numObjeto2.setText("");
                break;
            case 3:
                objeto3.setImage(null);
                numObjeto3.setText("");
                break;
            case 4:
                objeto4.setImage(null);
                numObjeto4.setText("");
                break;
            case 5:
                objeto5.setImage(null);
                numObjeto5.setText("");
                break;
            case 6:
                objeto6.setImage(null);
                numObjeto6.setText("");
                break;
            case 7:
                objeto7.setImage(null);
                numObjeto7.setText("");
                break;
            default:
                break;
        }



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

        switch (idObjeto) {
            case 1:
                try {
                    numObjeto1.setText(String.valueOf(Integer.parseInt(numObjeto1.getText()) + 1));
                } catch (NumberFormatException e) {

                }
                break;
            case 2:
                try {
                    numObjeto2.setText(String.valueOf(Integer.parseInt(numObjeto2.getText()) + 1));
                } catch (NumberFormatException e) {

                }
                break;
            case 3:
                try {
                    numObjeto3.setText(String.valueOf(Integer.parseInt(numObjeto3.getText()) + 1));
                } catch (NumberFormatException e) {

                }
                break;
            case 4:
                try {
                    numObjeto4.setText(String.valueOf(Integer.parseInt(numObjeto4.getText()) + 1));
                } catch (NumberFormatException e) {

                }
                break;
            case 5:
                try {
                    numObjeto5.setText(String.valueOf(Integer.parseInt(numObjeto5.getText()) + 1));
                } catch (NumberFormatException e) {

                }
                break;
            case 6:
                try {
                    numObjeto6.setText(String.valueOf(Integer.parseInt(numObjeto6.getText()) + 1));
                } catch (NumberFormatException e) {

                }
                break;
            case 7:
                try {
                    numObjeto7.setText(String.valueOf(Integer.parseInt(numObjeto7.getText()) + 1));
                } catch (NumberFormatException e) {

                }
                break;
            default:
                break;
        }






    }




}





