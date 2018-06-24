/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import psy.bean.Classes;
import psy.dao.ClassesDao;
import psy.utils.TipsApp;
import psy.dao.ClassesDao;
/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLDocumentClassAddController implements Initializable {

    @FXML
    private Insets x1;
    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldGrade;
    @FXML
    private Button buttonSure;
    private ClassAdd dataContainer;
    public List<Classes> classList;
    /**
     * Initializes the controller class.
     */

   
  public  void setData(ClassAdd dataContainer) {
        this.dataContainer=dataContainer;
    }
    

    @FXML
    private void handleButtonSureEvent(ActionEvent event) {
        Classes cl = new Classes();
        cl.setId(textFieldId.getText());
        cl.setName(textFieldName.getText());
        cl.setGrade(textFieldGrade.getText());
        boolean flag =true;
        flag=  ClassesDao.add(cl);
        classList  = ClassesDao.all();
        System.out.println(classList);
        System.out.println("I am sure event: "+flag);
        if(flag){
        //dataContainer.controller.updateData();
            dataContainer.controller.updateInsert(cl);
            dataContainer.stage.close();
        }else{
            try{
                TipsApp tipsApp = TipsApp.getInstance();
                tipsApp.setTitle("测试班级信息");
                tipsApp.setMessage("添加班级信息失败!");
                tipsApp.start(new Stage());
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    
}
