/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.bean.questionnaire;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *单选题
 * @author LYZ
 */
public class RadioQue implements BaseQue {
   
    private final StringProperty question = new SimpleStringProperty();
    private final ListProperty<String> options = new SimpleListProperty<String>(FXCollections.observableArrayList());
    private final IntegerProperty answer = new SimpleIntegerProperty();
    private final IntegerProperty rightAnswer = new SimpleIntegerProperty();

    public int getRightAnswer() {
        return rightAnswer.get();
    }

    public void setRightAnswer(int value) {
        rightAnswer.set(value);
    }

    public IntegerProperty rightAnswerProperty() {
        return rightAnswer;
    }
    

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

    @Override
    public ObservableList<String> getOptions() {
        return options.get();
    }

    @Override
    public void setOptions(ObservableList value) {
        options.set(value);
    }

    public ListProperty<String> optionsProperty() {
        return options;
    }

    public int getAnswer() {
        return answer.get();
    }

    public void setAnswer(int value) {
        answer.set(value);
    }

    public IntegerProperty answerProperty() {
        return answer;
    }
    
    public void writeObject(ObjectOutputStream oos) throws IOException{
        oos.writeObject(this.getQuestion());
        oos.writeInt(this.getOptions().size());
        for(int i=0;i<this.getOptions().size();i++){
            oos.writeObject(this.getOptions().get(i));
        }
        oos.writeInt(this.getAnswer());
    }
    
    public RadioQue readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        RadioQue que = new RadioQue();
        String question = (String)ois.readObject();
        que.setQuestion(question);
        
        int size = ois.readInt();
        
        for(int i=0;i<size;i++){
            que.getOptions().add((String)ois.readObject());
        }
        que.setAnswer(ois.readInt());
        
        return que;
    }
    
    @Override
    public int getPageType() {
        return 0;
    }
}
