/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.bean.questionnaire;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author LYZ
 */
public class Questionnaire implements Serializable {

    private final ListProperty<BaseQue> questions = new SimpleListProperty<BaseQue>(FXCollections.observableArrayList());
    private final StringProperty title = new SimpleStringProperty();

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String value) {
        title.set(value);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public ObservableList<BaseQue> getQuestions() {
        return questions.get();
    }

    public void setQuestions(ObservableList<BaseQue> value) {
        questions.set(value);
    }

    public ListProperty questionsProperty() {
        return questions;
    }

    public String toJsonString() {
        Gson gson = new Gson();
        String result = gson.toJson(this);
        return result;
    }

    public void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.getTitle());
        int size = this.getQuestions().size();
        oos.writeInt(size);
        for (int i = 0; i < this.getQuestions().size(); i++) {
            BaseQue que = this.getQuestions().get(i);
            if(que instanceof RadioQue){
                oos.writeObject("radio");
            }
            else if(que instanceof CheckQue){
                oos.writeObject("check");
            }
            else if(que instanceof TextQue){
                oos.writeObject("text");
            }
            else{
                oos.writeObject("null");
                continue;
            }
            this.getQuestions().get(i).writeObject(oos);
        }
    }

    public Questionnaire readObject(ObjectInputStream ois) throws Exception {
        Questionnaire ques = new Questionnaire();
        ques.setTitle((String) ois.readObject());
        int size = ois.readInt();
       
        for (int i = 0; i < size; i++) {
            String type = (String) ois.readObject();
            BaseQue que;
            if (type.equals("radio")) {
                que = new RadioQue();
            } else if (type.equals("check")) {
                que = new CheckQue();
            } else if (type.equals("text")) {
                que = new TextQue();
            } else {
                throw new Exception("没有"+type+"的问题模板");
            }
            ques.getQuestions().add(que.readObject(ois));
        }
        return ques;
    }
}
