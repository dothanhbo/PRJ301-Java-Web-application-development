/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbacess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Packages.Plant;
import mylib.DBUtils;

/**
 *
 * @author ACER
 */
public class PlantDao {

    public static ArrayList<Plant> getPlants(String keyword, String searchby) throws Exception {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null && searchby != null) {
            String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                    + "from Plants join Categories on Plants.CateID=Categories.CateID\n";
            if (searchby.equalsIgnoreCase("byname")) {
                sql = sql + "where Plants.PName like ?";
            } else {
                sql = sql + "where CateName like ?";
            }
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int PID = rs.getInt("PID");
                    String PName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int CateID = rs.getInt("CateID");
                    String CateName = rs.getString("CateName");
                    Plant plant = new Plant(PID, PName, price, imgPath, description, status, CateID, CateName);
                    list.add(plant);
                }
            }
        }
        cn.close();
        
        return list;
    }
    
    public static int getPrice(int Pid) throws Exception{
        int price=0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select price\n"
                    + "from Plants where PID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1,Pid);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                price = rs.getInt("price");   
                }
    }
    cn.close();
    return price;
}
    
    public static Plant getPlant(String pid) throws Exception {
        Plant plant = new Plant();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                    + "from Plants join Categories on Plants.CateID=Categories.CateID\n"
                    + "where Plants.PID like ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%" + pid + "%");
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int PID = rs.getInt("PID");
                    String PName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgPath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int CateID = rs.getInt("CateID");
                    String CateName = rs.getString("CateName");
                    plant = new Plant(PID, PName, price, imgPath, description, status, CateID, CateName);
                }
            }
        }
        cn.close();
        
        return plant;
    }
}