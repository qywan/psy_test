/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author LYZ
 */
public class StudentAdd extends Application{
    public Stage stage;
    public FXMLDocumentStudentController controller;
    public final String DEFAULT_PASSWORD = "123456";
    
    public void setLastController(FXMLDocumentStudentController controller){
        this.controller = controller;
    }
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.initOwner(AdminMain.getSingleton().getStage());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentStudentAdd.fxml"));
        Parent root = fxmlLoader.load();
        FXMLDocumentStudentAddController fsc =  fxmlLoader.getController();
        fsc.setData(this);
        
        Scene scene = new Scene(root);
        stage.setTitle("添加测评账号");
        stage.setScene(scene);
        stage.show();
    }
    
}
