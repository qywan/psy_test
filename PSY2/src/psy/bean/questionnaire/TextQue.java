/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.bean.questionnaire;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *填空题
 * @author LYZ
 */
public class TextQue implements BaseQue{

    private final StringProperty question = new SimpleStringProperty();

    @Override
    public String getQuestion() {
        return question.get();
    }

    @Override
    public void setQuestion(String value) {
        question.set(value);
    }

    public StringProperty questionProperty() {
        return question;
    }
    private final StringProperty answer = new SimpleStringProperty();

    public String getAnswer() {
        return answer.get();
    }

    public void setAnswer(String value) {
        answer.set(value);
    }

    public StringProperty answerProperty() {
        return answer;
    }
    
    @Override
    public void writeObject(ObjectOutputStream oos) throws IOException{
        oos.writeObject(this.getQuestion());
        oos.writeObject(this.getAnswer());
    }
    
    @Override
    public TextQue readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        TextQue que = new TextQue();
        que.setQuestion((String)ois.readObject());
        que.setAnswer((String)ois.readObject());
        return que;
    }

    @Override
    public ObservableList<String> getOptions() {
        return new SimpleListProperty<String>(FXCollections.observableArrayList());
    }

    @Override
    public void setOptions(ObservableList value) {
    }
    
    @Override
    public int getPageType() {
        return 2;
    }
}
