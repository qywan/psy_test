/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.student;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javax.swing.ImageIcon;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLDocumentInteractController implements Initializable {

    @FXML
    private Button buttonFilm;
    @FXML
    private Button buttonPhoto;
    @FXML
    private Button buttonMusic;
    @FXML
    private Button buttonArticle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonFilmEvent(ActionEvent event) {
        
    }

    @FXML
    private void handleButtonPhotoEvent(ActionEvent event) throws Exception {
      PhotoOpen fo=new PhotoOpen();
      fo.start(new Stage());
    }

    @FXML
    private void handleButtonMusicEvent(ActionEvent event) {
    }

    @FXML
    private void handelButtonArticleEvent(ActionEvent event) throws Exception {
        ArticleOpen ao=new ArticleOpen();
        ao.start(new Stage());
        
    }

    
    
}
