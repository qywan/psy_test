/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.bean.questionnaire;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *多选题
 * @author LYZ
 */
public class CheckQue implements BaseQue{
    private final StringProperty question = new SimpleStringProperty();
    private final ListProperty<String> options = new SimpleListProperty<String>(FXCollections.observableArrayList());
    private final ListProperty<Boolean> answers = new SimpleListProperty<Boolean>(FXCollections.observableArrayList());
    private final ListProperty<Boolean> rightAnswer = new SimpleListProperty<>(FXCollections.observableArrayList());
    
    public ObservableList getRightAnswer() {
        return rightAnswer.get();
    }

    public void setRightAnswer(ObservableList value) {
        rightAnswer.set(value);
    }

    public ListProperty rightAnswerProperty() {
        return rightAnswer;
    }
    

    public String getQuestion() {
        return question.get();
    }

    public void setQuestion(String value) {
        question.set(value);
    }

    public StringProperty questionProperty() {
        return question;
    }

    public ObservableList<String> getOptions() {
        return options.get();
    }
    
    public void setOptions(ObservableList value) {
        options.set(value);
    }

    public ListProperty<String> optionsProperty() {
        return options;
    }
    

    public ObservableList<Boolean> getAnswers() {
        return answers.get();
    }

    public void setAnswers(ObservableList<Boolean> value) {
        answers.set(value);
    }

    public ListProperty answersProperty() {
        return answers;
    }

    public void writeObject(ObjectOutputStream oos) throws IOException{
        oos.writeObject(this.getQuestion());
        oos.writeInt(this.getOptions().size());
        for(int i=0;i<this.getOptions().size();i++){
            oos.writeObject(this.getOptions().get(i));
        }
        for(int i=0;i<this.getAnswers().size();i++){
            oos.writeBoolean(this.getAnswers().get(i));
        }
    }
    
    public CheckQue readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        CheckQue que = new CheckQue();
        que.setQuestion((String)ois.readObject());

        int size = ois.readInt();
        for(int i=0;i<size;i++){
            que.getOptions().add((String)ois.readObject());
        }

        for(int i=0;i<this.getAnswers().size();i++){
            que.getAnswers().add(ois.readBoolean());
        }
        
        return que;
    }

    @Override
    public int getPageType() {
        return 1;
    }
   
}
