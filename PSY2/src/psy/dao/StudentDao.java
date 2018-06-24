/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import psy.JDBC;
import psy.bean.Student;

/**
 *
 * @author LYZ
 */
public class StudentDao {
    static String sql = null;
    static JDBC dbl = null;
    static ResultSet res = null;
    static final String TABLE = "student";
    
    /*
    *返回结果：0表示不存在该用户，1表示用户名或者密码错误，2表示登录成功
    */
    public static int login(String id,String password){
        String sql = String.format("SELECT * FROM student WHERE id = '%s';",id);
        dbl = new JDBC(sql);
        try{
            res = dbl.pst.executeQuery();
            
            if(res.next()){
                String matchPwd = res.getString(2);
                if(matchPwd.equals(password)){
                    return 2;
                }
                else{
                    return 1;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    
    public static Student findById(String id){
        String sql = String.format("SELECT * FROM student where id='%s';",id);
        dbl = new JDBC(sql);
        Student stu = null;
        try{
            res = dbl.pst.executeQuery();
            if(res.next()){
                stu = new Student();
                stu.setId(res.getString(1));
                stu.setPassword(res.getString(2));
                stu.setName(res.getString(3));
                stu.setClasses(ClassesDao.findById(res.getString(4)));
                stu.setSex(res.getString(5));
                stu.setBirth(res.getString(6));
                stu.setPhone(res.getString(7));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return stu;
    }
    
    public static boolean save(Student stu){
        String sql;
        if(stu.getClasses()!=null){
            sql = String.format("UPDATE student SET name = '%s',class=%s,sex='%s',birth='%s',phone='%s' WHERE id='%s';",
                stu.getName(),stu.getClasses().getId(),stu.getSex(),stu.getBirth(),stu.getPhone(),stu.getId());
        }else{
            sql = String.format("UPDATE student SET name = '%s',sex='%s',birth='%s',phone='%s' WHERE id='%s';",
                stu.getName(),stu.getSex(),stu.getBirth(),stu.getPhone(),stu.getId());
        }
        try{
            int num = dbl.pst.executeUpdate(sql);
            
            if(num>0){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean add(Student stu){
        String sql;
        if(stu.getClasses()!=null){
            sql = String.format("INSERT INTO student(id,password,name,class, sex,birth,phone) VALUES('%s','%s', '%s', '%s', '%s','%s','%s');",
                stu.getId(),stu.getPassword(), stu.getName(),stu.getClasses().getId(),stu.getSex(),stu.getBirth(),stu.getPhone());
        }
        else{
            sql = String.format("INSERT INTO student(id,password,name, sex,birth,phone) VALUES('%s','%s', '%s', '%s','%s','%s');",
                stu.getId(),stu.getPassword(), stu.getName(),stu.getSex(),stu.getBirth(),stu.getPhone());
        }
        try{
            int num = dbl.pst.executeUpdate(sql);
            //System.out.println(num);
            if(num>0){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public static List<Student> all(){
        List<Student> list = new ArrayList<Student>();
        String sql = String.format("SELECT * FROM student");
        dbl = new JDBC(sql);
        try{
            res = dbl.pst.executeQuery();
            while(res.next()){
                Student stu = new Student();
                stu.setId(res.getString(1));
                stu.setPassword(res.getString(2));
                stu.setName(res.getString(3));
                //stu.setClasses((Classes)res.getObject(4));
                stu.setClasses(ClassesDao.findById(res.getString(4)));
                stu.setSex(res.getString(5));
                stu.setBirth(res.getString(6));
                stu.setPhone(res.getString(7));
                list.add(stu);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public static boolean deleteById(String id){
        String sql = String.format("DELETE FROM %s WHERE id = '%s'",TABLE,id);
        try{
            int num = dbl.pst.executeUpdate(sql);
            if(num>0){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public static List<Student> query(String sql){
         List<Student> list = new ArrayList<Student>();
        dbl = new JDBC(sql);
        try{
            res = dbl.pst.executeQuery();
            while(res.next()){
                Student stu = new Student();
                stu.setId(res.getString(1));
                stu.setPassword(res.getString(2));
                stu.setName(res.getString(3));
                //stu.setClasses((Classes)res.getObject(4));
                stu.setClasses(ClassesDao.findById(res.getString(4)));
                stu.setSex(res.getString(5));
                stu.setBirth(res.getString(6));
                stu.setPhone(res.getString(7));
                list.add(stu);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public static List<Student> search(String str){
        List<Student> list = new ArrayList<Student>();
        //String sql = String.format("SELECT * FROM %s WHERE id LIKE '%%s%' or name LIKE '%%s%' or classes LIKI '%%s%';",TABLE,str,str,str);
        String sql = "SELECT * FROM "+TABLE+ " WHERE id LIKE '%"+str+"%' or name LIKE '%"+str+"%' or class LIKE '%"+str+ "%';";
        
        dbl = new JDBC(sql);
        try{
            res = dbl.pst.executeQuery();
            while(res.next()){
                Student stu = new Student();
                stu.setId(res.getString(1));
                stu.setPassword(res.getString(2));
                stu.setName(res.getString(3));
                //stu.setClasses((Classes)res.getObject(4));
                stu.setClasses(ClassesDao.findById(res.getString(4)));
                stu.setSex(res.getString(5));
                stu.setBirth(res.getString(6));
                stu.setPhone(res.getString(7));
                list.add(stu);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public static List<Student> search(String column, String searchText){
        List<Student> list = new ArrayList<Student>();
        //String sql = String.format("SELECT * FROM %s WHERE id LIKE '%%s%' or name LIKE '%%s%' or classes LIKI '%%s%';",TABLE,str,str,str);
        String sql = "SELECT * FROM "+TABLE+ " WHERE " +column+ " LIKE '%"+searchText+"%';";
        
        dbl = new JDBC(sql);
        try{
            res = dbl.pst.executeQuery();
            while(res.next()){
                Student stu = new Student();
                stu.setId(res.getString(1));
                stu.setPassword(res.getString(2));
                stu.setName(res.getString(3));
                //stu.setClasses((Classes)res.getObject(4));
                stu.setClasses(ClassesDao.findById(res.getString(4)));
                stu.setSex(res.getString(5));
                stu.setBirth(res.getString(6));
                stu.setPhone(res.getString(7));
                list.add(stu);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
