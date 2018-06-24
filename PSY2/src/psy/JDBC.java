/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author LYZ
 */
public class JDBC {
    public static final String url = "jdbc:mysql://localhost:3306/psy";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "123456";
    
    public Connection con = null;
    public PreparedStatement pst = null;
    public JDBC(String sql){
        try{
            Class.forName(name);
            con = DriverManager.getConnection(url,user,password);
            pst = con.prepareStatement(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void close(){
        try{
            this.con.close();
            this.pst.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
