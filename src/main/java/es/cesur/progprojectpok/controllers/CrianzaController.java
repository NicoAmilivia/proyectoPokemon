package es.cesur.progprojectpok.controllers;
import es.cesur.progprojectpok.clases.Entrenador;
import es.cesur.progprojectpok.clases.Pokemon;
import es.cesur.progprojectpok.database.DBConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;


public class CrianzaController {

    @FXML
    private Button irMenuFromCrianzaButton;


    @FXML
    private ListView<Pokemon> machoViewList;

    @FXML
    private ListView<Pokemon> hembraViewList;

    @FXML
    private List<Pokemon> pokemonMacho;

    @FXML
    private List<Pokemon> pokemonHembra;

    @FXML

    private TextField logCrianza;

    @FXML
    private TextField logOnAction;

    @FXML
    private Button abrirHuevoButton;

    private Pokemon machoSeleccionado;

    private Pokemon hembraSeleccionada;

    private Pokemon pokemonHijo;

    private boolean redurcirFertilidad;

    private int numPokedexHijo;

    private Entrenador entrenador;



    public void initialize() {

        cargarMacho();
        cargarHembra();

        machoViewList.setItems(FXCollections.observableArrayList(pokemonMacho));
        hembraViewList.setItems(FXCollections.observableArrayList(pokemonHembra));
    }


    private List<Pokemon> cargarPokemonesDesdeBD(char sexo) {
        List<Pokemon> pokemones = new ArrayList<>();

        String sql = "SELECT * FROM POKEMON WHERE SEXO = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, String.valueOf(sexo));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("MOTE");
                int numPokedex = resultSet.getInt("NUM_POKEDEX");
                int ataque = resultSet.getInt("ATAQUE");
                int defensa = resultSet.getInt("DEFENSA");
                int ataqueEspecial = resultSet.getInt("AT_ESPECIAL");
                int defensaEspecial = resultSet.getInt("DEF_ESPECIAL");
                int velocidad = resultSet.getInt("VELOCIDAD");
                int nivel = resultSet.getInt("NIVEL");
                int experiencia = resultSet.getInt("EXPERIENCIA");
                int vitalidad = resultSet.getInt("VITALIDAD");
                int idPokemon = resultSet.getInt("ID_POKEMON");
                int fertilidad = resultSet.getInt("FERTILIDAD");
                Pokemon pokemon = new Pokemon(nombre, numPokedex, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad, nivel, experiencia, vitalidad, idPokemon , fertilidad);
                pokemones.add(pokemon);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los Pokémon desde la base de datos: " + e.getMessage());
            e.printStackTrace();
        }




