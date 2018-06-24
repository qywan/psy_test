/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import psy.bean.Classes;
import psy.bean.Student;
import psy.dao.ClassesDao;
import psy.dao.StudentDao;
import psy.utils.TipsApp;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentStudentAddController implements Initializable {

    @FXML
    private Insets x1;
    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldBirth;
    @FXML
    private TextField textFieldPhone;
    @FXML
    private ChoiceBox<String> choiceBoxClasses;
    @FXML
    private TextField textFieldGrade;
    @FXML
    private TextField textFieldSchool;
    @FXML
    private Button buttonSure;
    @FXML
    private ChoiceBox<String> choiceBoxSex;
    
    private StudentAdd dataContainer;
    @FXML
    private TextField textFieldClassName;
    
    private List<Classes> classList;
    private List<String> classIdList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choiceBoxSex.setItems(FXCollections.observableArrayList("男","女"));
        choiceBoxSex.setValue("男");
        
        classList =  ClassesDao.all();
        classIdList = new ArrayList<String>();
        for(Classes cl : classList){
            classIdList.add(cl.getId());
        }
        choiceBoxClasses.setItems(FXCollections.observableArrayList(classIdList));
        choiceBoxClasses.setValue(classIdList.get(0));
        updateClassesUI(0);
        
        //添加选择事件
        choiceBoxClasses.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                int index = choiceBoxClasses.getSelectionModel().getSelectedIndex();
                updateClassesUI(index);
            }
           
            
        });
    }    
    
    public void setData(StudentAdd dataContainer){
        this.dataContainer = dataContainer;
    }
    
    private void updateClassesUI(int index){
        Classes cl = classList.get(index);
        textFieldClassName.setText(cl.getName());
        textFieldGrade.setText(cl.getGrade());
        textFieldSchool.setText(cl.getSchool());
    }

    @FXML
    private void handleButtonSureEvent(ActionEvent event) {
        Student stu = new Student();
        stu.setId(textFieldId.getText());
        stu.setName(textFieldName.getText());
        stu.setSex(choiceBoxSex.getValue());
        stu.setPassword(this.dataContainer.DEFAULT_PASSWORD); //默认密码
        stu.setBirth(textFieldBirth.getText());
        stu.setPhone(textFieldPhone.getText());
        int index = choiceBoxClasses.getSelectionModel().getSelectedIndex();
        Classes cl = classList.get(index);
        stu.setClasses(cl);
        //stu.setClasses();
        boolean flag =  StudentDao.add(stu);
       // System.out.println(flag);
        if(flag){
        //dataContainer.controller.updateData();
            dataContainer.controller.updateInsert(stu);
            dataContainer.stage.close();
        }else{
            try{
                TipsApp tipsApp = TipsApp.getInstance();
                tipsApp.setTitle("测试账号");
                tipsApp.setMessage("添加账号失败!");
                tipsApp.start(new Stage());
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleChoiceBoxClassesEvent(DragEvent event) {
        int index = choiceBoxClasses.getSelectionModel().getSelectedIndex();
        updateClassesUI(index);
    }
    
}
