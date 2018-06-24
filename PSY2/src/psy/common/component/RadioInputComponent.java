/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.common.component;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author lyz
 */
public class RadioInputComponent extends ListCell {

    @FXML
    private TextField contentTextField;
    @FXML
    private RadioButton selectRadio;
    
    final ObservableList<String> list;
   
    public RadioInputComponent(ObservableList<String> list) {
        this.list = list;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentRadioInput.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    public String getContent() {
        return contentTextField.getText();
    }

    public void setContent(String value) {
        contentTextField.setText(value);
    }
    
    public void setSelected(boolean value) {
        selectRadio.setSelected(value);
    }
    
    public void setPromptText(String value) {
        contentTextField.setPromptText(value);
    }
    
    public void setToggleGroup(ToggleGroup toggleGroup){
        selectRadio.setToggleGroup(toggleGroup);
    };

    @FXML
    protected void handleRadioSelectEvent(ActionEvent event) {
        System.out.println("content: "+ this.getText()+" is selected");
    }

     @Override
        public void updateSelected(boolean selected) {
            super.updateSelected(selected); //To change body of generated methods, choose Tools | Templates.
            this.setSelected(selected);
        }

        @Override
        protected void updateItem(Object item, boolean empty) {
            super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
            if (!empty) {
                setContentDisplay(ContentDisplay.CENTER);
                setGraphic(this);
                if(list.size()>this.getIndex())
                    selectRadio.setText(list.get(this.getIndex() + 1));
            } else {
                setGraphic(null);
            }
        }
    
    
}
