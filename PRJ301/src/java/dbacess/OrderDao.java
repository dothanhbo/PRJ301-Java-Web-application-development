/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbacess;

import Packages.OrderDetail;
import Packages.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import mylib.DBUtils;

/**
 *
 * @author NCC
 */
public class OrderDao {

    public static ArrayList<Order> getOrders(String email) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select dbo.Orders.OrderID as'OrderID',OrdDate, shipdate, dbo.Orders.status as'status', dbo.Orders.accID as'accID'\n"
                        + "from dbo.Orders\n"
                        + "join dbo.Accounts\n"
                        + "on dbo.Orders.accID=dbo.Accounts.accID\n"
                        + "where dbo.Accounts.email=? ";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, email);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int OrderID = table.getInt("OrderID");
                        String OrdDate = table.getString("OrdDate");
                        String shipdate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("accID");
                        Order ord = new Order(OrderID, OrdDate, shipdate, status, accID);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Order> getOrders(String email, int statusInput) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select dbo.Orders.OrderID as'OrderID',OrdDate, shipdate, dbo.Orders.status as'status', dbo.Orders.accID as'accID'\n"
                        + "from dbo.Orders\n"
                        + "join dbo.Accounts\n"
                        + "on dbo.Orders.accID=dbo.Accounts.accID\n"
                        + "where dbo.Accounts.email=? and dbo.Orders.status=?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, email);
                st.setInt(2, statusInput);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int OrderID = table.getInt("OrderID");
                        String OrdDate = table.getString("OrdDate");
                        String shipdate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("accID");
                        Order ord = new Order(OrderID, OrdDate, shipdate, status, accID);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean cancelOrder(int orderID) {
        boolean check = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Orders\n"
                        + "set status=3\n"
                        + "where OrderID=?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, orderID);
                st.executeUpdate();
                check = true;
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

    public static boolean orderAgain(int orderID) {
        boolean check = false;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Orders\n"
                        + "set status=1\n"
                        + "where OrderID=?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, orderID);
                st.executeUpdate();
                check = true;
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check;
    }

    public static OrderDetail getOrderDetail(int orderID) {
        OrderDetail detail = new OrderDetail();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select DetailId, dbo.Orders.OrderID as 'OrderID', FID, quantity, \n"
                        + "dbo.Plants.PName as'PlantName', dbo.Plants.price as'price', \n"
                        + "dbo.Plants.imgPath as'imgPath', dbo.OrderDetails.quantity as'quantity'\n"
                        + "from dbo.OrderDetails join  dbo.Orders\n"
                        + "on dbo.Orders.OrderID=dbo.OrderDetails.OrderID\n"
                        + "join dbo.Plants\n"
                        + "on dbo.Plants.PID=dbo.OrderDetails.FID\n"
                        + "where dbo.Orders.OrderID=?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, orderID);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int DetailId = table.getInt("DetailId");
                        int OrderID = table.getInt("OrderID");
                        int FID = table.getInt("FID");
                        String PlantName = table.getString("PlantName");
                        int price = table.getInt("price");
                        String imgPath = table.getString("imgPath");
                        int quantity = table.getInt("quantity");
                        detail = new OrderDetail(DetailId, OrderID, FID, PlantName, price, imgPath, quantity);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) throws SQLException {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                String sql = "select AccID from dbo.Accounts where email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("AccID");
                }
                //insert a new order
                Date d = new Date(System.currentTimeMillis());
                sql = "insert Orders(OrdDate, status, AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                //get orderID that is the largest number
                sql = "select top 1 OrderID from Orders order by OrderID desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("OrderID");
                }
                //insert order details
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails values(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    }
