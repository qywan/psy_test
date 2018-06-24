/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import psy.admin.DesignApp;
import psy.bean.Qtable;
import psy.dao.QtableDao;

/**
 *
 * @author LYZ
 */
public class CURDQtable extends CURDBase<Qtable> {

    private ObservableList<Qtable> list;
    private ObservableList<Boolean> selectedList;
    private TableColumn tableColumnId, tableColumnName, tableColumnUrl, tableColumnTime, tableColumnSelected;
    public final String columnsName[] = {"编号", "问卷名", "路径", "创建时间"};

    @Override
    public void update() {
        try {
            //更新搜索界面
            this.getController().choiceBoxType.setItems(FXCollections.observableArrayList(columnsName[0], columnsName[1]));
            this.getController().choiceBoxType.getSelectionModel().select(0);

            //更新列表界面
            list = FXCollections.observableArrayList(QtableDao.all());
            selectedList = FXCollections.observableArrayList();
            for(int i=0;i<list.size();i++){
                selectedList.add(Boolean.FALSE);
            }
            this.getController().tableViewMain.setItems(list);

            tableColumnSelected = new TableColumn();
            tableColumnId = new TableColumn(columnsName[0]);
            tableColumnName = new TableColumn(columnsName[1]);
            tableColumnUrl = new TableColumn(columnsName[2]);
            tableColumnTime = new TableColumn(columnsName[3]);

            this.getController().tableViewMain.getColumns().add(tableColumnSelected);
            this.getController().tableViewMain.getColumns().add(tableColumnId);
            this.getController().tableViewMain.getColumns().add(tableColumnName);
            this.getController().tableViewMain.getColumns().add(tableColumnUrl);
            this.getController().tableViewMain.getColumns().add(tableColumnTime);

            tableColumnId.setCellValueFactory(new PropertyValueFactory<Qtable, Integer>("id"));
            tableColumnId.setPrefWidth(50);
            tableColumnName.setCellValueFactory(new PropertyValueFactory<Qtable, String>("name"));
            tableColumnUrl.setCellValueFactory(new PropertyValueFactory<Qtable, String>("url"));
            tableColumnTime.setCellValueFactory(new PropertyValueFactory<Qtable, String>("time"));

            tableColumnSelected.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Qtable, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Qtable, Boolean> param) {
                    return new SimpleBooleanProperty(param.getValue() != null);
                }

            });
            tableColumnSelected.setCellFactory(new Callback<TableColumn<Qtable, Boolean>, TableCell<Qtable, Boolean>>() {
                @Override
                public TableCell<Qtable, Boolean> call(TableColumn<Qtable, Boolean> param) {
                    return new CheckBoxCell();
                }

            });

        } catch (Exception ex) {

        }
    }

    @Override
    public void search() {
        String type = (String) this.getController().choiceBoxType.getValue();
        String filter = this.getController().textFieldSearch.getText();
        //to search
    }

    @Override
    public void selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add() {
        try {
            DesignApp da = new DesignApp();
            da.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CURDQtable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void additionalAction1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //内部类，自定义多选按钮
    class CheckBoxCell extends TableCell<Qtable, Boolean> {

        final CheckBox box = new CheckBox();
        final CheckBoxCell that;

        CheckBoxCell() {
            this.that = this;
            box.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedList.set(that.getIndex(), !selectedList.get(that.getIndex()));
                }

            });
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.

            if (!empty) {
                setContentDisplay(ContentDisplay.CENTER);
                setGraphic(box);
                box.setSelected(selectedList.get(that.getIndex()));
            } else {
                setGraphic(null);
            }
        }
    }
}
