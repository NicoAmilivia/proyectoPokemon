package es.cesur.progprojectpok.controllers;

import es.cesur.progprojectpok.clases.Entrenador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.IOException;

public class TiendaController {

    @FXML
    private Button irMenuFromTiendaButton;

    @FXML
    private Text pokedollarsText;

    @FXML
    private TextArea logTienda;


    private Entrenador entrenador;






    @FXML
    public void mancuernaPush(MouseEvent mouseEvent) {
        logTienda.appendText("Has comprado el objeto PESA!\n");
    }
    @FXML
    public void plumaPush(MouseEvent mouseEvent) {
        logTienda.appendText("Has comprado el objeto PLUMA!\n");
    }
    @FXML
    public void chalecoPush(MouseEvent mouseEvent) {
        logTienda.appendText("Has comprado el objeto CHALECO!!\n");
        System.out.println("polla");
    }
    @FXML
    public void bastonPush(MouseEvent mouseEvent) {
        logTienda.appendText("Has comprado el objeto BASTON!!\n");
    }
    @FXML
    public void pilaPush(MouseEvent mouseEvent) {
        System.out.println("Se ha hecho click en pila");
        logTienda.appendText("Has comprado el objeto PILA!!\n");
    }
    @FXML
    public void EterPush(MouseEvent mouseEvent) {
        logTienda.appendText("Has comprado el objeto ETER!\n");
    }

    @FXML
    public void anilloUnicoPush(MouseEvent mouseEvent) {
        logTienda.appendText("Has comprado el objeto ANILLO!\n");
    }

    @FXML
    public void pokeballPush(MouseEvent mouseEvent) {
        logTienda.appendText("Has comprado el objeto POKEBALL!\n");
    }







    @FXML
    public void irMenuFromTiendaOnAction() {


            Stage stage = (Stage) irMenuFromTiendaButton.getScene().getWindow();
            stage.close();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/es/cesur/progprojectpok/view/menu-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 590, 600);
                Stage menuStage = new Stage();
                menuStage.setTitle("Menu");
                MenuController Menucontroller = fxmlLoader.getController();
                Menucontroller.setEntrenador(entrenador);
                menuStage.setScene(scene);
                menuStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    public void setEntrenador(Entrenador entrenador){
        this.entrenador=entrenador;;
        pokedollarsText.setText(String.valueOf(entrenador.getNumPokeDollars()));

    }




}

