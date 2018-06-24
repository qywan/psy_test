/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.bean;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author LYZ
 */
public class Student {
    
    private final StringProperty id;
    private final StringProperty password;
    private final StringProperty name;
    private final StringProperty sex = new SimpleStringProperty();
    private final StringProperty birth = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final ObjectProperty<Classes> classes = new SimpleObjectProperty<Classes>();
    
    public Student(){
        this.id = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
    }
    
    public Student(String id,String pwd,String name){
        this.id = new SimpleStringProperty(id);
        this.password = new SimpleStringProperty(pwd);
        this.name = new SimpleStringProperty(name);
    }
    
    public String getId() {
        return id.get();
    }

    public void setId(String value) {
        id.set(value);
    }

    public StringProperty idProperty() {
        return id;
    }
    
    

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String value) {
        password.set(value);
    }

    public StringProperty passwordProperty() {
        return password;
    }
    

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }
    

    public String getSex() {
        return sex.get();
    }

    public void setSex(String value) {
        sex.set(value);
    }

    public StringProperty sexProperty() {
        return sex;
    }
    

    public String getBirth() {
        return birth.get();
    }

    public void setBirth(String value) {
        birth.set(value);
    }

    public StringProperty birthProperty() {
        return birth;
    }
    

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String value) {
        phone.set(value);
    }

    public StringProperty phoneProperty() {
        return phone;
    }
    

    public Classes getClasses() {
        return classes.get();
    }

    public void setClasses(Classes value) {
        classes.set(value);
    }

    public ObjectProperty classesProperty() {
        return classes;
    }
    
    
}
