/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.student;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import psy.admin.FXMLDocumentClassController;
import psy.bean.Classes;
import psy.bean.Qtable;
import psy.bean.questionnaire.Questionnaire;

/**
 * FXML Controller class
 *
 * @author LYZ
 */
public class FXMLDocumentTestController implements Initializable {

    @FXML
    private Color x4;
    @FXML
    private Font x1;
    @FXML
    private Insets x2;
    @FXML
    private Color x3;
    @FXML
    private Button buttonStart;
    @FXML
    private Insets x5;
    @FXML
    private Font x6;

    private TestApp dataModel;
    @FXML
    private ListView<Qtable> listViewUnCompleted;
    @FXML
    private ListView<Qtable> listViewCompleted;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void updateUI() {
        this.initTableUI();
    }

    public void setDataModel(TestApp dataModel) {
        this.dataModel = dataModel;
    }

    private void initTableUI() {
        ObservableList<Qtable> ob1 = FXCollections.observableList(dataModel.getTableList());
        listViewUnCompleted.setItems(ob1);
        listViewUnCompleted.setVisible(true);
        listViewUnCompleted.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        listViewUnCompleted.setCellFactory(new Callback<ListView<Qtable>, ListCell<Qtable>>() {
            @Override
            public ListCell<Qtable> call(ListView<Qtable> param) {
                MyCell cell = new MyCell(ob1);
                return cell;
            }
        });
    }

    @FXML
    private void handleButtonStartEvent(ActionEvent event) throws Exception {

        ShowQueApp app = new ShowQueApp();

        String filePath = "E:\\心理测评\\hello";

        Questionnaire questionnaire = new Questionnaire();

        ObjectInputStream objectInputStream;
        objectInputStream = new ObjectInputStream(new FileInputStream(new File(filePath)));
        questionnaire = questionnaire.readObject(objectInputStream);
        objectInputStream.close();
//        System.out.println("Questionnaire: " + questionnaire.getTitle());
//        System.out.println("Questions number: " + questionnaire.getQuestions().size());
        app.setQuestionnaire(questionnaire);

        app.start(new Stage());

    }

    private class MyCell extends ListCell {

        final Label nameLabel = new Label();
        private ObservableList<Qtable> list;

        MyCell(ObservableList<Qtable> list) {
            this.list = list;
        }

        @Override
        public void updateSelected(boolean selected) {
            super.updateSelected(selected); //To change body of generated methods, choose Tools | Templates.
            if (selected && list != null) {
                dataModel.setSelectedTable(list.get(this.getIndex()));
                System.out.println("selected: "+ list.get(this.getIndex()).getName());
            }
        }

        @Override
        protected void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
            if (!empty) {
                setContentDisplay(ContentDisplay.CENTER);
                setGraphic(nameLabel);
                if (list.size() > this.getIndex()) {
                    nameLabel.setText(list.get(this.getIndex()).getName());
                }
            } else {
                setGraphic(null);
            }
        }
    }
}
