/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import psy.bean.Student;
import psy.dao.StudentDao;

/**
 *
 * @author LYZ
 */
public class StudentInfor{
    
    private static StudentInfor singleton;
    private final String url = "FXMLDocumentStudent.fxml";
    private Parent node;
    private FXMLDocumentStudentController controller;
    
    private ObservableList<Student> studentList;
    private ObservableList<Boolean> selectedList; //记录多选框的状态
    public static final String[] searchFilters = {"所有", "学号", "姓名", "班级"}; //搜索选项
    public static final String[] searchColumn = {"", "id", "name", "class"}; //搜索选项对应的数据表的列名
    
    public static StudentInfor getInstance(){
        if(singleton==null){
            singleton = new StudentInfor();
        }
        return singleton;
    }
    
    public ObservableList<Student> getStudentList(){
        return this.studentList;
    }
    
    public void setStudentList(List<Student> list){
        this.studentList = FXCollections.observableArrayList(list);
        if(selectedList==null){
            selectedList = FXCollections.observableArrayList();
        }
        selectedList.clear();
        for(int i=0;i<this.studentList.size();i++){
            selectedList.add(Boolean.FALSE);
        }
        
    }
    
    public ObservableList<Boolean> getSelectedList(){
        return this.selectedList;
    }
    
    public Parent init(){
        try{
            //node = FXMLLoader.load(getClass().getResource(url));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
            node = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.setDataModel(this);
            
            //this.refresh();
        }catch(Exception e){
            e.printStackTrace();
        }
        return node;
    }
    
    public void refresh(){
        setStudentList(StudentDao.all()); //获取数据库中所有的学生信息
        controller.updateUI();
    }
    
    
    public void insert(Student student) {
        this.studentList.add(student);
        this.selectedList.add(Boolean.FALSE);
    }

    
    public void edit(int index, Student student) {
        if(index<0||index>=this.studentList.size()){
            return;
        }
        this.studentList.set(index,student);
        
    }

    public void delete(int index) {
        System.out.println(index);
        if(index<0||index>=this.studentList.size()){
            return;
        }
        this.studentList.remove(index);
        this.selectedList.remove(index);
        
    }
    
    public void delete(List<Integer> list){
        for(int i=0;i<list.size();i++){
            delete(list.get(i));
        }
    }
    
    public void search(String type,String filter){
        
        for (int i = 0; i < searchFilters.length; i++) {
            String str = searchFilters[i];
            if (str.equals(type)) {
                switch (i) {
                    case 0:
                        this.setStudentList(StudentDao.search(filter));
                        break;
                    case 1:
                    case 2:
                    case 3:
                        this.setStudentList(StudentDao.search(searchColumn[i], filter));
                        break;
                    default:
                       ;
                }
            }
        }
    }
   
    
}
