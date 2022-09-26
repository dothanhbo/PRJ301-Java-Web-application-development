/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbacess;

import Packages.Account;
import Packages.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author huy
 */
public class AccountDao {

    

    public static boolean insertAccount(String email, String password, String fullname, String phone, int status, int role) throws Exception {
        int check = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "Insert dbo.Accounts(email,password,fullname,phone,status,role)\n"
                    + "values(?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1,email );
            pst.setString(2, password);
            pst.setString(3, fullname);
            pst.setString(4, phone);
            pst.setInt(5, status);
            pst.setInt(6, role);
            check = pst.executeUpdate();
        }
        if (check>0)
            return true;
        else
            return false;
                   
    }
    public static Account getAccount(String email, String password) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID,email,password,fullname,phone,status,role\n"
                    + "from dbo.Accounts\n"
                    + "where status = 1 and email = ? and password = ? COLLATE Latin1_General_CS_AS";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet table1 = st.executeQuery();
            if (table1 != null && table1.next()) {
                int accid = table1.getInt("accID");
                String fullname = table1.getString("fullname");
                String phone = table1.getString("phone");
                int status = table1.getInt("status");
                int role = table1.getInt("role");
                acc = new Account(accid, email, password, fullname, phone, status, role);
            }
            cn.close();
        }
        return acc;
    }
    
    public static Account getAccount(String email) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID,email,password,fullname,phone,status,role\n"
                    + "from dbo.Accounts\n"
                    + "where status = 1 and email = ? COLLATE Latin1_General_CS_AS";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet table1 = st.executeQuery();
            if (table1 != null && table1.next()) {
                int accid = table1.getInt("accID");
                String password = table1.getString("password");
                String fullname = table1.getString("fullname");
                String phone = table1.getString("phone");
                int status = table1.getInt("status");
                int role = table1.getInt("role");
                acc = new Account(accid, email, password, fullname, phone, status, role);
            }
            cn.close();
        }
        return acc;
    }

    public static void changeProfile(String email, String name, String phone) throws Exception{
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update dbo.Accounts\n" +
                         "set fullname=?, phone=?\n" +
                         "where email=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1,name );
            pst.setString(2, phone);
            pst.setString(3, email);
            pst.executeUpdate();
            cn.close();
        }
    }

    public static void updateToken(String token, String email)throws Exception {
        //step 1: make connection

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update dbo.Accounts set token=?\n"
                    + " where email =?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, token);
            st.setString(2, email);
            st.executeUpdate();
            cn.close();
        }
    }
}
