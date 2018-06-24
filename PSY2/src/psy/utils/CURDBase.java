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
public abstract class CURDBase<T> extends Application{
    private Stage stage;
    private FXMLDocumentCURDController<T> controller;
    private Parent root;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        
        if(this.root==null){
            initRoot();
        }
        Scene scene = new Scene(root);
        //stage.setTitle("提示");
        stage.setScene(scene);
        stage.show();
    }
    
    public Parent initRoot() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentCURD.fxml"));
        this.root = fxmlLoader.load();
        controller =  fxmlLoader.getController();
        controller.setDataModel(this);
        controller.update();

        return this.root;
    }
    
    public Parent getRoot(){
        return this.root;
    }
    
    public Stage getStage(){
        return this.stage;
    }
    public FXMLDocumentCURDController getController(){
        return this.controller;
    }
    
    public abstract void update();
    public abstract void search();
    public abstract void selectAll();
    public abstract void delete();
    public abstract void add();
    public abstract void edit();
    public abstract void additionalAction1();

}
