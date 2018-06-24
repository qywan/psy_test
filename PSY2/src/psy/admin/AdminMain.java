/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.admin;

import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import psy.bean.Admin;
import psy.utils.CURDQtable;

/**
 *
 * @author LYZ
 */
public class AdminMain extends Application {

    /*
    static Admin admin;
    public static Stage stage;
    public AdminMain(Admin admin){
        this.admin = admin;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentMain.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("管理界面");
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
    private static AdminMain singleton;
    private Stage stage;
    private Admin admin;
    private HashMap<String, Parent> paneMap = new HashMap<String, Parent>(); //存放最近打开的菜单所对应的面板
    private FXMLDocumentMainController controller;
    public final String[] menusName = {"班级信息","测评账号","自定义量表导入","测评报告","咨询预约"};
    private StudentInfor studentInfor;
    private ClassesInfor classesInfor;
    private AdminMain(){
        studentInfor = StudentInfor.getInstance();
        classesInfor = ClassesInfor.getInstance();
    }
    private AdminMain(Admin admin) {
        this.admin = admin;
        studentInfor = StudentInfor.getInstance();
        classesInfor=ClassesInfor.getInstance();
    }

    public static AdminMain getSingleton(Admin admin) {
        if (singleton == null) {
            singleton = new AdminMain(admin);
        }
        return singleton;
    }
    
    public static AdminMain getSingleton() {
        if (singleton == null) {
            singleton = new AdminMain();
        }
        return singleton;
    }

    public Admin getAdmin() {
        return this.admin;
    }
    
    public void setAdmin(Admin admin){
        this.admin = admin;
    }
    
    public Stage getStage(){
        return this.stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //TODO, this should judge if this.admin is null?
        
        this.initAllRightPane();
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocumentMain.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setModel(this);

        Scene scene = new Scene(root);
        stage.setTitle("测评管理");
        stage.setScene(scene);
        stage.show();
    }
    
    private void initAllRightPane(){
        try{
            paneMap.put(menusName[0], classesInfor.init());
            paneMap.put(menusName[1], studentInfor.init()); 
            paneMap.put(menusName[2], new CURDQtable().initRoot());
            paneMap.put(menusName[3], FXMLLoader.load(getClass().getResource("FXMLDocumentReport.fxml")));
            paneMap.put(menusName[4], FXMLLoader.load(getClass().getResource("FXMLDocumentAdminConsult.fxml")));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showRightPane(int index) {
        
        if(this.controller!=null&&index>=0&&index<menusName.length){
            this.controller.getContentPane().getChildren().clear();
            this.controller.getContentPane().getChildren().add(paneMap.get(menusName[index]));
            
        }
        System.out.println(index);
        switch(index){
            case 0:classesInfor.refresh();
            case 1:studentInfor.refresh();
            case 2:break;
            case 3:break;
            case 4:break;
            default:;
        }
    }
    
}
