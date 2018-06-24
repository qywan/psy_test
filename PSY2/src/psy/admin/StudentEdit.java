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
import psy.bean.Student;

/**
 *
 * @author LYZ
 */
public class StudentEdit extends Application {
    public Student student;
    public Stage stage;
    
    public StudentEdit(Student student){
        this.student = student;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.initOwner(AdminMain.getSingleton().getStage());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentStudentEdit.fxml"));
        Parent root = fxmlLoader.load();
        FXMLDocumentStudentEditController fsc =  fxmlLoader.getController();
        fsc.setData(this);
        fsc.update();
        
        Scene scene = new Scene(root);
        stage.setTitle("修改测评账号");
        stage.setScene(scene);
        stage.show();
    }
    
}
