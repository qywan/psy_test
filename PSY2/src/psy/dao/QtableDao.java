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
import psy.bean.Qtable;

/**
 *
 * @author LYZ
 */
public class QtableDao {

    static String sql = null;
    static JDBC dbl = null;
    static ResultSet res = null;
    static final String TABLE = "qtable";

    public static List<Qtable> all() throws Exception {
        List<Qtable> list = new ArrayList<Qtable>();
        String sql = String.format("SELECT * FROM %s", TABLE);
        dbl = new JDBC(sql);
        res = dbl.pst.executeQuery();
        while (res.next()) {
            Qtable qt = new Qtable();
            qt.setId(res.getInt(1));
            qt.setName(res.getString(2));
            qt.setTime(res.getString(3));
            list.add(qt);
        }
        return list;
    }
    
    public static List<Qtable> completedTable() throws Exception {
        List<Qtable> list = new ArrayList<Qtable>();
        String sql = String.format("SELECT * FROM %s where status=2", TABLE);
        dbl = new JDBC(sql);
        res = dbl.pst.executeQuery();
        while (res.next()) {
            Qtable qt = new Qtable();
            qt.setId(res.getInt(1));
            qt.setName(res.getString(2));
            qt.setTime(res.getString(3));
            list.add(qt);
        }
        return list;
    }
    
    public static List<Qtable> unCompletedTable() throws Exception {
        List<Qtable> list = new ArrayList<Qtable>();
        String sql = String.format("SELECT * FROM %s where status=0 or status=1", TABLE);
        dbl = new JDBC(sql);
        res = dbl.pst.executeQuery();
        while (res.next()) {
            Qtable qt = new Qtable();
            qt.setId(res.getInt(1));
            qt.setName(res.getString(2));
            qt.setTime(res.getString(3));
            list.add(qt);
        }
        return list;
    }
}
