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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.util.Callback;
import psy.bean.questionnaire.BaseQue;
import psy.bean.questionnaire.CheckQue;
import psy.bean.questionnaire.RadioQue;
import psy.bean.questionnaire.TextQue;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentShowQueController implements Initializable {

    @FXML
    private Label labelTitle;
    @FXML
    private Label labelQuestion;
    @FXML
    private ListView<String> listViewOptions;
    @FXML
    private Button buttonLeft;
    @FXML
    private Font x1;
    @FXML
    private Button buttonRIght;
    @FXML
    private Label labelPage;

    private ShowQueApp dataModel;
    @FXML
    private TextArea textAreaText;
    @FXML
    private Font x2;

    private boolean modified = false;
    private List<Boolean> selectedList = new ArrayList<Boolean>(); //用来保存checkboxcell的选择
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    public void setDataModel(ShowQueApp dataModel) {
        this.dataModel = dataModel;
    }

    public void saveAnswer() {
        BaseQue que = dataModel.getCurrentQue();
        if (que instanceof RadioQue) {
            RadioQue rque = (RadioQue) que;
            for (int i = 0; i < listViewOptions.getItems().size(); i++) {
                /*
                RadioButtonCell cell = (RadioButtonCell) listViewOptions.getChildrenUnmodifiable().get(i);
                if (cell.radioButton.isSelected()) {
                    rque.setAnswer(i);
                }
                */
                if( listViewOptions.getSelectionModel().isSelected(i) ){
                    rque.setAnswer(i);
                    break;
                }
            }
        } else if (que instanceof CheckQue) {
            CheckQue cque = (CheckQue) que;
            for(int i=0;i<listViewOptions.getItems().size();i++){
                if(cque.getAnswers().size()>i){
                    //cque.getAnswers().set(i,listViewOptions.getSelectionModel().isSelected(i));
                    cque.getAnswers().set(i,selectedList.get(i));
                }else{
                    //cque.getAnswers().add(i,listViewOptions.getSelectionModel().isSelected(i));
                    cque.getAnswers().add(i,selectedList.get(i));
                }
                System.out.println(String.format("SAVE %d as %b", i,cque.getAnswers().get(i)));
                
            }
        } else if(que instanceof TextQue){
            TextQue tque = (TextQue)que;
            tque.setAnswer(this.textAreaText.getText());
            
        }
    }

    public void update() {
        labelTitle.setText(this.dataModel.getTitle());
        labelQuestion.setText(this.dataModel.getCurrentQue().getQuestion());
        updateListView();
    }

    public void updateListView() {
        BaseQue que = dataModel.getCurrentQue();
        listViewOptions.getSelectionModel().clearSelection();
        if (que instanceof RadioQue) {
            textAreaText.setVisible(false);
            listViewOptions.setVisible(true);
            listViewOptions.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            
            RadioQue rque = (RadioQue) que;
            
            ToggleGroup toggleGroup = new ToggleGroup();
            listViewOptions.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> param) {
                    RadioButtonCell cell = new RadioButtonCell(rque.getOptions());
                    cell.radioButton.setToggleGroup(toggleGroup);
                    return cell;
                }
            });
            listViewOptions.setItems(rque.getOptions());
            int selectedIndex = rque.getAnswer();
            listViewOptions.getSelectionModel().select(selectedIndex);
        } else if (que instanceof CheckQue) {
            textAreaText.setVisible(false);
            listViewOptions.setVisible(true);
            listViewOptions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            CheckQue cque = (CheckQue) que;
            selectedList.clear();
            for(int i=0;i<cque.getOptions().size();i++){
                selectedList.add(false);
            }
            
            System.out.println("check:" + cque.getOptions().size());
            listViewOptions.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> param) {
                    CheckBoxCell cell = new CheckBoxCell(cque.getOptions());
                   
                    return cell;
                }

            });
            
            listViewOptions.setItems(cque.getOptions());
            
            for(int i=0;i<cque.getAnswers().size();i++){
                if(cque.getAnswers().get(i)){
                    listViewOptions.getSelectionModel().select(i);
                }
            }
        } else if (que instanceof TextQue) {
            textAreaText.setVisible(true);
            listViewOptions.setVisible(false);
            TextQue tque = (TextQue)que;
            if(tque.getAnswer()!=null){
                textAreaText.setText(tque.getAnswer());
            }else{
                textAreaText.setText("");
            }
            
        }

        String pageStr = String.format("%d/%d", this.dataModel.getIndex() + 1, this.dataModel.getTotal());
        labelPage.setText(pageStr);
    }

    //automatically called when dataModel.index changed
    public void indexChanged(Number oldValue, Number newValue) {
        //TODO
        this.update();
    }

    @FXML
    private void handleButtonLeftEvent(ActionEvent event) {
        
        if(modified){
            System.out.println("save");
            saveAnswer();
        }
        this.dataModel.previousQue();
        if(this.dataModel.getIndex()+1<this.dataModel.getTotal()){
            this.buttonRIght.setText("下一题");
        }
    }

    @FXML
    private void handleButtonRIghtEvent(ActionEvent event) {
        if(modified){
            System.out.println("save");
            saveAnswer();
        }
        this.dataModel.nextQue();
        if(this.dataModel.getIndex()+1==this.dataModel.getTotal()){
            this.buttonRIght.setText("保存");
        }
        else{
            this.buttonRIght.setText("下一题");
        }
    }

    @FXML
    private void handleTextKeyReleased(KeyEvent event) {
        if(modified){
            System.out.println("save");
            saveAnswer();
        }
        modified = true;
    }

    class RadioButtonCell extends ListCell {

        final RadioButton radioButton;
        final ObservableList<String> list;

        public RadioButtonCell(ObservableList<String> list) {
            radioButton = new RadioButton();
            radioButton.setPrefHeight(40);
            this.list = list;
        }

        @Override
        public void updateSelected(boolean selected) {
            super.updateSelected(selected); //To change body of generated methods, choose Tools | Templates.
            radioButton.setSelected(selected);
            modified = true;
        }

        @Override
        protected void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
            System.out.println("update radio item");
            if (!empty) {
                setContentDisplay(ContentDisplay.CENTER);
                setGraphic(radioButton);
                if(list.size()>this.getIndex())
                    radioButton.setText(list.get(this.getIndex()));
                //checkBox.setSelected(dataModel.getSelectedList().get(that.getIndex()));
            } else {
                setGraphic(null);
            }
        }
        
    }

    class CheckBoxCell extends ListCell {

        final CheckBox checkBox;
        final ObservableList<String> list;
        final CheckBoxCell that;
        public CheckBoxCell(ObservableList<String> list) {
            this.that = this;
            checkBox = new CheckBox();
            checkBox.setPrefHeight(40);
            this.list = list;
           
            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedList.set(that.getIndex(),checkBox.isSelected());
                    listViewOptions.getSelectionModel().select(that.getIndex());
                    modified = true;
                }

            });
           
        }

        @Override
        public void updateSelected(boolean selected) {
            super.updateSelected(selected); //To change body of generated methods, choose Tools | Templates.
            //System.out.println(listViewOptions.getSelectionModel().isSelected(that.getIndex())+","+selected);
            if (this.isSelected()) {
                checkBox.setSelected(!checkBox.isSelected());
                selectedList.set(that.getIndex(),checkBox.isSelected());
            }
            modified = true;
            
        }

        @Override
        protected void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
            System.out.println("update check item");
            if (!empty) {
                setContentDisplay(ContentDisplay.CENTER);
                setGraphic(checkBox);
                if(list.size()>this.getIndex())
                    checkBox.setText(list.get(this.getIndex()));
                //checkBox.setSelected(dataModel.getSelectedList().get(that.getIndex()));
            } else {
                setGraphic(null);
            }
        }

        
    }

}
