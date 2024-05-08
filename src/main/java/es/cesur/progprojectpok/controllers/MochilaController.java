package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Entrenador;
import es.cesur.progprojectpok.clases.Pokemon;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
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
        int idEntrenador = entrenador.getId();
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT m.ID_OBJETO, o.URL " +
                    "FROM Objeto o " +
                    "INNER JOIN Mochila m ON o.ID_OBJETO = m.ID_OBJETO " +
                    "WHERE m.ID_ENTRENADOR = ? AND M.ID_OBJETO!=8";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idEntrenador);
            ResultSet resultSet = statement.executeQuery();


            Map<Integer, String> idToUrlMap = new HashMap<>();
            while (resultSet.next()) {
                int idObjeto = resultSet.getInt("ID_OBJETO");
                String url = resultSet.getString("URL");
                idToUrlMap.put(idObjeto, url);

            }

            for (int i = 0; i < imageViews.size(); i++) {
                ImageView imageView = imageViews.get(i);
                int idObjeto = i + 1;
                String url = idToUrlMap.get(idObjeto);

                if (url != null && !url.isEmpty()) {
                    try {
                        // Intenta cargar la imagen
                        File archivo = new File(url);
                        String rutaAbsoluta = archivo.getAbsolutePath();
                        if (System.getProperty("os.name").startsWith("Windows")) {
                            rutaAbsoluta = rutaAbsoluta.replace("/", "\\");
                        }
                        Image objetoGenerado = new Image("file:\\" + rutaAbsoluta);
                        imageView.setImage(objetoGenerado);

                        imageView.setOnMouseClicked(event -> {
                            aplicaridObjetoEnBD(idObjeto);
                        });


                    } catch (NullPointerException e) {
                        System.err.println("La imagen no se encuentra en la ruta especificada: " + url);

                    }
                } else {

                    System.err.println("La URL de la imagen es nula o vacía para el objeto con ID: " + idObjeto);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void aplicaridObjetoEnBD(int idObjeto) {

        Pair<Boolean, String> objetoEquipado = obtenerObjetoEquipado(pokemonSeleccionado.getIdPokemon());

        if (objetoEquipado.getKey()==true){

            logMochila.appendText(pokemonSeleccionado.getNombre() + " ya tiene equipado " + objetoEquipado.getValue().toUpperCase() + " !!!!\n" +
                    "Desequipa " + objetoEquipado.getValue().toUpperCase() + " para poder equipar un nuevo objeto\n");


        }else {

            try (Connection connection = DBConnection.getConnection()) {

                String updateSQL = "UPDATE POKEMON SET ";
                switch (idObjeto) {
                    case 1: // pesa
                        updateSQL += " ID_OBJETO= 1";
                        logMochila.appendText("Se ha equipado el objeto pesa");
                        break;
                    case 2: // pluma
                        updateSQL += " ID_OBJETO= 2";
                        logMochila.appendText("Se ha equipado el objeto pluma");
                        break;
                    case 3: // chaleco
                        updateSQL += " ID_OBJETO= 3";
                        logMochila.appendText("Se ha equipado el objeto chaleco");
                        break;
                    case 4: // bastón
                        updateSQL += " ID_OBJETO= 4";
                        logMochila.appendText("Se ha equipado el objeto baston");
                        break;
                    case 5: // pilas
                        updateSQL += " ID_OBJETO= 5";
                        logMochila.appendText("Se ha equipado el objeto pilas");
                        break;
                    case 6: // Eter
                        updateSQL += "ID_OBJETO=6";
                        logMochila.appendText("Se ha equipado el objeto eter");
                        break;
                    case 7: // Anillo único
                        updateSQL += "ID_OBJETO= 7";
                        logMochila.appendText("Se ha equipado el objeto anillo");
                        break;
                    default:
                        System.out.println("Objeto no reconocido.");
                        return;
                }
                // Completa el updateSQL
                updateSQL += " WHERE ID_POKEMON = ?";

                // Prepara y ejecuta la sentencia SQL
                PreparedStatement statement = connection.prepareStatement(updateSQL);
                statement.setInt(1, pokemonSeleccionado.getIdPokemon());
                statement.executeUpdate();
                cargarImagenObjetoEquipado();
            } catch (SQLException e) {
                e.printStackTrace();
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
            String sql = "SELECT o.NOMBRE FROM POKEMON p INNER JOIN Objeto o ON p.ID_OBJETO = o.ID_OBJETO WHERE p.ID_POKEMON = ?";
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
                            System.err.println("La imagen no se encuentra en la ruta especificada: " + url);
                        }
                    } else {
                        System.err.println("La URL de la imagen es nula o vacía para el objeto equipado del Pokémon con ID: " + idPokemon);
                    }
                } else {
                    System.err.println("No se encontró un objeto equipado para el Pokémon con ID: " + idPokemon);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("No se ha seleccionado ningún Pokémon");
        }
    }



    @FXML
    public void desequiparObjetoOnAction() {
        try (Connection connection = DBConnection.getConnection()) {
            String updateSQL = "UPDATE POKEMON SET ID_OBJETO = NULL WHERE ID_POKEMON = ?";
            PreparedStatement statement = connection.prepareStatement(updateSQL);
            statement.setInt(1, pokemonSeleccionado.getIdPokemon());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                imagenObjetoEquipado.setImage(null);
                logMochila.appendText("Se ha desequipado el objeto del pokemon " + "'"+pokemonSeleccionado.getNombre()+"'" + "\n");
            } else {
                logMochila.appendText("No se encontró ningún objeto equipado para el Pokémon " + "'"+pokemonSeleccionado.getNombre()+ "'" + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}




