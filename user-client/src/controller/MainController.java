package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import model.Request;
import model.User;
import model.Image;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private TextField filenameText;
    @FXML private Pane content;
    @FXML private Button selectFileBtn;
    @FXML private Button uploadFileBtn;
    @FXML private ListView<Hyperlink> imgList;

    private File signalFile;
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signalFile = null;

        //Initiliaze "Upload file" objects
        selectFileBtn = new Button();
        selectFileBtn.setText("Select file");
        selectFileBtn.setLayoutY(169);
        selectFileBtn.setLayoutX(50);
        selectFileBtn.setPrefWidth(135);
        selectFileBtn.setOnAction(event -> openFilePicker());

        filenameText = new TextField();
        filenameText.setLayoutY(169);
        filenameText.setLayoutX(194);
        filenameText.setPrefWidth(228);

        uploadFileBtn = new Button();
        uploadFileBtn.setText("Upload");
        uploadFileBtn.setLayoutY(169);
        uploadFileBtn.setLayoutX(430);
        uploadFileBtn.setPrefWidth(135);
        uploadFileBtn.setOnAction(event -> uploadFile());

        content.getChildren().addAll(selectFileBtn, filenameText, uploadFileBtn);
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setFilenameText(String text){
        this.filenameText.setText(text);
    }

    public void openMyImagesView(){
        content.getChildren().clear();

        ObservableList<Hyperlink> list = FXCollections.observableArrayList();
        imgList = new ListView<>();
        imgList.setItems(list);
        imgList.setPrefWidth(600);

        for (Image img : user.getImgs()){
            imgList.getItems().add(new Hyperlink(img.getImagePath()));
        }

        content.getChildren().add(imgList);


    }

    public void openUploadFileView(){
        content.getChildren().clear();
        content.getChildren().addAll(selectFileBtn, filenameText, uploadFileBtn);
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
            propfile = new FileInputStream("src/controller/filepath.properties");
            prop.load(propfile);

            //verify if user has a folder, if not, create one
            userFolder = new File(prop.getProperty("SIGNALFILE_PATH") + "/" + this.user.getLogin());
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
        return userFolder.getPath() + "\\" + file.getName();
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
