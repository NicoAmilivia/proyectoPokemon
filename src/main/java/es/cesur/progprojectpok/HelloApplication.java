package es.cesur.progprojectpok;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

/*        UserManager userManager = new UserManager();
        Boolean loginOk = userManager.login("miguel", "1234");
        System.out.println(loginOk);

        User user = new User("angel", "1234", true, "angel@email.es");
        userManager.signIn(user);*/



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 650);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}