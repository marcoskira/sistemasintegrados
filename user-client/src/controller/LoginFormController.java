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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/main.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            MainController mainController = fxmlLoader.getController();
            mainController.setUser(user);
            Stage nextStage = new Stage();
            Stage currentStage = (Stage) passwordField.getScene().getWindow();
            nextStage.setScene(new Scene(root));
            nextStage.show();
            currentStage.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
