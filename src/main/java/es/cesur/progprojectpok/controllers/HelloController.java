package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.ImageData;
import es.cesur.progprojectpok.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    public static final String PATH_IMG_FONDO = Utils.PATH_BASE_SOURCES + "/images/fondoplaya.png";
    public static final String PATH_IMG_CRIATURA = Utils.PATH_BASE_SOURCES + "/images/criatura-planta.png";
    public static final String PATH_IMG_CRIATURA2 = Utils.PATH_BASE_SOURCES + "/images/criatura-rayo.png";

    @FXML
    private Label welcomeText;

    @FXML
    private ImageView imgViewEjemplo;

    @FXML
    private ComboBox<String> comboBoxColores;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button btnHello;


    @FXML
    private TableView<ImageData> tableView;

    @FXML
    private TableColumn<ImageData, String> imageColumn;
    @FXML
    private TableColumn<ImageData, String> nameColumn;



    @FXML
    private ProgressBar progressBar;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
// ComboBox: selecciona un valor por defecto
        comboBoxColores.getItems().addAll("Opción 1", "Opción 2", "Opción 3");
        comboBoxColores.getSelectionModel().select("Opción 2");

        // ImageView: establece una imagen por defecto

        File fileImageFondo = new File(PATH_IMG_FONDO);
        imgViewEjemplo.setImage(new Image(fileImageFondo.getAbsolutePath()));



        // Slider: establece un valor por defecto
        progressBar.setProgress(0.5);

        listView.getItems().addAll("Elemento 1", "Elemento 2", "Elemento 3");



        // Configura la columna de nombre como normal
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Configura la columna de imagen para mostrar una ImageView
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("imagePath"));
        imageColumn.setCellFactory(tc -> new TableCell<ImageData, String>() {
            private final ImageView imageView = new ImageView();
            {
                imageView.setFitHeight(50); // Ajusta a tus necesidades
                imageView.setFitWidth(50);
            }

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    File fileImageFondo = new File(imagePath);
                    imageView.setImage(new Image(fileImageFondo.getAbsolutePath()));
                    setGraphic(imageView);
                }
            }
        });

        // Ejemplo de cómo añadir datos
        tableView.getItems().add(new ImageData(PATH_IMG_CRIATURA, "Criatura"));
        // Añade más datos según sea necesario


    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        tableView.getItems().add(new ImageData(PATH_IMG_CRIATURA2, "Criatura 2"));

        listView.getItems().add("Elemento 4");

        progressBar.setProgress(0.1);
        progressBar.setStyle("-fx-accent: red;");

        comboBoxColores.getItems().add("Opción 4");
        comboBoxColores.getSelectionModel().select("Opción 4");
    }


}