/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import psy.bean.Student;
import psy.dao.StudentDao;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentStudentController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBoxType;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Boolean> tableColumnSelected;
    @FXML
    private TableColumn tableColumnId;
    @FXML
    private TableColumn tableColumnName;
    @FXML
    private TableColumn tableColumnSex;
    @FXML
    private TableColumn tableColumnBirth;
    @FXML
    private TableColumn tableColumnPhone;
    @FXML
    private TableColumn tableColumnClasses;
    @FXML
    private TableColumn tableColumnGrade;
    @FXML
    private TableColumn tableColumnSchool;
    @FXML
    private TableColumn tableColumnUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private Insets x1;
    @FXML
    private CheckBox checkBoxAll;
    
    private StudentInfor dataModel;
    @FXML
    private Button buttonAdd;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
    
    public void setDataModel(StudentInfor dataModel){
        this.dataModel = dataModel;
    }
    
    public void updateUI(){

        //设置搜索框
        initSearchUI();
        //设置列表界面
        initTableUI();
    }
    
    private void initTableUI(){
        tableView.setEditable(true);
        tableView.setItems(dataModel.getStudentList());
        tableColumnId.setCellValueFactory(new PropertyValueFactory<Student, String>("id")); 
        tableColumnName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        tableColumnSex.setCellValueFactory(new PropertyValueFactory<Student, String>("sex"));
        tableColumnBirth.setCellValueFactory(new PropertyValueFactory<Student, String>("birth"));
        tableColumnPhone.setCellValueFactory(new PropertyValueFactory<Student, String>("phone"));
        tableColumnClasses.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> param) {
                String value = null;
                if (param.getValue().getClasses() != null) {
                    value = param.getValue().getClasses().getName();
                }
                return new ReadOnlyStringWrapper(value);
            }

        });
        tableColumnGrade.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> param) {
                String value = null;
                if (param.getValue().getClasses() != null) {
                    value = param.getValue().getClasses().getGrade();
                }
                return new ReadOnlyStringWrapper(value);
            }

        });
        tableColumnSchool.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Student, String> param) {
                String value = null;
                if (param.getValue().getClasses() != null) {
                    value = param.getValue().getClasses().getSchool();
                }
                return new ReadOnlyStringWrapper(value);

            }

        });

        tableColumnSelected.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student,Boolean>,ObservableValue<Boolean>>(){
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Student, Boolean> param) {
                return new SimpleBooleanProperty(param.getValue()!=null);
            }
            
        });
        tableColumnSelected.setCellFactory(new Callback<TableColumn<Student,Boolean>,TableCell<Student,Boolean>>(){
            @Override
            public TableCell<Student, Boolean> call(TableColumn<Student, Boolean> param) {
                return new CheckBoxCell();
            }
            
        });
        
        
        
        tableColumnUpdate.setSortable(false);
        tableColumnUpdate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Student, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Student, Boolean> param) {
                return new ReadOnlyBooleanWrapper(param.getValue() != null);
            }

        });
        tableColumnUpdate.setCellFactory(new Callback<TableColumn<Student, Boolean>, TableCell<Student, Boolean>>() {
            @Override
            public TableCell<Student, Boolean> call(TableColumn<Student, Boolean> param) {
                return new EditStudentCell(new Stage());
            }
        });
    }
    
   
    /*初始化搜索面板*/
    private void initSearchUI() {

        choiceBoxType.setItems(FXCollections.observableArrayList(StudentInfor.searchFilters));
        choiceBoxType.setValue(StudentInfor.searchFilters[0]);
    }
    
    @FXML
    private void handleButtonSearchEvent(ActionEvent event) { 
        String type = choiceBoxType.getValue();
        String filter = textFieldSearch.getText();
        dataModel.search(type,filter);
        tableView.setItems(dataModel.getStudentList());
        checkBoxAll.setSelected(false);
    }

    @FXML
    private void handleButtonAddEvent(ActionEvent event) throws Exception {
        StudentAdd sa = new StudentAdd();
        sa.setLastController(FXMLDocumentStudentController.this);
        sa.start(new Stage());
    }

    @FXML
    private void handleButtonDeleteEvent(ActionEvent event) {
       for (int i = 0; i < dataModel.getStudentList().size(); i++) {
            if (dataModel.getSelectedList().get(i)) {
                Student stu = dataModel.getStudentList().get(i);
                boolean flag = StudentDao.deleteById(stu.getId());
                if (flag) {
                    dataModel.delete(i);
                    i--;
                }
            }
        }
    }

    @FXML
    private void handleCheckBoxAllEvent(ActionEvent event) {
        for (int i = 0; i < this.dataModel.getSelectedList().size(); i++) {
            this.dataModel.getSelectedList().set(i, checkBoxAll.isSelected());
        }
        tableView.refresh();
    }
   
    
    public void updateInsert(Student stu){
        this.dataModel.insert(stu);
    }

    //内部类，自定义多选按钮
    class CheckBoxCell extends TableCell<Student, Boolean> {

        final CheckBox box = new CheckBox();
        final CheckBoxCell that;

        CheckBoxCell() {
            this.that = this;
            box.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dataModel.getSelectedList().set(that.getIndex(), !dataModel.getSelectedList().get(that.getIndex()));
                }

            });
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.

            if (!empty) {
                setContentDisplay(ContentDisplay.CENTER);
                setGraphic(box);
                box.setSelected(dataModel.getSelectedList().get(that.getIndex()));
            } else {
                setGraphic(null);
            }
        }

    }

    //内部类，自定义继承TableCell的按钮
    class EditStudentCell extends TableCell<Student, Boolean> {

        Stage stage;
        final Button editButton = new Button("修改");

        EditStudentCell(Stage stage) {
            this.stage = stage;
            editButton.setPadding(new Insets(3));
            editButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    showEditPane();
                }
            });
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
            if (!empty) {
                setContentDisplay(ContentDisplay.CENTER);
                setGraphic(editButton);

            } else {
                setGraphic(null);
            }
        }

        private void showEditPane() {
            try {
                //Student stu = tableView.getSelectionModel().getSelectedItem();
                Student stu = tableView.getItems().get(this.getIndex());
                StudentEdit se = new StudentEdit(stu);
                se.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
