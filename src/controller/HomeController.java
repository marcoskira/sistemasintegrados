package controller;

import model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML private Label usernameLabel;
    @FXML private Label filenameText;
    private User user;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameLabel.setText("Testezao");
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFilenameText(String text){
        this.filenameText.setText(text);
    }


    public void openFilePicker(){

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            setFilenameText(selectedFile.getName());
        }
        else {
            System.out.println("File selection cancelled.");
        }
    }



}
