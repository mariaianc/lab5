package Service;

import Service.ControllerGUI;
import com.example.proj_fxml.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main  extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cake.fxml"));
        fxmlLoader.setController(new ControllerGUI());
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        org.burningwave.core.assembler.StaticComponentContainer.Modules.exportAllToAll();
        launch();
    }
}
