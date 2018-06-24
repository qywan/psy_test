/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author LYZ
 */
public class TipsApp extends Application{
    private static TipsApp singleton;
    private String title;
    private String message;
    private Stage stage;
    private TipsApp(){
    
    }
    public static TipsApp getInstance(){
        if(singleton==null){
            singleton = new TipsApp();
        }
        return singleton;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentTips.fxml"));
        Parent root = fxmlLoader.load();
        FXMLDocumentTipsController fsc =  fxmlLoader.getController();
        fsc.setData(this);
        fsc.update();
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("提示");
        stage.setScene(scene);
        stage.show();
    }
    
    
}
