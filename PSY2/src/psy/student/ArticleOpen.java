/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.student;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author dell
 */
public class ArticleOpen extends Application {
      static Stage stage = null;
    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentArticleEvent.fxml"));
       
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("心理文库");
        stage = primaryStage;

    }
    public void startArticle1(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentArticle1Event.fxml"));
       
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("心理文库");
       
        stage = primaryStage;

    }
     public void startArticle2(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentArticle2Event.fxml"));
       
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("心理文库");
       
        stage = primaryStage;

    }     
         
        
    }

