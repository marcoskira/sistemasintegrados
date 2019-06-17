package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

public class LoginFormController {

    @FXML private TextField userField;
    @FXML private PasswordField passwordField;
    @FXML private Text registerTxt;
    private User user;


    public void validateCredentials(ActionEvent event){

        this.user = new User();
        int userId;
        userId = this.user.validateCredentials(userField.getText(), passwordField.getText());

        switch(userId){
            case -1:
                showAlertIncorrectLogin();
                break;
            case 0:
                showAlertIncorrectPassword();
                break;
            default:
                try {
                    this.user = this.user.getUserById(userId);
                    openHomeView();
                }
                catch(Exception e){
                    System.out.println(e);
                }
        }
    }

    public void showAlertIncorrectPassword(){
        System.out.println("Incorrect password");
    }

    public void showAlertIncorrectLogin(){
        System.out.println("User does not exist");
    }

    public void openHomeView(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/home.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            HomeController homeController = fxmlLoader.getController();
            homeController.setUser(user);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
