package view;

//Imports
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LoginView {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public LoginView(){
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("login.fxml"));
    }

    public void start() {
        try {

            this.scene = new Scene(fxmlLoader.load());
            this.stage = new Stage();

            this.stage.setScene(this.scene);
            this.stage.show();

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
