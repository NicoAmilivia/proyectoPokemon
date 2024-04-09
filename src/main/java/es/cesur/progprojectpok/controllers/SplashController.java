package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.utils.Utils;
import es.cesur.progprojectpok.utils.UtilsAudio;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * <!-- https://mvnrepository.com/artifact/org.openjfx/javafx-media -->
 * <dependency>
 * <groupId>org.openjfx</groupId>
 * <artifactId>javafx-media</artifactId>
 * <version>22</version>
 * </dependency>
 */
public class SplashController implements Initializable {

    public static final String PATH_SPLASH_BG_MUSIC = Utils.PATH_BASE_SOURCES + "/audio/splash.mp3";
    private static final String PATH_IMAGE_ENTRENADOR = Utils.PATH_BASE_SOURCES + "/images/entren-espaldas.png";


    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label labelPorcentaje;

    @FXML
    private ImageView imageViewEntrenador;
    @FXML
    private ImageView imageViewEntrenadorSinBg;


    @FXML
    private ImageView imgLogoCesur;

    MediaPlayer mpMusic;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mpMusic = UtilsAudio.playAudio(PATH_SPLASH_BG_MUSIC, true);

        imgLogoCesur.setOpacity(0.0);
        // Crear la animación
        Timeline timeline = new Timeline(
                // Aumentar opacidad de 0 a 1 durante los primeros 5 segundos
                new KeyFrame(Duration.seconds(0), new KeyValue(imageViewEntrenador.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(5), new KeyValue(imageViewEntrenador.opacityProperty(), 1)),
                // Disminuir opacidad de 1 a 0 durante los siguientes 5 segundos
                //new KeyFrame(Duration.seconds(10), new KeyValue(imageViewEntrenador.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(10), new KeyValue(imageViewEntrenador.opacityProperty(), 0))
        );
        // Iniciar la animación
        timeline.play();


        // Crear la animación
        Timeline timeline2 = new Timeline(
                // Aumentar opacidad de 0 a 1 durante los primeros 5 segundos
                new KeyFrame(Duration.seconds(0), new KeyValue(imageViewEntrenadorSinBg.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(5), new KeyValue(imageViewEntrenadorSinBg.opacityProperty(), 1)),
                // Disminuir opacidad de 1 a 0 durante los siguientes 5 segundos
                //new KeyFrame(Duration.seconds(10), new KeyValue(imageViewEntrenadorSinBg.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(15), new KeyValue(imageViewEntrenadorSinBg.opacityProperty(), 0))
        );

// Animación de translación para el segundo ImageView
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(10), imageViewEntrenadorSinBg);
        translateTransition.setFromX(imageViewEntrenadorSinBg.getLayoutX() - imageViewEntrenadorSinBg.getFitWidth() + 100);
        translateTransition.setFromY(imageViewEntrenadorSinBg.getLayoutY() - 25);
        translateTransition.setToX(imageViewEntrenador.getLayoutX() - imageViewEntrenador.getFitWidth() + 75);
        translateTransition.setToY(imageViewEntrenador.getLayoutY() - 25);
        //translateTransition.play();

        // Ejecutar ambas animaciones simultáneamente
        ParallelTransition parallelTransition = new ParallelTransition(timeline2, translateTransition);


        parallelTransition.play();


        Timeline timeline3 = new Timeline(
                // Aumentar opacidad de 0 a 1 durante los primeros 5 segundos
                new KeyFrame(Duration.seconds(0), new KeyValue(imgLogoCesur.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(5), new KeyValue(imgLogoCesur.opacityProperty(), 0)),
                // Disminuir opacidad de 1 a 0 durante los siguientes 5 segundos
                //new KeyFrame(Duration.seconds(10), new KeyValue(imgLogoCesur.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(10), new KeyValue(imgLogoCesur.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(15), new KeyValue(imgLogoCesur.opacityProperty(), 0))
        );
        // Iniciar la animación
        timeline3.play();

    }

    public void actualizarProgreso(double progreso) {
        progressBar.setProgress(progreso);
        int porcentaje = (int) (progreso * 100);
        labelPorcentaje.setText(porcentaje + "%");
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public Label getLabelPorcentaje() {
        return labelPorcentaje;
    }

    public void setLabelPorcentaje(Label labelPorcentaje) {
        this.labelPorcentaje = labelPorcentaje;
    }

    public MediaPlayer getMpMusic() {
        return mpMusic;
    }

    public void setMpMusic(MediaPlayer mpMusic) {
        this.mpMusic = mpMusic;
    }
}