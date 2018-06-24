/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LYZ
 */
public class Tips extends Application{
    public static Stage stage;
    public static String message ="";
    
    public Tips(String message){
        this.message = message;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentTips.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("注册");
        stage.setScene(scene);
        stage.show();
    }

}
