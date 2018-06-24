/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.student;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import psy.admin.AdminMain;
import psy.bean.questionnaire.BaseQue;
import psy.bean.questionnaire.Questionnaire;

/**
 *
 * @author LYZ
 */
public class ShowQueApp extends Application{

    private FXMLDocumentShowQueController controller;
    private Stage stage;
    private Questionnaire questionnaire;
    private IntegerProperty index = new SimpleIntegerProperty(0); //标识当前的问题位置
    
    public ShowQueApp(){
        questionnaire = new Questionnaire();
        
    }
    
    public void setQuestionnaire(Questionnaire questionnaire){
        this.questionnaire = questionnaire;
    }
    
    public String getTitle(){
        return this.questionnaire.getTitle();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        //for test
        
        if(questionnaire==null){
            throw new Exception("必须调用setQuestionnaire()方法");
        }
        
        this.stage = stage;
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.initOwner(AdminMain.getSingleton().getStage());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentShowQue.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setDataModel(this);
        controller.update();
        
        this.questionnaire.getQuestions().addListener(new ListChangeListener<BaseQue>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends BaseQue> c){
                
            }
            
        });
        
        this.index.addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                controller.indexChanged(oldValue, newValue);
            }
        });
        
        Scene scene = new Scene(root);
        stage.setTitle("问卷作答");
        stage.setScene(scene);
        stage.show();
    }
    
    public boolean previousQue(){
        if(index.get()>0){
            index.set(index.get()-1);
            return true;
        }
        return false;
    }
    
    public boolean nextQue(){
        if(index.get()<questionnaire.getQuestions().size()-1){
            index.set(index.get()+1);
            return true;
        }
        return false;
    }
    
    public BaseQue getCurrentQue(){
        return this.questionnaire.getQuestions().get(this.index.get());
    }
    
    public int getIndex(){
        return this.index.get();
    }
    
    public int getTotal(){
        return this.questionnaire.getQuestions().size();
    }
}
