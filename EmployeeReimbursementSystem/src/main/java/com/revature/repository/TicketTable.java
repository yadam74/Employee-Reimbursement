package com.revature.repository;

import com.revature.exceptions.InvalidTicketID;
import com.revature.exceptions.TicketAlreadyUpdated;
import com.revature.model.Ticket;

import javax.swing.text.TabableView;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketTable {

    public void submitTicket(double amount, String description, int categoriesId, int usersId) throws SQLException {

        try (Connection connectionObj = ConnectionBuilder.createConnection()) {
            String sql = "insert into tickets (amount, description, categories_id, status_id, users_id) values (?, ?, ?, ?, ?)";
            PreparedStatement pst = connectionObj.prepareStatement(sql);

            pst.setDouble(1, amount);
            pst.setString(2, description);
            pst.setInt(3, categoriesId);
            pst.setInt(4, 1);
            pst.setInt(5, usersId);

            pst.executeUpdate();
        }
    }

    public List<Ticket> viewAllTickets() throws SQLException {

        try (Connection connectionObj = ConnectionBuilder.createConnection()) {

            List<Ticket> tickets = new ArrayList<Ticket>();

            String sql = "select * from tickets order by id asc";
            PreparedStatement pst = connectionObj.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                double amt = rs.getDouble("amount");
                String des = rs.getString("description");
                int catId = rs.getInt("categories_id");
                int status = rs.getInt("status_id");
                int userId = rs.getInt("users_id");

                Ticket allTickets = new Ticket(id, amt, des, catId, status, userId);
                tickets.add(allTickets);

                
            }

            return tickets;

        }
    }

    public List<Ticket> viewUserTickets(int usersId) throws SQLException {

        try (Connection connectionObj = ConnectionBuilder.createConnection()) {

            List<Ticket> tickets = new ArrayList<Ticket>();

            String sql = "select * from tickets where users_id = ? order by amount desc";
            PreparedStatement pst = connectionObj.prepareStatement(sql);

            pst.setInt(1, usersId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                double amt = rs.getDouble("amount");
                String des = rs.getString("description");
                int catId = rs.getInt("categories_id");
                int status = rs.getInt("status_id");
                int userId = rs.getInt("users_id");

                Ticket allTickets = new Ticket(id, amt, des, catId, status, userId);
                tickets.add(allTickets);
            }

            return tickets;

        }
    }

    public List<Ticket> getPendingTickets() throws SQLException {

        try (Connection connectionObj = ConnectionBuilder.createConnection()) {

            List<Ticket> tickets = new ArrayList<Ticket>();

            String sql = "select * from tickets where status_id = 1 order by id desc";
            PreparedStatement pst = connectionObj.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                double amt = rs.getDouble("amount");
                String des = rs.getString("description");
                int catId = rs.getInt("categories_id");
                int status = rs.getInt("status_id");
                int userId = rs.getInt("users_id");

                Ticket allPendingTickets = new Ticket(id, amt, des, catId, status, userId);
                tickets.add(allPendingTickets);
            }

            return tickets;

        }
    }

    public boolean getTicket(int ticketId) throws SQLException, InvalidTicketID, TicketAlreadyUpdated {

        try (Connection connection = ConnectionBuilder.createConnection()) {

            String sql = "select status_id from tickets where id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1, ticketId);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                throw new InvalidTicketID("No such ticket exists in the system");
            } else if ((rs.getInt(1) == 2 || (rs.getInt(1) == 3 ))) {
                throw new TicketAlreadyUpdated("This ticket has already been approved or denied");
            }
        } return true;
    }

    public void updateTicket(int ticketsId, int statusId) throws SQLException {

        try (Connection connection = ConnectionBuilder.createConnection()) {

            String sql = "update tickets set status_id = ? where id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setInt(1,statusId);
            pst.setInt(2, ticketsId);

            pst.executeUpdate();

        }
    }

    public double getTotalAmount() throws SQLException {

        try (Connection connection = ConnectionBuilder.createConnection()) {

            String sql = "select get_total_amount_reimbursements()";
            CallableStatement cst = connection.prepareCall(sql);

            ResultSet rs = cst.executeQuery();
            rs.next();
           double total = rs.getDouble(1);
            return total;

        }
    }

}
