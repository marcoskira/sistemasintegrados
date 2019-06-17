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
            this.signalFile = selectedFile;
        }
        else {
            System.out.println("File selection cancelled.");
        }
    }

    public String uploadIntoFileServer(File file){
        Properties prop = new Properties();
        FileInputStream propfile = null;
        File userFolder = null;
        try{
            propfile = new FileInputStream("E:/Dropbox/Faculdade/Sistemas Integrados/out/production/Sistemas Integrados/controller/filepath.properties");
            prop.load(propfile);

            //verify if user has a folder, if not, create one
            userFolder = new File(prop.getProperty("SIGNALFILE_PATH") + "/" + this.user.getLogin());
            System.out.println(prop.getProperty("SIGNALFILE_PATH") + "/" + this.user.getLogin());
            if(!userFolder.exists()){
                System.out.println("Creating " + this.user.getLogin() + " directory...");
                userFolder.mkdirs();
                System.out.println("Directory successfully created");
            }

            try {
                FileUtils.copyFileToDirectory(file, userFolder);
                System.out.println("Signal file successfully transferred");
            } catch (Exception e){
                System.out.println("Error while transferring file to server");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return userFolder.getPath();
    }

    public boolean createNewRequest(String filepath){
        Request request = new Request();
        request.setUserId(user.getUserId());
        request.setSignalSize(((float) signalFile.length()));
        request.setSignalPath(filepath);

        return request.createNewRequest();
    }


    public void uploadFile(){
        if(this.signalFile != null){
            String filepath = uploadIntoFileServer(this.signalFile);

            if(filepath != null){
                if(createNewRequest(filepath)){
                    System.out.println("File successfully uploaded. Your request is now waiting to be processed");
                }
                else {
                    System.out.println("Error while uploading your file. Try again.");
                }
            }

        }else {
            System.out.println("Select a file before upload");
        }
    }



}
