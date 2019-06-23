package controller;

import model.Request;
import model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import org.apache.commons.io.FileUtils;
import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML private TextField filenameText;
    private User user;
    private File signalFile;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signalFile = null;
    }




    public void setFilenameText(String text){
        this.filenameText.setText(text);
    }


    public void openFilePicker(){

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            setFilenameText(selectedFile.getName());
            this.signalFile = selectedFile;
        }
        else {
            System.out.println("File selection cancelled.");
        }
    }




}
