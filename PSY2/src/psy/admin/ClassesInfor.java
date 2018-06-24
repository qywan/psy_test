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
//import static psy.admin.StudentInfor.searchColumn;
import psy.bean.Classes;
import psy.dao.ClassesDao;



/**
 *
 * @author dell
 */
public class ClassesInfor {

    public static final String[] searchFilters={"全部","編号","年級"};
    public static final String[] searchColumn = {"", "id", "grade"};
    private ObservableList<Classes> classesList =FXCollections.observableArrayList();
    private ObservableList<Boolean> selectedList= FXCollections.observableArrayList();;

           
        
    private final String url="FXMLDocumentClass.fxml";
    FXMLDocumentClassController controller;
    private Parent node;
    private static ClassesInfor singleton;
    
    
    public static ClassesInfor getInstance(){
        if(singleton==null){
            singleton = new ClassesInfor();
        }
        return singleton;
    }
    
  public  void search(String type, String filter) {
         for (int i = 0; i < searchFilters.length; i++) {
            String str = searchFilters[i];
            if (str.equals(type)) {
                switch (i) {
                    case 0:
                      this.setClassesList(ClassesDao.search(filter));
                        break;
                    case 1:
                    case 2:
                        this.setClassesList(ClassesDao.search(searchColumn[i], filter));
                        break;
                    default:
                       ;
                }
            }
         }
    }

   public void insert(Classes cl) {
        this.classesList.add(cl);
        this.selectedList.add(Boolean.FALSE);
    }
       
    public void edit(int index, Classes classes) {
        if(index<0||index>=this.classesList.size()){
            return;
        }
        this.classesList.set(index,classes);
        
    }
    public void refresh(){
        setClassesList(ClassesDao.all()); //获取数据库中所有的学生信息
        controller.updateUI();
    }

   public  ObservableList<Classes> getClassList() {
      return this.classesList;
    }

   public  ObservableList<Boolean> getSelectedList(){
        return this.selectedList;
    }

   public void delete(int index) {
    System.out.println(index);
        if(index<0||index>=this.classesList.size()){
            return;
        }
        this.classesList.remove(index);
        this.selectedList.remove(index);
   }
   
    public void delete(List<Integer> list){
        for(int i=0;i<list.size();i++){
            delete(list.get(i));
        }
    }
    private void setClassesList(List<Classes> list) {
        this.classesList = FXCollections.observableArrayList(list);
        if(selectedList==null){
            selectedList = FXCollections.observableArrayList();
        }
        selectedList.clear();
        for(int i=0;i<this.classesList.size();i++){
            selectedList.add(Boolean.FALSE);
        }
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
    
    
    
}
