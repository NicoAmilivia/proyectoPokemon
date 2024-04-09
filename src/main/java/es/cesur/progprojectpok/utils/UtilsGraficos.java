package es.cesur.progprojectpok.utils;

import es.cesur.progprojectpok.HelloApplication;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UtilsGraficos {


    public void resizeAndScaleImageInView(ImageView imageView, double newWidth, double newHeight) {
        // Asegúrate de que el ImageView tiene una imagen cargada
        if (imageView.getImage() != null){
            // Configura el ImageView para usar las nuevas dimensiones
            imageView.setFitWidth(newWidth);
            imageView.setFitHeight(newHeight);
            imageView.setPreserveRatio(false);

            // Crear un objeto SnapshotParameters para transformar la imagen
            SnapshotParameters parameters = new SnapshotParameters();

            // Escala para ajustar la imagen a las nuevas dimensiones
            double scaleX = newWidth / imageView.getImage().getWidth();
            double scaleY = newHeight / imageView.getImage().getHeight();
            parameters.setTransform(new Scale(scaleX, scaleY, 0, 0));

            // Toma un snapshot del ImageView con las nuevas dimensiones
            WritableImage newImage = imageView.snapshot(parameters, null);

            // Establece la imagen escalada de nuevo en el ImageView
            imageView.setImage(newImage);

            // Opcional: Restablecer las dimensiones del ImageView a las dimensiones de la imagen para evitar distorsión
            imageView.setFitWidth(newImage.getWidth());
            imageView.setFitHeight(newImage.getHeight());
        }


    }





        public static void scaleImage(File inputImageFile, File outputImageFile, int targetWidth, int targetHeight) throws IOException {
            // Leer la imagen original
            BufferedImage originalImage = ImageIO.read(inputImageFile);

            // Calcular el factor de escala para mantener la proporción
            double scaleFactor = Math.min((double) targetWidth / originalImage.getWidth(),
                    (double) targetHeight / originalImage.getHeight());

            // Calcular las nuevas dimensiones
            int scaledWidth = (int) (originalImage.getWidth() * scaleFactor);
            int scaledHeight = (int) (originalImage.getHeight() * scaleFactor);

            // Crear una nueva imagen con las nuevas dimensiones
            BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());

            // Dibujar la imagen escalada
            Graphics2D g2d = scaledImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();

            // Escribir la imagen escalada a disco
            ImageIO.write(scaledImage, "PNG", outputImageFile);
        }


    public ImageView escalarImage(ImageView imageView, Image image){
        // Ajustar el tamaño de visualización deseado
        imageView.setFitWidth(200); // Ancho deseado
        imageView.setFitHeight(200); // Alto deseado

        // Mantener la proporción de la imagen al escalar
        imageView.setPreserveRatio(true);

        return imageView;
    }
    public static Stage crearVistaGrafica(String pathView, String title, int width, int height, double duration) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(pathView));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), width, height);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (duration > 0) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(duration), e -> stage.close()));
            timeline.play();
        }
        return stage;
    }

    public static Stage crearVistaGraficaImage(String pathView, String title, int width, int height, double duration,
                                               Image image, Image image2, Image image3) {


        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(pathView));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), width, height);
            // Acceso al controlador
            Object controller = fxmlLoader.getController();
/*            if (controller instanceof CapturadoController) { // Asegúrate de reemplazar 'MiControlador' con el nombre real de tu controlador
                CapturadoController miControlador = (CapturadoController) controller;
                ImageView imageView = miControlador.getImageViewEntrenador(); // Asegúrate de que 'miImageView' coincide con el fx:id de tu ImageView en el FXML
                imageView.setImage(image); // Cambia la imagen
                ImageView imageView2 = miControlador.getImageViewTxt(); // Asegúrate de que 'miImageView' coincide con el fx:id de tu ImageView en el FXML
                imageView2.setImage(image2); // Cambia la imagen
                ImageView imageView3 = miControlador.getImageViewFondo(); // Asegúrate de que 'miImageView' coincide con el fx:id de tu ImageView en el FXML
                imageView3.setImage(image3); // Cambia la imagen
            }*/


            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        if (duration > 0) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(duration), e -> stage.close()));
            timeline.play();
        }
        return stage;
    }


    public static void main(String[] args) {
        try {
            // Ruta de la imagen original y destino de la imagen escalada
            File inputImage = new File("path/to/original/image.png");
            File outputImage = new File("path/to/scaled/image.png");

            // Dimensiones finales deseadas
            int targetWidth = 200;
            int targetHeight = 200;

            // Escalar la imagen
            scaleImage(inputImage, outputImage, targetWidth, targetHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
