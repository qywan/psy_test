/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.student;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import psy.bean.Admin;
import psy.dao.AdminDao;
import psy.student.ConsultEvent;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLDocumentConsultController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBoxObject;
    @FXML
    private TextField textFiledTitle;
    @FXML
    private TextArea textAreaAsk;
    @FXML
    private Button buttonSubmit;
    @FXML
    private Button buttomCancel;
     ConsultEvent dataContainer;
     private List<Admin> adminList;
     private List<String> adminIdList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         choiceBoxObject.setItems(FXCollections.observableArrayList("男","女"));
         choiceBoxObject.setValue("男");
    }

    @FXML
    private void handleChoiceBoxObjectsEvent(DragEvent event) {
        
    }

    @FXML
    private void handleButtonSubmitEvent(ActionEvent event) {
    }

    @FXML
    private void handleButtonCancelEvent(ActionEvent event) {
    }

     }