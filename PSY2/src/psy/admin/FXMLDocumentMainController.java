/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentMainController implements Initializable {

    @FXML
    private Pane paneTitle;
    @FXML
    private Label labelName;
    @FXML
    private Pane paneContent;
    @FXML
    private Button buttonClass;
    @FXML
    private Insets x1;
    @FXML
    private Button buttonAccount;
    @FXML
    private Button buttonReport;
    @FXML
    private Button buttonConsult;
    
    private AdminMain model;
    @FXML
    private Button buttonQuestionnaire;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(this.model!=null)
            labelName.setText(model.getAdmin().getId());
    }

    @FXML
    private void handleButtonClassEvent(ActionEvent event) {
        this.model.showRightPane(0);
        
    }

    @FXML
    private void handleButtonAccountEvent(ActionEvent event) {
        this.model.showRightPane(1);
    }

    @FXML
    private void handleButtonReportEvent(ActionEvent event) {
        this.model.showRightPane(3);
    }

    @FXML
    private void handleButtonCousultEvent(ActionEvent event) {
        this.model.showRightPane(4);
    }

    
    public void setModel(AdminMain model){
        this.model = model;
    }
    
    public Pane getContentPane(){
        return this.paneContent;
    }

    @FXML
    private void handleButtonQuestionnairetEvent(ActionEvent event) {
         this.model.showRightPane(2);
    }
    
   }

