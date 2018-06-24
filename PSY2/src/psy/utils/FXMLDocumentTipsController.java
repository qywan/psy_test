/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.utils;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentTipsController implements Initializable {

    @FXML
    private Label labelTitle;
    @FXML
    private Label labelMessage;
    private TipsApp dataContainer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(TipsApp dataContainer){
        this.dataContainer = dataContainer;
    }
    
    public void update(){
        this.labelTitle.setText(dataContainer.getTitle());
        this.labelMessage.setText(dataContainer.getMessage());
    }
}
