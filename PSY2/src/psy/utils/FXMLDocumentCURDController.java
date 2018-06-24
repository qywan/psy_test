/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.utils;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentCURDController<T> implements Initializable {

    @FXML
    ChoiceBox<String> choiceBoxType;
    @FXML
    TextField textFieldSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    TableView<T> tableViewMain;
    @FXML
    private Button buttonDelete;
    @FXML
    private Insets x1;
    @FXML
    private Button buttonAdd;
    @FXML
    CheckBox checkBoxSelect;
    
    private CURDBase dataModel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setDataModel(CURDBase dataModel){
        this.dataModel = dataModel;
    }
    
    public void update(){
        dataModel.update();
    }
    
    @FXML
    private void handleButtonSearchEvent(ActionEvent event) {
        dataModel.search();
    }

    @FXML
    private void handleButtonDeleteEvent(ActionEvent event) {
        dataModel.delete();
    }

    @FXML
    private void handleButtonAddEvent(ActionEvent event) {
        dataModel.add();
    }

    @FXML
    private void handleCheckBoxAllEvent(ActionEvent event) {
        dataModel.selectAll();
    }
   
}
