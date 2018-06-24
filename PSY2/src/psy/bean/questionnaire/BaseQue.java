/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.bean.questionnaire;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.collections.ObservableList;

/**
 *
 * @author LYZ
 */
public interface BaseQue<T> extends Serializable{
    public int getPageType();
    public void setQuestion(String value);
    public String getQuestion();
    public ObservableList<String> getOptions();
    public void setOptions(ObservableList<String> value);
    
    public void writeObject(ObjectOutputStream oos) throws IOException;
    public BaseQue readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException;
}
//题目基类