/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Snowyy
 */
public class UserDAO {

    DAO db = new DAO();
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean checkLogin(User user) {
        boolean result = false;
        String sql = "SELECT * FROM [User] WHERE username = ? AND password = ? ";
        try {
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setName(rs.getString("name"));
                user.setPosition(rs.getString("position"));
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