        return pokemones;
    }

    private void cargarMacho() {
        pokemonMacho = cargarPokemonesDesdeBD('M');
    }

    private void cargarHembra() {
        pokemonHembra = cargarPokemonesDesdeBD('H');
    }


    @FXML
    private void irMenuFromCrianzaOnAction() {

        Stage stage = (Stage) irMenuFromCrianzaButton.getScene().getWindow();
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
    private void realizarCrianzaOnAction() {
        machoSeleccionado = machoViewList.getSelectionModel().getSelectedItem();
        hembraSeleccionada = hembraViewList.getSelectionModel().getSelectedItem();

        if (machoSeleccionado == null || hembraSeleccionada == null) {
            logCrianza.setText("Debe seleccionar un macho y una hembra para la crianza.");
            return;
        }

        if (machoSeleccionado.getNumPokedex() != hembraSeleccionada.getNumPokedex()) {
            logCrianza.setText("Los Pokémon seleccionados deben ser de la misma especie.");
            return;
        }


        if (machoSeleccionado.getFertilidad() <= 0 || hembraSeleccionada.getFertilidad() <= 0) {
            logCrianza.setText("Los padres no tienen suficiente fertilidad para la crianza.");
            return;
        }

        pokemonHijo = generarHijo(machoSeleccionado, hembraSeleccionada);

        if (pokemonHijo != null) {
            // Reducir la fertilidad de los padres en 1 punto
            reducirFertilidadPadres(machoSeleccionado);
            reducirFertilidadPadres(hembraSeleccionada);

            // Mostrar mensaje de éxito y agregar el hijo a la base de datos
            logCrianza.setText("¡Crianza realizada con éxito! Abra el huevo");
        } else {
            logCrianza.setText("Error al generar el Pokémon hijo.");
        }
    }


    private Pokemon generarHijo(Pokemon machoSeleccionado, Pokemon hembraSeleccionada) {




        pokemonHijo = new Pokemon();
        pokemonHijo.setNombre(machoSeleccionado.getNombre());
        pokemonHijo.setNumPokedex(machoSeleccionado.getNumPokedex());
        pokemonHijo.setSexo(randomSex());
        pokemonHijo.setTipo1(machoSeleccionado.getTipo1());
        pokemonHijo.setTipo2(machoSeleccionado.getTipo2());
        pokemonHijo.setFertilidad(Math.max(machoSeleccionado.getFertilidad(), hembraSeleccionada.getFertilidad()));
        pokemonHijo.setAtaque(Math.max(machoSeleccionado.getAtaque(), hembraSeleccionada.getAtaque()));
        pokemonHijo.setDefensa(Math.max(machoSeleccionado.getDefensa(), hembraSeleccionada.getDefensa()));
        pokemonHijo.setAtaqueEspecial(Math.max(machoSeleccionado.getAtaqueEspecial(), hembraSeleccionada.getAtaqueEspecial()));
        pokemonHijo.setDefensaEspecial(Math.max(machoSeleccionado.getDefensaEspecial(), hembraSeleccionada.getDefensaEspecial()));
        pokemonHijo.setVelocidad(Math.max(machoSeleccionado.getVelocidad(), hembraSeleccionada.getVelocidad()));
        pokemonHijo.setNivel(1);
        pokemonHijo.setExperiencia(0);
        pokemonHijo.setVitalidad(Math.max(machoSeleccionado.getVitalidad(), hembraSeleccionada.getVitalidad()));




        return pokemonHijo;

    }



    private void reducirFertilidadPadres(Pokemon pokemon) {
        int nuevaFertilidad = pokemon.getFertilidad() - 1;
        pokemon.setFertilidad(nuevaFertilidad);

        String updateFertilidad = "UPDATE POKEMON SET FERTILIDAD = ? WHERE ID_POKEMON = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateFertilidad)) {

            statement.setInt(1, nuevaFertilidad);
            statement.setInt(2, pokemon.getIdPokemon());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Fertilidad actualizada correctamente para el Pokémon con ID: " + pokemon.getIdPokemon());
            } else {
                System.out.println("No se encontró el Pokémon con ID: " + pokemon.getIdPokemon());
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la fertilidad del Pokémon");
            e.printStackTrace();
        }
    }


    private char randomSex() {
        return Math.random() < 0.5 ? 'M' : 'H';
    }




    private void insertarPokemonEnBD(Pokemon pokemon) throws SQLException {
        String sql = "INSERT INTO POKEMON (NUM_POKEDEX,ID_ENTRENADOR, MOTE, CAJA, ATAQUE, AT_ESPECIAL, DEFENSA, DEF_ESPECIAL, VELOCIDAD, NIVEL, FERTILIDAD, SEXO, ESTADO, EXPERIENCIA, VITALIDAD,VIDA_ACTUAL) " +
                "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, pokemon.getNumPokedex());
            statement.setInt(2,entrenador.getId());
            statement.setString(3, pokemon.getNombre());
            statement.setInt(4, 1); // Ajustar el valor de la caja según corresponda
            statement.setInt(5, pokemon.getAtaque());
            statement.setInt(6, pokemon.getAtaqueEspecial());
            statement.setInt(7, pokemon.getDefensa());
            statement.setInt(8, pokemon.getDefensaEspecial());
            statement.setInt(9, pokemon.getVelocidad());
            statement.setInt(10, pokemon.getNivel());
            statement.setInt(11, pokemon.getFertilidad());
            statement.setString(12, String.valueOf(pokemon.getSexo()));
            statement.setString(13, "Normal"); // Establecer el estado según corresponda
            statement.setInt(14, pokemon.getExperiencia());
            statement.setInt(15, pokemon.getVitalidad());
            statement.setInt(16, pokemon.getVitalidad());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo insertar el Pokémon en la base de datos");
            }

            // Obtener el ID generado automáticamente
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idPokemon = generatedKeys.getInt(1); // Obtener el valor del ID del Pokémon hijo
                    pokemonHijo.setIdPokemon(idPokemon); // Establecer el ID del Pokémon hijo en el objeto Pokemon

                    String insertMovimiento = "INSERT INTO MOVIMIENTOS_POKEMON (ID_MOVIMIENTO, ID_POKEMON, ACTIVO ) VALUES (?,?,?)";
                    try (PreparedStatement statementMovimiento = connection.prepareStatement(insertMovimiento)){
                        statementMovimiento.setInt(1, 7);
                        statementMovimiento.setInt(2,pokemonHijo.getIdPokemon());
                        statementMovimiento.setString(3,"S");
                        statementMovimiento.executeUpdate();
                    }

                } else {
                    throw new SQLException("No se generó ningún ID para el Pokémon hijo");
                }

            }
        } catch (SQLException e) {
            // Manejar cualquier excepción que pueda ocurrir al ejecutar la consulta SQL
            e.printStackTrace();
            // Puedes mostrar un mensaje de error al usuario si lo deseas
        }

    }

    @FXML
    public void abrirHuevoOnAction() {
        if (pokemonHijo == null) {
            logCrianza.setText("No hay huevo que abrir. Realiza primero la crianza.\n");
            return;
        }



        try {
            // Insertar el Pokémon hijo en la base de datos
            insertarPokemonEnBD(pokemonHijo);

            // Mostrar un mensaje indicando que se agregó el Pokémon a la base de datos
            logCrianza.setText("¡Huevo abierto! Nuevo Pokémon agregado " + pokemonHijo.getNombre());
        } catch (SQLException e) {
            logCrianza.setText("Error al agregar el nuevo Pokémon a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }



        try {
            pokemonHijo= generarHijo(machoSeleccionado,hembraSeleccionada);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/asignar-mote.fxml"));
            Parent root = fxmlLoader.load();
            Stage menuStage = new Stage();
            AsignarMoteController asignarMoteController = fxmlLoader.getController();
            asignarMoteController.setPokemonHijo(pokemonHijo);
            asignarMoteController.setMachoSeleccionado(machoSeleccionado);
            asignarMoteController.setCrianzaController(this);
            menuStage.setTitle("Asignar Mote");
            menuStage.setScene(new Scene(root, 590, 600));
            menuStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Maneja el error apropiadamente
        }

    }


    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }


    public void actualizarLogCrianza(String mensaje){
        logCrianza.setText(mensaje);
    }
}