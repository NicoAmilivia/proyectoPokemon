package es.cesur.progprojectpok.controllers;
import es.cesur.progprojectpok.clases.Entrenador;
import es.cesur.progprojectpok.clases.Tipo;
import es.cesur.progprojectpok.clases.Pokemon;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void initialize() {
        //Generar un número aleatorio para el ID del Pokémon
        Random random = new Random();
        int pokemonId = random.nextInt(151) + 1;
        // Cargar la imagen del Pokémon según el ID generado
        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonImageView.setImage(image);

        try(Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM pokedex WHERE NUM_POKEDEX = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
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

        Random random = new Random();
        int capturar = random.nextInt(3);

        if (capturar >= 1) {

            try (Connection connection = DBConnection.getConnection()) {
                String sql = "INSERT INTO POKEMON (NUM_POKEDEX, ID_ENTRENADOR, MOTE, CAJA, ATAQUE, AT_ESPECIAL, DEFENSA, DEF_ESPECIAL, VELOCIDAD, NIVEL, FERTILIDAD, SEXO, ESTADO, EXPERIENCIA, VITALIDAD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
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
                    System.out.println("Pokemon insertado en la base de datos correctamente.");
                } else {
                    System.out.println("No se pudo insertar el Pokemon en la base de datos.");
                }
            } catch (SQLException e) {
                System.out.println("Error al insertar el Pokemon en la base de datos: " + e.getMessage());
            }
        }else
            capturarNuevoOnAction();
    }


    @FXML
    private void capturarNuevoOnAction(){
        //Generar un número aleatorio para el ID del Pokémon
        Random random = new Random();
        int pokemonId = random.nextInt(151) + 1;
        // Cargar la imagen del Pokémon según el ID generado
        String imageUrl = String.format("/es/cesur/progprojectpok/images/pokemon/%03d.png", pokemonId);
        Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
        pokemonImageView.setImage(image);

        try(Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM pokedex WHERE NUM_POKEDEX = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
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


    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
}
