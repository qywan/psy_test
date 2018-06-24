/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import psy.dao.StudentDao;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentStudentEditController implements Initializable {

    @FXML
    public TextField textFieldId;
    @FXML
    public TextField textFieldName;
    @FXML
    public ChoiceBox<String> choiceBoxSex;
    @FXML
    public TextField textFieldBirth;
    @FXML
    public TextField textFieldPhone;
    @FXML
    public TextField textFieldClasses;
    @FXML
    public TextField textFieldGrade;
    @FXML
    public TextField textFieldSchool;
    @FXML
    public Button buttonSure;

    public StudentEdit dataContainer;

    public void setData(StudentEdit dataContainer) {
        this.dataContainer = dataContainer;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println("init StudentEditController");
    }
    //更新UI显示的数据
    public void update() {

        if (this.dataContainer != null && this.dataContainer.student != null) {
            textFieldId.setText(this.dataContainer.student.getId());
            textFieldName.setText(this.dataContainer.student.getName());
            choiceBoxSex.setItems(FXCollections.observableArrayList("男", "女"));
            choiceBoxSex.setValue(this.dataContainer.student.getSex());
            textFieldBirth.setText(this.dataContainer.student.getBirth());
            textFieldPhone.setText(this.dataContainer.student.getPhone());
            if (this.dataContainer.student.getClasses() != null) {
                textFieldClasses.setText(this.dataContainer.student.getClasses().getName());
                textFieldGrade.setText(this.dataContainer.student.getClasses().getGrade());
                textFieldSchool.setText(this.dataContainer.student.getClasses().getSchool());
            }
        }
    }

    @FXML
    private void handleButtonSureEvent(ActionEvent event) {
        this.dataContainer.student.setName(textFieldName.getText());
        this.dataContainer.student.setSex(choiceBoxSex.getValue());
        this.dataContainer.student.setBirth(textFieldBirth.getText());
        this.dataContainer.student.setPhone(textFieldPhone.getText());

        StudentDao.save(this.dataContainer.student);
        dataContainer.stage.close();
    }

}
