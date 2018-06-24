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
import psy.bean.Classes;
/**
 *
 * @author LYZ
 */
public class ClassesDao {
    static String sql = null;
    static JDBC dbl = null;
    static ResultSet res = null;
    final static String TABLE = "classes"; 
    public static Classes findById(String id){
        String sql = String.format("SELECT * FROM %s where id='%s';",TABLE,id);
        dbl = new JDBC(sql);
        Classes cla = null;
        try{
            res = dbl.pst.executeQuery();
            if(res.next()){
                cla = new Classes();
                cla.setId(res.getString(1));
                cla.setName(res.getString(2));
                cla.setGrade(res.getString(3));
                cla.setSchool(res.getString(4));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return cla;
    }
     public static boolean add(Classes cl){
        String sql;
       // String TempId=null;
      //  if(!cl.getId().equals(TempId)){
         sql = String.format("INSERT INTO classes(id,name,grade) VALUES('%s','%s', '%s');",cl.getId(),cl.getName(), cl.getGrade());
        try{
            int num = dbl.pst.executeUpdate(sql);
            System.out.println("I am num: "+num);
            if(num>0){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
     
    public static List<Classes> all(){
        String sql = String.format("SELECT * FROM %s;",TABLE);
        dbl = new JDBC(sql);
        List<Classes> list = new ArrayList<Classes>();
        try{
            res = dbl.pst.executeQuery();
            while(res.next()){
                Classes cla = new Classes();
                cla.setId(res.getString(1));
                cla.setName(res.getString(2));
                cla.setGrade(res.getString(3));
                cla.setSchool(res.getString(4));
                
                list.add(cla);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
public static boolean save(Classes cl){
    String sql;
    sql = String.format("INSERT INTO classes(id,name,grade) VALUES('%s','%s', '%s');",cl.getId(),cl.getName(), cl.getGrade());
      
        try{
            int num = dbl.pst.executeUpdate(sql);
            System.out.println("I am num="+num);
            if(num>0){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean deleteById(String id) {
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
    
    public static List<Classes> search(String str){
         List<Classes> list = new ArrayList<Classes>();
        //String sql = String.format("SELECT * FROM %s WHERE id LIKE '%%s%' or name LIKE '%%s%' or classes LIKI '%%s%';",TABLE,str,str,str);
        String sql = "SELECT * FROM "+TABLE+ " WHERE id LIKE '%"+str+"%' or name LIKE '%"+str+"%' or grade LIKE '%"+str+ "%';";
        
        dbl = new JDBC(sql);
        try{
            res = dbl.pst.executeQuery();
            while(res.next()){
                Classes cl = new Classes();
                cl.setId(res.getString(1));
                cl.setName(res.getString(2));
                cl.setGrade(res.getString(3));
                list.add(cl);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list; 
    }
    public static List<Classes> search(String column, String searchText){
        List<Classes> list = new ArrayList<Classes>();
        //String sql = String.format("SELECT * FROM %s WHERE id LIKE '%%s%' or name LIKE '%%s%' or classes LIKI '%%s%';",TABLE,str,str,str);
        String sql = "SELECT * FROM "+TABLE+ " WHERE " +column+ " LIKE '%"+searchText+"%';";
        
        dbl = new JDBC(sql);
        try{
            res = dbl.pst.executeQuery();
            while(res.next()){
                Classes cl = new Classes();
                cl.setId(res.getString(1));
                cl.setName(res.getString(2));
                cl.setGrade(res.getString(3));
                //stu.setClasses((Classes)res.getObject(4));
                list.add(cl);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
      public static List<Classes> query(String sql){
         List<Classes> list = new ArrayList<Classes>();
        dbl = new JDBC(sql);
        try{
            res = dbl.pst.executeQuery();
            while(res.next()){
                Classes cl = new Classes();
                cl.setId(res.getString(1));
                cl.setName(res.getString(2));
                cl.setGrade(res.getString(3));
                //stu.setClasses((Classes)res.getObject(4));
                list.add(cl);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    
    }
  


