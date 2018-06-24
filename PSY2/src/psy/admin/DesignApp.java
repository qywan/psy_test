/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import psy.bean.questionnaire.BaseQue;
import psy.bean.questionnaire.Questionnaire;

/**
 *
 * @author LYZ
 */
public class DesignApp extends Application {

    private Stage stage;
    private Questionnaire questionnaire = new Questionnaire();
    public IntegerProperty totalPage = new SimpleIntegerProperty(0);
    public IntegerProperty currentPage = new SimpleIntegerProperty(0);
    public ListProperty<Integer> pages = new SimpleListProperty(FXCollections.observableArrayList());

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        questionnaire.getQuestions().addListener(new ListChangeListener<BaseQue>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends BaseQue> c) {
                c.next();
                if (c.wasAdded()) {//添加
                    System.out.println("添加");
                    currentPage.set(currentPage.get() + 1);
                    pages.add(pages.size() + 1);
                }
                if (c.wasRemoved()) {//删除
                    System.out.println("删除");
                    currentPage.set(currentPage.get() - 1);
                    pages.remove(pages.size() - 1);
                }
                if (c.wasUpdated()) {
                    System.out.println("修改");
                }
                totalPage.set(questionnaire.getQuestions().size());
            }

        });
        BaseQue que = null;
        questionnaire.getQuestions().add(que);
        questionnaire.setTitle("问卷");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentDesign.fxml"));
        Parent root = fxmlLoader.load();
        FXMLDocumentDesignController controller = fxmlLoader.getController();
        controller.setDataModel(this);
        controller.update();

        Scene scene = new Scene(root);
        stage.setTitle("设计问卷");
        stage.setScene(scene);
        stage.show();
    }

    public Stage getStage() {
        return this.stage;
    }

    public boolean selectPage(int pange) {

        if (pange < 1 || pange > this.totalPage.get()) {
            return false;
        }

        this.currentPage.set(pange);
        System.out.println(this.getCurrentPage() + "," + this.getTotalPage());
        return true;
    }

    public void addPage(int page) {//add questionnaire after page
        BaseQue que = null;
        //int index = page-1+1;
        int index = page;
        this.questionnaire.getQuestions().add(index, que);
    }

    public void addPage() {
        BaseQue que = null;
        this.questionnaire.getQuestions().add(que);
    }

    public void savePage(BaseQue que) {
        int index = this.getCurrentPage() - 1;
        this.questionnaire.getQuestions().set(index, que);
    }

    public void removePage() {
        int index = this.getCurrentPage() - 1;
        if (this.getTotalPage() > 1)//保留一页不被删除
        {
            this.questionnaire.getQuestions().remove(index);
        }
    }

    public boolean swapPageUp() {
        if (this.getCurrentPage() <= 1) {
            return false;
        }
        int index = this.getCurrentPage() - 1;
        BaseQue que = this.questionnaire.getQuestions().get(index);
        this.questionnaire.getQuestions().set(index, this.questionnaire.getQuestions().get(index - 1));
        this.questionnaire.getQuestions().set(index - 1, que);
        this.currentPage.set(this.getCurrentPage() - 1);
        return true;
    }

    public boolean swapPageDown() {
        if (this.getCurrentPage() >= this.getTotalPage()) {
            return false;
        }
        int index = this.getCurrentPage() - 1;
        BaseQue que = this.questionnaire.getQuestions().get(index);
        this.questionnaire.getQuestions().set(index, this.questionnaire.getQuestions().get(index + 1));
        this.questionnaire.getQuestions().set(index + 1, que);
        this.currentPage.set(this.getCurrentPage() + 1);
        return true;
    }

    public boolean left() {

        if (this.currentPage.get() <= 1) {
            return false;
        }
        return selectPage(this.currentPage.get() - 1);

    }

    public boolean right() {

        if (this.currentPage.get() == this.totalPage.get()) {
            return false;
        }
        return selectPage(this.currentPage.get() + 1);

    }

    public BaseQue getCurrentQuestion() {
        return this.questionnaire.getQuestions().get(this.currentPage.get() - 1);
    }

    public Questionnaire getQuestionnaire() {
        return this.questionnaire;
    }

    public int getCurrentPage() {
        return this.currentPage.get();
    }

    public int getTotalPage() {
        return this.totalPage.get();
    }

    public void saveQuestionnaire(String filePath) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        this.getQuestionnaire().writeObject(objectOutputStream);
        objectOutputStream.close();
    }

    public Questionnaire readQuestionnaire(String filePath) throws Exception {
        ObjectInputStream objectInputStream;
        objectInputStream = new ObjectInputStream(new FileInputStream(new File(filePath)));
        this.questionnaire.readObject(objectInputStream);
        objectInputStream.close();
        return this.questionnaire;
    }
}
