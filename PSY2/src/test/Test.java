/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import psy.bean.questionnaire.Questionnaire;
import psy.bean.questionnaire.RadioQue;

/**
 *
 * @author LYZ
 */
public class Test {
    public static void main(String args[]){
        Questionnaire que = new Questionnaire();
        que.setTitle("问卷1");
        RadioQue rq = new RadioQue();
        rq.setQuestion("性别");
        rq.setOptions(FXCollections.observableArrayList("男","女"));
        que.getQuestions().add(new RadioQue());
        //System.out.println(que.getTitle());
        System.out.println(new Gson().toJson(que));
    }
}
