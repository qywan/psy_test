/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.bean;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author LYZ
 */
public class Classes {

    private final StringProperty id;
    private final StringProperty grade;
    private final StringProperty school;
    private final StringProperty name;

    public Classes() {
        this.id = new SimpleStringProperty();
        this.grade = new SimpleStringProperty();
        this.school = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
    }

    public Classes(String id) {
        this.id = new SimpleStringProperty(id);
        this.grade = new SimpleStringProperty();
        this.school = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
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

    public String getGrade() {
        return grade.get();
    }

    public void setGrade(String value) {
        grade.set(value);
    }

    public StringProperty gradeProperty() {
        return grade;
    }

    public String getSchool() {
        return school.get();
    }

    public void setSchool(String value) {
        school.set(value);
    }

    public StringProperty schoolProperty() {
        return school;
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

}
