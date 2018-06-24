/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy;

import psy.student.ShowPersonalInf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import psy.student.ConsultEvent;
import psy.student.InteractAction;
import psy.student.TestApp;
import psy.student.ProscienceEvent;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentMainController implements Initializable {

    @FXML
    private Color x1;
    @FXML
    private Button buttonInteract;//心理互动
    @FXML
    private Font x2;
    @FXML
    private Insets x3;
    @FXML
    private Button buttonPopscience;//心理科普
    @FXML
    private Button buttonTrain;//心理训练
    @FXML
    private Button buttonInfo;//个人信息
    @FXML
    private Button buttonTest;//自主测评
    @FXML
    private Button buttonReservation;//咨询预约
    @FXML
    private Label labelStatus;
    @FXML
    private Label labelName;
    @FXML
    private Font x4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(Main.userId!=null){
            labelName.setText(Main.userId);
        }
        
    }    

    @FXML
    private void handleButtonInteractAction(ActionEvent event) throws Exception {
        try{InteractAction InA= new InteractAction();
        InA.start(new Stage());
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    
    @FXML
    private void handleButtonPopscienceEvent(ActionEvent event) throws Exception {  
        try{
        ProscienceEvent pe=new ProscienceEvent();
        pe.start(new Stage());
        }catch(Exception e){
           e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonTrainAction(ActionEvent event) {
        
        
    }

    @FXML
    private void handleButtonInfoAction(ActionEvent event) {
        try{
            ShowPersonalInf spi = new ShowPersonalInf();
            spi.start(new Stage());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleButtonTestAction(ActionEvent event) throws Exception{
        TestApp app = new TestApp();
        app.start(new Stage());
    }

    @FXML
    private void handleButtonConsultEvent(ActionEvent event) throws Exception {
        ConsultEvent ce=new ConsultEvent();
        ce.start(new Stage());
        
    }
    
    private void updateLoginState(){
        
    }
    
    private void updateFooterState(){
        
    }

}
