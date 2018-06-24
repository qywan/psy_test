/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.student;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLDocumentArticleEventController implements Initializable {

    @FXML
    private Label labelArticle1;
    @FXML
    private Label llabelArticle2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLabelArticle1Event(MouseEvent event) throws Exception {
        ArticleOpen ao= new ArticleOpen();
        ao.startArticle1(new Stage());
    }

    @FXML
    private void handleLbelArticle2Event(MouseEvent event) throws Exception {
        ArticleOpen ao=new ArticleOpen();
        ao.startArticle2(new Stage());
    }
    
}
