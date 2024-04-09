package es.cesur.progprojectpok.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class UtilsAudio {


    public static MediaPlayer playAudio(String audioPath, boolean repeat) {
        // Crea un objeto Media con la ruta del archivo de audio
        Media sound = new Media(new File(audioPath).toURI().toString());

        // Crea el MediaPlayer con el objeto Media
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        if (repeat) {
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                public void run() {
                    mediaPlayer.seek(Duration.ZERO);
                }
            });
        }

        mediaPlayer.play();
        return mediaPlayer;
    }



}
