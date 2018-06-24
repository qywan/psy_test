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
import javafx.stage.Modality;
import static javafx.stage.Modality.APPLICATION_MODAL;
import javafx.stage.Stage;
import psy.FXMLDocumentMainController;

/**
 *
 * @author dell
 */
public class InteractAction extends Application{
   static Stage stage = null;
    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentInteract.fxml"));
       
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("心理互动");
        stage = primaryStage;

    }
    
}
