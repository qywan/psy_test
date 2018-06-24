/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.student;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLDocumentPhotoOpenController implements Initializable {

    @FXML
    private Button buttoCat;
    @FXML
    private Button buttonLine;
    @FXML
    private Button buttonIllusion;
    @FXML
    private Button buttonCat2;
    @FXML
    private Button buttonAngle;
    @FXML
    private Button buttonHeilin;
    @FXML
    private Button buttonExperiment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void handleButtonCatEvent(ActionEvent event) {
        PhotoShow ps=new PhotoShow();
        ps.startGif(new Stage(),"mao");
    }

    @FXML
    private void handleButtonLineEvent(ActionEvent event) {
        PhotoShow ps=new PhotoShow();
        ps.startJpg(new Stage(), "Line");

    }

    @FXML
    private void handleButtonIllusionEvent(ActionEvent event) {
         PhotoShow ps=new PhotoShow();
         ps.startJpg(new Stage(), "illusion");
    }

    @FXML
    private void handleButtonCat2Event(ActionEvent event) {
         PhotoShow ps=new PhotoShow();
         ps.startGif(new Stage(),"mao2");
    }

    @FXML
    private void handleButtonAngleEvent(ActionEvent event) {
        PhotoShow ps=new PhotoShow();
        ps.startJpg(new Stage(), "angle");
    }

    @FXML
    private void handleButtonHeilinEvent(ActionEvent event) {
        PhotoShow ps=new PhotoShow();
        ps.startJpg(new Stage(), "heilin");
    }

    @FXML
    private void handleButtonExperimentEvent(ActionEvent event) {
        PhotoShow ps=new PhotoShow();
        ps.startJpg(new Stage(), "experiment");
    }
    
}
