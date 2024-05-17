package es.cesur.progprojectpok.controllers;
import es.cesur.progprojectpok.clases.Entrenador;
import es.cesur.progprojectpok.clases.Tipo;
import es.cesur.progprojectpok.clases.Pokemon;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class CapturaController {

    private Pokemon pokemon;
    @FXML
    private ImageView pokemonImageView;
    @FXML
    private Button irMenuFromCapturarButton;

    @FXML
    private Button capturarNuevo;

    @FXML
    private Button capturar;

    private Entrenador entrenador;

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    Random random = new Random();

    @FXML
    private TextArea logCaptura;

    public void initialize() {

        int pokemonId = random.nextInt(151) + 1;
        // Cargar la imagen del Pokémon según el ID generado
        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonImageView.setImage(image);

        String sql = "SELECT * FROM pokedex WHERE NUM_POKEDEX = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pokemonId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
               String imagenPokemon = resultSet.getString("IMAGEN");
               String nombrePokemon = resultSet.getString("NOM_POKEMON");


                Pokemon pokemon = new Pokemon(nombrePokemon, pokemonId);
                setPokemon(pokemon);
                System.out.println(pokemon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Pokemon no existente.");
        }
    }

    @FXML
    private void irMenuFromCapturarOnAction() {

        Stage stage = (Stage) irMenuFromCapturarButton.getScene().getWindow();
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


    @FXML
    private void capturarOnAction(){


        int capturar = random.nextInt(3);

        String sql = "INSERT INTO POKEMON (NUM_POKEDEX, ID_ENTRENADOR, MOTE, CAJA, ATAQUE, AT_ESPECIAL, DEFENSA, DEF_ESPECIAL, VELOCIDAD, NIVEL, FERTILIDAD, SEXO, ESTADO, EXPERIENCIA, VITALIDAD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if (capturar >= 1) {

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setInt(1, pokemon.getNumPokedex());
                statement.setInt(2, 1);
                statement.setString(3, pokemon.getNombre());
                statement.setInt(4, 1);
                statement.setInt(5, pokemon.getAtaque());
                statement.setInt(6, pokemon.getAtaqueEspecial());
                statement.setInt(7, pokemon.getDefensa());
                statement.setInt(8, pokemon.getDefensaEspecial());
                statement.setInt(9, pokemon.getVelocidad());
                statement.setInt(10, pokemon.getNivel());
                statement.setInt(11, pokemon.getFertilidad());
                statement.setString(12, String.valueOf(pokemon.getSexo()));
                statement.setString(13, "Saludable");
                statement.setInt(14, pokemon.getExperiencia());
                statement.setInt(15, pokemon.getVitalidad());

                // Ejecutar la consulta
                int filasInsertadas = statement.executeUpdate();

                if (filasInsertadas > 0) {
                    logCaptura.appendText("Has capturado al Pokemon " + pokemon.getNombre() + "\n");
                } else {
                    System.out.println("No se pudo insertar el Pokemon en la base de datos.");
                }


                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idPokemon = generatedKeys.getInt(1);
                        pokemon.setIdPokemon(idPokemon);

                        String insertMovimiento = "INSERT INTO MOVIMIENTOS_POKEMON (ID_MOVIMIENTO, ID_POKEMON, ACTIVO ) VALUES (?,?,?)";
                        try (PreparedStatement statementMovimiento = connection.prepareStatement(insertMovimiento)){
                            statementMovimiento.setInt(1, 7);
                            statementMovimiento.setInt(2,pokemon.getIdPokemon());
                            statementMovimiento.setString(3,"S");
                            statementMovimiento.executeUpdate();
                        }

                    } else {
                        throw new SQLException("No se generó ningún ID para el Pokémon");
                    }

                }

            } catch (SQLException e) {
                System.out.println("Error al insertar el Pokemon en la base de datos: " + e.getMessage());
            }
        }else {
            logCaptura.appendText("El pokemon ha escapado\n");
            capturarNuevoOnAction();
        }
    }


    @FXML
    private void capturarNuevoOnAction(){
        //Generar un número aleatorio para el ID del Pokémon

        int pokemonId = random.nextInt(151) + 1;
        // Cargar la imagen del Pokémon según el ID generado
        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonImageView.setImage(image);

        String sql = "SELECT * FROM pokedex WHERE NUM_POKEDEX = ?";


        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pokemonId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String imagenPokemon = resultSet.getString("IMAGEN");
                String nombrePokemon = resultSet.getString("NOM_POKEMON");


                Pokemon pokemon = new Pokemon(nombrePokemon, pokemonId);
                setPokemon(pokemon);
                logCaptura.appendText("Ha aparecido un " + nombrePokemon + " salvaje\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
}
