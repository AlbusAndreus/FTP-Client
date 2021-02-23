package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    List <File> fileList = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("FTP Client");

        GridPane gp = new GridPane();



        Button selectFiles = new Button("Select Files");
        GridPane.setConstraints(selectFiles, 0,2);
        selectFiles.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileList = fileChooser.showOpenMultipleDialog(primaryStage);
        });

        Label address = new Label("address");
        GridPane.setConstraints(address, 0,0);

        TextField Address = new TextField();
        GridPane.setConstraints(Address, 1,0);

        Label username = new Label("Username:");
        GridPane.setConstraints(username, 0,1);

        TextField UserName = new TextField();
        GridPane.setConstraints(UserName,1,1);

        Label password = new Label("Password");
        GridPane.setConstraints(password,2,0);

        TextField Password = new TextField();
        GridPane.setConstraints(Password, 2, 1);


        Button upload = new Button("Upload");
        GridPane.setConstraints(upload, 2,2);
        upload.setOnAction(event -> {

            try {
                FTPUploader ftpUploader = new FTPUploader(Address.getText(), UserName.getText(), Password.getText());
               
                for(int count = 0; count <= fileList.size()-1; count++){
                    ftpUploader.uploadFile(fileList.get(count).getAbsolutePath(), fileList.get(count).getName(), "/home/8ehhnc/web/verboplex.com/public_html/");

                }
                ftpUploader.disconnect();
                System.out.println("Done");
            }catch(Exception e){
                e.printStackTrace();
            }
        });
        gp.getChildren().addAll(Address, address, password, Password, username, UserName, upload, selectFiles);
        primaryStage.setScene(new Scene(gp, 300, 275));
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
