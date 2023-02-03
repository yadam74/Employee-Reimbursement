package com.revature.repository;

import com.revature.model.User;

import java.sql.*;

public class UserTable {


   public void addNewUser(String firstName, String lastName, String username, String password) throws SQLException {

       try (Connection connectionObj = ConnectionBuilder.createConnection()) {
            String sql = "insert into users (first_name, last_name, username, password, roles_id) values (?, ?, ?, ?, ?)";
            PreparedStatement pst = connectionObj.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, username);
            pst.setString(4, password);
            pst.setInt(5, 2);

           pst.executeUpdate();


            ResultSet rs = pst.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
        }
    }

    public User authenticate(String username, String password) throws SQLException {

       try (Connection connectionObj = ConnectionBuilder.createConnection())  {
           String sql = "select * from users as u where u.username = ? and u.password = ?";
           PreparedStatement pst = connectionObj.prepareStatement(sql);

           pst.setString(1, username);
           pst.setString(2, password);

           ResultSet rs = pst.executeQuery();

           if (rs.next()) {
               int id = rs.getInt(1);
               String fn = rs.getString(2);
               String ln = rs.getString(3);
               String un = rs.getString(4);
               String pw = rs.getString(5);
               int rid = rs.getInt(6);

               return new User(id, fn, ln, un, pw, rid);
           } else {
                return null;
           }



       }
    }
}
