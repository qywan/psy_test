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
import psy.bean.Admin;

/**
 *
 * @author LYZ
 */
public class AdminDao {
    static String sql = null;
    static JDBC dbl = null;
    static ResultSet res = null;
    static final String TABLE = "admin";
    
    /*
    *返回结果：0表示不存在该用户，1表示用户名或者密码错误，2表示登录成功
    */
    public static int login(String id,String password){
        String sql = String.format("SELECT * FROM %s WHERE id = '%s';",TABLE,id);
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
    public static List<Admin> all(){
        String sql = String.format("SELECT * FROM %s;",TABLE);
        dbl = new JDBC(sql);
        List<Admin> list = new ArrayList<Admin>();
        try{
            res = dbl.pst.executeQuery();
            while(res.next()){
                Admin admin = new Admin();
                admin.setId(res.getString(1));
                admin.setPassword(res.getString(2));
                admin.setEmail(res.getString(3));
                
                list.add(admin);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static Admin findById(String id){
        String sql = String.format("SELECT * from %s WHERE id = '%s'",TABLE,id);
        dbl = new JDBC(sql);
        Admin admin = null;
        try{
            res = dbl.pst.executeQuery();
            
            if(res.next()){
                admin = new Admin();
                admin.setId(res.getString(1));
                admin.setPassword(res.getString(2));
                admin.setEmail(res.getString(3));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return admin;
    }
}
