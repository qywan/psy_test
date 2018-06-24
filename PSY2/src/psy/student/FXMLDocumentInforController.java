/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.student;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import psy.Main;
import psy.bean.Student;
import psy.dao.StudentDao;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentInforController implements Initializable {

    @FXML
    private Font x1;
    @FXML
    private Insets x2;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldClass;
    @FXML
    private TextField textFieldBirth;
    @FXML
    private TextField textFieldPhone;
    @FXML
    private ToggleButton toggleButtonEdit;
    @FXML
    private Button buttonSave;
    
    private boolean isEditable = true;
    @FXML
    private GridPane gridPane;
    @FXML
    private ChoiceBox<String> choiceBoxSex;
    
    private Student student;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choiceBoxSex.setItems(FXCollections.observableArrayList("男","女"));
        String id = Main.userId;
        student = StudentDao.findById(id);
        textFieldName.setText(student.getName());
        //textFieldClass.setText(student.getClasses().getId());
        choiceBoxSex.setValue(student.getSex());
        textFieldBirth.setText(student.getBirth());
        textFieldPhone.setText(student.getPhone());
    }    

    @FXML
    private void handleButtonEditEvent(ActionEvent event) {
        isEditable = !isEditable;
        
        textFieldName.setDisable(isEditable);
        textFieldClass.setDisable(isEditable);
        choiceBoxSex.setDisable(isEditable);
        textFieldBirth.setDisable(isEditable);
        textFieldPhone.setDisable(isEditable);
    }

    @FXML
    private void handleButtonSaveEvent(ActionEvent event) throws Exception {
        if(changeModified()){
            Student modifyStudent = new Student();
            modifyStudent.setId(student.getId());
            modifyStudent.setPassword(student.getPassword());
            modifyStudent.setName(textFieldName.getText());
            //modifyStudent.setClasses(textFieldClass.getText());
            modifyStudent.setSex(choiceBoxSex.getValue());
            modifyStudent.setBirth(textFieldBirth.getText());
            modifyStudent.setPhone(textFieldPhone.getText());
            if(StudentDao.save(modifyStudent)){
                student = modifyStudent;
                
                Tips tips = new Tips("修改成功！");
                tips.start(new Stage());
                
            }
        }
    }
    
    private boolean changeModified(){
        if(textFieldName.getText().equals(student.getName())&&
           //textFieldClass.getText().equals(student.getClassNo())&&
           choiceBoxSex.getValue().equals(student.getSex())&&
                textFieldBirth.getText().equals(student.getBirth())&&
                textFieldPhone.getText().equals(student.getPhone())){
            return false;
        }
        return true;
    }
}
