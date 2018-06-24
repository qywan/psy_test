/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static psy.Main.stage;


/**
 *
 * @author dell
 */
class ClassAdd extends Application{
FXMLDocumentClassController controller;
public Stage stage;
public  void setLastController(FXMLDocumentClassController controller) {
     this.controller=controller;
    }

 public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.initOwner(AdminMain.getSingleton().getStage());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentClassAdd.fxml"));
        Parent root = fxmlLoader.load();
        FXMLDocumentClassAddController fsc =  fxmlLoader.getController();
        fsc.setData(this);
        
        Scene scene = new Scene(root);
        stage.setTitle("添加班级信息");
        stage.setScene(scene);
        stage.show();
  
  
  }
    
}
