/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.student;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author dell
 */
public class PhotoShow extends Application {
    
    /**
     *
     * @param primaryStage
     * @param str
     */
    @Override
    public void start(Stage primaryStage) {
      // String str;
     // Create Image and ImageView objects  
      /*  javafx.scene.image.Image image = new javafx.scene.image.Image("res/mao.jpg", 0, 100, false, false);  
        
        ImageView imageView = new ImageView();  
        imageView.setImage(image);  
        
        // Display image on screen  
        StackPane root = new StackPane();  
        root.getChildren().add(imageView);  
        Scene scene = new Scene(root, 300, 250);  
        primaryStage.setTitle("Image Read Test");  
        primaryStage.setScene(scene);  
        primaryStage.show();  
*/
    }
      public void startGif(Stage primaryStage,String str) {
     // Create Image and ImageView objects  
        javafx.scene.image.Image image = new javafx.scene.image.Image("res/"+str+".gif", 0, 100, false, false);  
        
        ImageView imageView = new ImageView();  
        imageView.setImage(image);  
        
        // Display image on screen  
        StackPane root = new StackPane();  
        root.getChildren().add(imageView);  
        Scene scene = new Scene(root, 300, 250);  
        primaryStage.setTitle("心理图片");  
        primaryStage.setScene(scene);  
        primaryStage.show();  

    }
       public void startJpg(Stage primaryStage,String str) {
     // Create Image and ImageView objects  
        javafx.scene.image.Image image = new javafx.scene.image.Image("res/"+str+".jpg", 0, 100, false, false);  
        
        ImageView imageView = new ImageView();  
        imageView.setImage(image);  
        
        // Display image on screen  
        StackPane root = new StackPane();  
        root.getChildren().add(imageView);  
        Scene scene = new Scene(root, 300, 250);  
        primaryStage.setTitle("心理图片");  
        primaryStage.setScene(scene);  
        primaryStage.show();  

    } 
    
}
