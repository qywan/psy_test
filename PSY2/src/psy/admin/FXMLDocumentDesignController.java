/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import psy.bean.questionnaire.RadioQue;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import psy.bean.questionnaire.BaseQue;
import psy.bean.questionnaire.CheckQue;
import psy.bean.questionnaire.TextQue;
import psy.common.component.*;
import psy.student.FXMLDocumentShowQueController;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentDesignController implements Initializable {

    @FXML
    private VBox vboxTool;
    @FXML
    private Button buttonSingle;
    @FXML
    private Insets x1;
    @FXML
    private Button buttonMultiple;
    @FXML
    private Button buttonWrite;
    @FXML
    private HBox hboxSettins;
    @FXML
    private Button buttonDeletePage;
    @FXML
    private Insets x2;
    @FXML
    private Button buttonMoveUp;
    @FXML
    private Button buttonMoveDown;
    @FXML
    private Button buttonDeleteItem;
    @FXML
    private Button buttonAddItem;
    @FXML
    private AnchorPane panePage;
    @FXML
    private VBox vboxItems;
    @FXML
    private Button buttonLeft;
    @FXML
    private ChoiceBox<Integer> choiceBoxCurrent;
    @FXML
    private Label labelTotal;
    @FXML
    private Button buttonRight;
    @FXML
    private TextField textFieldTitle;
    @FXML
    private Button buttonSave;
    @FXML
    private ListView<String> listViewOptions; // 题目列表项

    private List<TextField> textFieldList = new ArrayList<TextField>(); // 抛弃

    private DesignApp dataModel;
    private int pageType = -1;  //表示当前题目类型，-1表示没有选择，0表示选择的单选，1表示选择的多选,2表示选择的填空
    @FXML
    private Button butttonAdd;
    @FXML
    private Button buttonSaveQuestionnaire;

    private boolean modified = false;
    private List<Boolean> selectedList = new ArrayList<Boolean>(); //用来保存checkboxcell的选择

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void setDataModel(DesignApp dataModel) {
        this.dataModel = dataModel;
    }

    public void update() {
        //choiceBoxCurrent.setItems();
        choiceBoxCurrent.setItems(dataModel.pages);
        choiceBoxCurrent.getSelectionModel().select(0);
        labelTotal.setText("共" + this.dataModel.getTotalPage() + "页");

        this.dataModel.totalPage.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                labelTotal.setText("共" + newValue.intValue() + "页");
            }
        });

        this.dataModel.currentPage.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Platform.runLater(() -> {
                    choiceBoxCurrent.setValue(dataModel.getCurrentPage());
                });
            }
        });

        this.dataModel.pages.addListener(new ChangeListener<ObservableList<Integer>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<Integer>> observable, ObservableList<Integer> oldValue, ObservableList<Integer> newValue) {
                Platform.runLater(() -> {
                    choiceBoxCurrent.setItems(dataModel.pages);
                    //choiceBoxCurrent.setValue(dataModel.getCurrentPage());
                });

            }

        });

        choiceBoxCurrent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                handleSelectPageEvent(newValue.intValue());
            }

        });

    }

    /**
     * 更新选项列表界面, 暂时用不上
     */
    public void updateListView() {
        /*
        BaseQue que = this.dataModel.getCurrentQuestion();
        if (que instanceof RadioQue) {
            listViewOptions.setVisible(true);
            listViewOptions.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            RadioQue rque = (RadioQue) que;

            ToggleGroup toggleGroup = new ToggleGroup();
            listViewOptions.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> param) {
                    RadioButtonCell cell = new RadioButtonCell("");
                    cell.radioButton.setToggleGroup(toggleGroup);
                    return cell;
                }
            });
            listViewOptions.setItems(rque.getOptions());
            int selectedIndex = rque.getAnswer();
            listViewOptions.getSelectionModel().select(selectedIndex);
        } else if (que instanceof CheckQue) {

        }
        */
    }

    private void handleSelectPageEvent(int page) {
        dataModel.selectPage(page);
        resetUI();
        BaseQue que = this.dataModel.getCurrentQuestion();
        setRaidoItemsUI(que);
    }

    private TextField addItem() {
        TextField tf = new TextField("");
        tf.setPromptText("输入答案选项");
        tf.setPrefHeight(40);
        tf.setPadding(new Insets(5));
        textFieldList.add(tf);
        this.vboxItems.getChildren().add(tf);
        return tf;
    }

    private boolean removeItem() {
        if (this.textFieldList.size() > 0) {
            this.vboxItems.getChildren().remove(this.vboxItems.getChildren().size() - 1);
            return true;
        }
        return false;
    }

    @FXML
    private void handleButtonSingleEvent(ActionEvent event) {
        this.pageType = 0;
        addItemsUI(2, 0);
    }

    @FXML
    private void handleButtonMultipleEvent(ActionEvent event) {
        this.pageType = 1;
        addItemsUI(4, 1);
    }

    @FXML
    private void handleButtonWriteEvent(ActionEvent event) {
        this.pageType = 2;
        addItemsUI(0, 2);
    }

    @FXML
    private void handleButtonDeletePage(ActionEvent event) {
        this.dataModel.removePage();
    }

    @FXML
    private void handleButtonMoveUpEvent(ActionEvent event) {
        this.dataModel.swapPageUp();
        BaseQue que = this.dataModel.getCurrentQuestion();
        setRaidoItemsUI(que);
    }

    @FXML
    private void handleButtonMoveDownEvent(ActionEvent event) {
        this.dataModel.swapPageDown();
        BaseQue que = this.dataModel.getCurrentQuestion();
        setRaidoItemsUI(que);
    }

    @FXML
    private void handleButtonDeleteItemEvent(ActionEvent event) {
        this.removeItem();
    }

    @FXML
    private void handleButtonAddItemEvent(ActionEvent event) {
        this.addItem();
    }

    @FXML
    private void handleButtonLeftHandle(ActionEvent event) {
        this.dataModel.left();
        resetUI();
        BaseQue que = this.dataModel.getCurrentQuestion();
        setRaidoItemsUI(que);
    }

    @FXML
    private void handleButtonRightEvent(ActionEvent event) {
        boolean isEnd = this.dataModel.right();
        resetUI();
        if (isEnd) {//不是最后一页
            BaseQue que = this.dataModel.getCurrentQuestion();
            setRaidoItemsUI(que);
        } else {
            this.dataModel.addPage();
        }
    }

    @FXML
    private void handleButtonSaveEvent(ActionEvent event) {
        if (!check()) {
            System.out.println("没有选项，添加失败！");
            return;
        }
        String title = textFieldTitle.getText().trim();
        ObservableList<String> options = listViewOptions.getItems();
        switch (pageType) {
            case 0:
                RadioQue rque = new RadioQue();
                rque.setQuestion(title);
                rque.setOptions(options);
                int selectedIndex = listViewOptions.getSelectionModel().getSelectedIndex();
                rque.setRightAnswer(selectedIndex);
                this.dataModel.savePage(rque);
                break;
            case 1:
                CheckQue cque = new CheckQue();
                cque.setQuestion(title);
                cque.setOptions(options);
                ObservableList<Integer> selectedIndices = listViewOptions.getSelectionModel().getSelectedIndices();
                List<Boolean> answerList = new ArrayList<Boolean>();
                for(int i=0; i<options.size(); i++) {
                    answerList.add(false);
                }
                for(int j=0; j<selectedIndices.size(); j++){
                    answerList.set(selectedIndices.get(j), true);
                }
                ObservableList<Boolean> answers = FXCollections.observableArrayList(answerList);
                cque.setRightAnswer(answers);
                this.dataModel.savePage(cque);
                break;
            case 2:
                TextQue tque = new TextQue();
                tque.setQuestion(title);
                this.dataModel.savePage(tque);
                break;
        }

    }
    // no use
    private void saveQuestionOptions() {
        ObservableList options = FXCollections.observableArrayList();
        switch (pageType) {
            case 0:
                RadioQue rque = new RadioQue();
                for (int i = 0; i < listViewOptions.getItems().size(); i++) {
                    // TODO replace textFieldList
                    String text = textFieldList.get(i).getText().trim();
                    if (!text.equals("")) {
                        options.add(text);
                    }
                }

                rque.setOptions(options);

                this.dataModel.savePage(rque);
                break;
            case 1:
                CheckQue cque = new CheckQue();
                for (int i = 0; i < textFieldList.size(); i++) {
                    String text = textFieldList.get(i).getText().trim();
                    if (!text.equals("")) {
                        options.add(text);
                    }
                }
                cque.setOptions(options);
                this.dataModel.savePage(cque);
                break;
            case 2:
                TextQue tque = new TextQue();
                this.dataModel.savePage(tque);
                break;
        }
    }

    @FXML
    private void handleButtonAddEvent(ActionEvent event) {
        resetUI();
        if (this.dataModel.getTotalPage() > this.dataModel.getCurrentPage()) {
            this.dataModel.addPage();
        } else {
            this.dataModel.addPage(this.dataModel.getCurrentPage());
        }

    }
    
    private void addItemsUI(int size, int pageTye) {
        List<String> options = new ArrayList<String>();
        for (int i=0; i<size; i++ ){
            options.add("");
        }
        this.addItemsUI(options, pageType, null);
    }
    
    private void addItemsUI(List<String> options, int pageType, int selected) {
        int size = options.size();
        List<Boolean> selectedList = new ArrayList<Boolean>();
        for(int i=0; i<size; i++) {
            if (i == selected) {
                selectedList.add(true);
            } else {
                selectedList.add(false);
            }
        }
        this.addItemsUI(options, pageType, selectedList);
    }

    private void addItemsUI(List<String> options, int pageType, List<Boolean> selectedList) {
        if (options == null) {
            options = new ArrayList<String>();
        }
        this.pageType = pageType;
        ObservableList<String> items = FXCollections.observableList(options);
        listViewOptions.getSelectionModel().clearSelection();
        switch (pageType) {
            case 0:
                listViewOptions.setVisible(true);
                listViewOptions.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                ToggleGroup toggleGroup = new ToggleGroup();
                listViewOptions.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> param) {
                        RadioButtonCell cell = new RadioButtonCell(items);
                        cell.radioButton.setToggleGroup(toggleGroup);
                        return cell;
                    }
                });
                listViewOptions.setItems(items);
                
                if (selectedList !=null ) {
                    for (int i = 0; i < selectedList.size(); i++ ){
                        if (selectedList.get(i)) {
                            listViewOptions.getSelectionModel().select(i);
                        }
                    }
                }
                break;
            case 1: 
                listViewOptions.setVisible(true);
                listViewOptions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                listViewOptions.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> param) {
                        CheckBoxCell cell = new CheckBoxCell(items);
                        return cell;
                    }
                });
                listViewOptions.setItems(items);
                if (selectedList !=null ) {
                    for (int i = 0; i < selectedList.size(); i++ ){
                        if (selectedList.get(i)) {
                            listViewOptions.getSelectionModel().select(i);
                            System.out.println("check select "+ i);
                        }
                    }
                }
                
                break;
            case 2:
                listViewOptions.setVisible(false);
                break;
        }
    }

    private void setRaidoItemsUI(BaseQue que) {
        if (que == null) {
            return;
        }
        this.setTitle(que.getQuestion());
        if (que instanceof RadioQue) {
            RadioQue rque = (RadioQue)que;
            this.addItemsUI(que.getOptions(), que.getPageType(), rque.getRightAnswer());
        } else if(que instanceof CheckQue) {
            CheckQue cque = (CheckQue)que;
            this.addItemsUI(que.getOptions(), que.getPageType(), cque.getRightAnswer());
        }
        
    }

    private void resetUI() {
        this.textFieldTitle.setText("");
        this.listViewOptions.setItems(null);
    }

    private void resetTitle() {
        textFieldTitle.setText("");
    }

    private void setTitle(String title) {
        textFieldTitle.setText(title);
    }

    private boolean check() {//check if should be save
        return true;
    }

    @FXML
    private void handleButtonSaveQuestionnaireEvent(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择路径");
        File file = fileChooser.showSaveDialog(this.dataModel.getStage());
        if (file != null) {
            try {
                dataModel.saveQuestionnaire(file.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        /*
        //测试是否成功地序列化
        Questionnaire questionnaire;
        try {
            questionnaire = dataModel.readQuestionnaire(file.getAbsolutePath());
            System.out.println("Questionnaire: " + questionnaire.getTitle());
            System.out.println("Questions number: " + questionnaire.getQuestions().size());
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentDesignController.class.getName()).log(Level.SEVERE, null, ex);
        }
         */

    }

    class RadioButtonCell extends ListCell {
        
        final HBox hbox;
        final RadioButton radioButton;
        final TextField textField;
        private ObservableList<String> items;
        
        public RadioButtonCell(ObservableList<String> items) {
            this.items = items;
            radioButton = new RadioButton();
            radioButton.setPrefHeight(40);
            textField = new TextField();
            textField.setPrefHeight(32);
            textField.setPrefWidth(320);
            // 输入框内容变化时修改listview数据模型
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                int index = this.getIndex();
                if (index > -1 && index < items.size()) {
                   items.set(index, newValue);
                }
            });
            // 选择按钮被选择时修改listview被选择项
            radioButton.setOnAction((event) -> {
                int index = this.getIndex();
                listViewOptions.getSelectionModel().select(index);
            });

            hbox = new HBox();
            hbox.getChildren().add(radioButton);
            hbox.getChildren().add(textField);
            hbox.setPrefHeight(32);
        }

        @Override
        public void updateSelected(boolean selected) {
            super.updateSelected(selected); //To change body of generated methods, choose Tools | Templates.
            radioButton.setSelected(selected);
        }

        @Override
        protected void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
            if (!empty) {
                int index = this.getIndex();
                if (index > -1 && index < this.items.size()) {
                    this.textField.setText(this.items.get(this.getIndex()));
                }
                setContentDisplay(ContentDisplay.CENTER);
                setGraphic(hbox);
            } else {
                setGraphic(null);
            }
        }

    }

    class CheckBoxCell extends ListCell {
        final HBox hbox;
        final CheckBox checkBox;
        final TextField textField;
        private ObservableList<String> items;

        public CheckBoxCell(ObservableList<String> items) {
            checkBox = new CheckBox();
            checkBox.setPrefHeight(40);
            textField = new TextField();
            textField.setPrefHeight(32);
            textField.setPrefWidth(320);
            hbox = new HBox();
            hbox.getChildren().add(checkBox);
            hbox.getChildren().add(textField);
            hbox.setPrefHeight(32);
            
            this.items = items;
            
            // 输入框内容变化时修改listview数据模型
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                int index = this.getIndex();
                if (index > -1 && index < items.size()) {
                   items.set(index, newValue);
                }
            });
            // 选择按钮被选择时修改listview被选择项
            checkBox.setOnAction((event) -> {
                listViewOptions.getSelectionModel().select(this.getIndex());
            });
        }

        @Override
        public void updateSelected(boolean selected) {
            super.updateSelected(selected); //To change body of generated methods, choose Tools | Templates.
            checkBox.setSelected(selected);
            modified = true;

        }

        @Override
        protected void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
            if (!empty) {
                int index = this.getIndex();
                if (index > -1 && index < this.items.size()) {
                    this.textField.setText(this.items.get(this.getIndex()));
                }
                setContentDisplay(ContentDisplay.CENTER);
                setGraphic(hbox);
            } else {
                setGraphic(null);
            }
        }

    }
}
