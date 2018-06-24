/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LYZ
 */
public class Main extends Application{
    public static Stage stage;
    public static String userId;
    public static int type;
    public Main(String id,int type){
        this.userId = id;
        this.type = type;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        
        String url;
        if(this.type == 0){
            url = "FXMLDocumentMain.fxml";
        }
        else{
            url = "FXMLDocumentMain.fxml";
        }
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root);
        
        stage.setTitle("主页");
        stage.setScene(scene);
        stage.show();
       
    }
    
    
}
