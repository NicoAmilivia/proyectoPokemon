package es.cesur.progprojectpok.controllers;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConfirmarCompraController {

    @FXML
    private Button aceptarCompraButton;

    @FXML
    private Button cancelarCompraButton;

    @FXML
    private Text textoConfirmacion;

    private String nombreObjeto;

    private int precioObjeto;

    private boolean compraConfirmada = false;
    @FXML
    private void aceptarCompraOnAction() {
        compraConfirmada = true;
        cerrarVentana();
    }
    @FXML
    private void cancelarCompraOnAction() {
        cerrarVentana();
    }



    public void initialize(){
        agregarAnimacionSeleccion(aceptarCompraButton);
       agregarAnimacionSeleccion(cancelarCompraButton);
    }

    public boolean isCompraConfirmada() {
        return compraConfirmada;
    }

    private void cerrarVentana() {
        Stage stage = (Stage) aceptarCompraButton.getScene().getWindow();
        stage.close();
    }

    public void setNombrePrecioObjeto(String nombreObjeto,int precioObjeto) {
        this.nombreObjeto = nombreObjeto;
        this.precioObjeto=precioObjeto;
        textoConfirmacion.setText("¿Deseas comprar el objeto " + nombreObjeto.toUpperCase() + " por " + precioObjeto + " pokedollars?" );
    }


    private void agregarAnimacionSeleccion(Button boton) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), boton);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);
        boton.setOnMouseEntered(event -> scaleTransition.playFromStart());
        boton.setOnMouseExited(event -> scaleTransition.stop());
    }

}
