package es.cesur.progprojectpok.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AsignarMoteController {

    @FXML
    private TextField nombreHijo;

    private String moteIngresado;

    @FXML
    public void initialize() {
        moteIngresado = "";
    }

    @FXML
    private void asignarMote() {
        moteIngresado = nombreHijo.getText();
        closeWindow();
    }

    public String getMoteIngresado() {
        return moteIngresado;
    }

    private void closeWindow() {
        nombreHijo.getScene().getWindow().hide();
    }
}
