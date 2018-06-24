/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import psy.bean.Classes;
import psy.dao.ClassesDao;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXMLDocumentClassController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBoxType;
    @FXML
    private TextField textFieldSearch;
    @FXML
    private TableView<Classes> tableView;
    @FXML
    private TableColumn<Classes, Boolean> tableColumnSelected;
    @FXML
    private TableColumn tableColumnId;
    @FXML
    private TableColumn  tableColumnName;
    @FXML
    private TableColumn  tableColumnGrade;

    @FXML
    private CheckBox checkBoxAll;
    @FXML
    private TableColumn  tableColumnUpdate;
    private ClassesInfor dataModel; 
    @FXML
    private Button buttonSearch;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonDelete;
   
   
    /**
     * 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> list=Arrays.asList("全部","编号","年级");
        choiceBoxType.setItems(FXCollections.observableArrayList(list));
        choiceBoxType.setValue("全部");
    }    
    //检查填写的是哪种类型
    @FXML
    private void handleButtonSearchEvent(ActionEvent event) {
        String type=choiceBoxType.getValue();
        String filter=textFieldSearch.getText();
        dataModel.search(type,filter);
        checkBoxAll.setSelected(false);
    }

    @FXML
    private void handleButtonAddEvent(ActionEvent event) throws Exception {
        ClassAdd cd = new ClassAdd();
        cd.setLastController(FXMLDocumentClassController.this);
        cd.start(new Stage());
    }

    public void setDataModel(ClassesInfor dataModel) {
       this.dataModel=dataModel;
    }

    public void updateUI() {
       initSearchUI();
       initTableUI();
    
    }
//搜索框初始化是全部
    private void initSearchUI() {
      choiceBoxType.setItems(FXCollections.observableArrayList(ClassesInfor.searchFilters));
      choiceBoxType.setValue(ClassesInfor.searchFilters[0]);
    }
    //插入更新
    public void updateInsert(Classes cla){
        this.dataModel.insert(cla);
    }
    private void initTableUI() {
     tableView.setEditable(true);
     tableView.setItems(dataModel.getClassList());
     tableColumnId.setCellValueFactory(new PropertyValueFactory<Classes,String>("id"));
     tableColumnName.setCellValueFactory(new PropertyValueFactory<Classes,String>("name"));
     tableColumnGrade.setCellValueFactory(new PropertyValueFactory<Classes,String>("grade"));
     tableColumnSelected.setCellValueFactory(new Callback<CellDataFeatures<Classes,Boolean>,ObservableValue<Boolean>>(){
         @Override
         public ObservableValue<Boolean> call(CellDataFeatures<Classes, Boolean> param) {
          return new SimpleBooleanProperty(param.getValue()!=null);  
         }
         });
     tableColumnSelected.setCellFactory(new Callback<TableColumn<Classes,Boolean>,TableCell<Classes,Boolean>>(){
            @Override
            public TableCell<Classes, Boolean> call(TableColumn<Classes, Boolean> param) {
                return new CheckBoxCell();
            }
    });
     tableColumnUpdate.setSortable(false); 
    }        

    @FXML
    private void handleButtonDeleteEvent(ActionEvent event) {
        for (int i = 0; i < dataModel.getClassList().size(); i++) {
            if (dataModel.getSelectedList().get(i)) {
                Classes cla = dataModel.getClassList().get(i);
                boolean flag = ClassesDao.deleteById(cla.getId());
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

 //内部类，自定义多选按钮
   class CheckBoxCell extends TableCell<Classes, Boolean> {

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
                System.out.println(that.getIndex());
                box.setSelected(dataModel.getSelectedList().get(that.getIndex()));
            } else {
                setGraphic(null);
            }
        }

   }
   
    }
    

