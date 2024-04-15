package es.cesur.progprojectpok;


import es.cesur.progprojectpok.controllers.SplashController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SplashApplication extends Application {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SplashApplication.class.getResource("view/splash-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 760, 750);
        scene.getStylesheets().add(getClass().getResource("styles/styles.css").toExternalForm());
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();

        Object controller = fxmlLoader.getController();
        if (controller instanceof SplashController) { // Asegúrate de reemplazar 'MiControlador' con el nombre real de tu controlador
            SplashController controllerSplash = (SplashController) controller;


            // Tarea para simular el progreso
            Task<Void> tarea = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    for (int i = 0; i <= 20; i++) {
                        final double progreso = i / 20.0;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                controllerSplash.actualizarProgreso(progreso);
                            }
                        });
                        Thread.sleep(800); // Simular trabajo en segundo plano

                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader(SplashApplication.class.getResource("view/login-view.fxml"));
                            Scene scene = null;
                            try {
                                scene = new Scene(fxmlLoader.load(), 716, 717);
                                scene.getStylesheets().add(getClass().getResource("styles/styles.css").toExternalForm());
                                stage.setTitle("Menu");
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            MediaPlayer mpMusic = controllerSplash.getMpMusic();
                            mpMusic.setOnEndOfMedia(null);
                           // mpMusic.stop();  ESTA COMENTADO PARA QUE LA PANTALLA DE LOGIN CONTINUE CON LA MUSICA.

                            Label label = controllerSplash.getLabelPorcentaje();
                            Stage stageAnterior = (Stage) label.getScene().getWindow();
                            stageAnterior.close();
                        }
                    });


                    stop();
                    return null;
                }
            };

            // Ejecutar la tarea en segundo plano
            executorService.submit(tarea);


        }


    }

    @Override
    public void stop() throws Exception {
        executorService.shutdownNow();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}