import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("view./login.fxml"));
        primaryStage.setTitle("Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

 /*
        Parent root = FXMLLoader.load(getClass().getResource("view./main.fxml"));
        primaryStage.setTitle("Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}