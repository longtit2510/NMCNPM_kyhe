/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Room;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Snowyy
 */
public class RoomDAO {

    DAO db = new DAO();
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Room> searchRoom(String key) {
        ArrayList<Room> result = new ArrayList<Room>();

        String sql = "SELECT * FROM ROOM WHERE name LIKE ?";
        try {
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Room rm = new Room();
                rm.setId(rs.getInt("id"));
                rm.setName(rs.getString("name"));
                rm.setType(rs.getString("type"));
                rm.setPrice(rs.getFloat("price"));
                rm.setDes(rs.getString("des"));
                result.add(rm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * update the @room
     *
     * @param rm
     */
    public boolean updateRoom(Room rm) {
        String sql = "UPDATE ROOM SET name=?, type=?, price=?, des=? WHERE id =  ? ";
        try {
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, rm.getName());
            ps.setString(2, rm.getType());
            ps.setFloat(3, rm.getPrice());
            ps.setString(4, rm.getDes());
            ps.setInt(5, rm.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*public ArrayList<Room> searchFreeRoom(Date checkin, Date checkout) {
        ArrayList<Room> result = new ArrayList<Room>();
        String sql = "SELECT * FROM ROOM WHERE id NOT IN (SELECT idroom "
                + "FROM tblBookedRoom WHERE checkout > ? AND checkin < ?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sdf.format(checkin));
            ps.setString(2, sdf.format(checkout));
            rs = ps.executeQuery();
            while (rs.next()) {
                Room rm = new Room();
                rm.setId(rs.getInt("id"));
                rm.setName(rs.getString("name"));
                rm.setType(rs.getString("type"));
                rm.setPrice(rs.getFloat("price"));
                rm.setDes(rs.getString("des"));
                result.add(rm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }*/
}
