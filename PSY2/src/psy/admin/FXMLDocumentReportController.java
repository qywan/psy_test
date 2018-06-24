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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLDocumentReportController implements Initializable {

    @FXML
    private ChoiceBox<?> choiceBoxType;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> tableColumnSelected;
    @FXML
    private TableColumn<?, ?> tableColumnId;
    @FXML
    private TableColumn<?, ?> tableColumnName;
    @FXML
    private TableColumn<?, ?> tableColumnSex;
    @FXML
    private TableColumn<?, ?> tableColumnClasses;
    @FXML
    private TableColumn<?, ?> tableColumnGrade;
    @FXML
    private TableColumn<?, ?> tableColumnSchool;
    @FXML
    private TableColumn<?, ?> tableColumnReport;
    @FXML
    private TableColumn<?, ?> tableColumnUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private Insets x1;
    @FXML
    private Button buttonAdd;
    @FXML
    private CheckBox checkBoxAll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonSearchEvent(ActionEvent event) {
    }

    @FXML
    private void handleButtonDeleteEvent(ActionEvent event) {
    }

    @FXML
    private void handleButtonAddEvent(ActionEvent event) {
    }

    @FXML
    private void handleCheckBoxAllEvent(ActionEvent event) {
    }
    
}
