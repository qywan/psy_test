/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.student;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import psy.bean.Qtable;
import psy.dao.QtableDao;

/**
 *
 * @author LYZ
 */
public class TestApp extends Application {
    
    private FXMLDocumentTestController controller;
    private Stage stage;
    private List<Qtable> tableList = new ArrayList<Qtable>();
    private Qtable selectedTable;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentTest.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        this.initData();
        controller.setDataModel(this);
        controller.updateUI();

        Scene scene = new Scene(root);
        stage.setTitle("自主测评");
        stage.setScene(scene);
        stage.show();
    }

    private void initData() {
       try {
           tableList =  QtableDao.all();
       } catch(Exception e) {
         e.printStackTrace();
       }
    }

    public List<Qtable> getTableList() {
        return tableList;
    }

    public void setTableList(List<Qtable> tableList) {
        this.tableList = tableList;
    }

    public Qtable getSelectedTable() {
        return selectedTable;
    }

    public void setSelectedTable(Qtable selectedTable) {
        this.selectedTable = selectedTable;
    }
   
}
