package com.revature.service;

import com.revature.exceptions.InvalidAmountException;
import com.revature.exceptions.InvalidCategoryException;
import com.revature.exceptions.InvalidTicketID;
import com.revature.exceptions.TicketAlreadyUpdated;
import com.revature.model.Ticket;
import com.revature.repository.TicketTable;

import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class TicketService {

   private TicketTable ticketTable = new TicketTable();


      public void submitTicket(double amount, String description, int categoriesId, int usersId) throws SQLException, InvalidAmountException, InvalidCategoryException {


          if (amount <= 0) {
              throw new InvalidAmountException("Amount must be greater than 0");
          } else if (categoriesId < 1 || categoriesId > 4) {
              throw new InvalidCategoryException("Please choose a number corresponding to one of the categories\n" + "1) Food\n" + "2) Travel\n" + "3) Equipment\n" + "4) Other");
          } else {
              ticketTable.submitTicket(amount, description, categoriesId, usersId);
          }
      }

      public List<Ticket> viewAllTickets() throws SQLException {

          List<Ticket> allTickets = ticketTable.viewAllTickets();
          return allTickets;
      }

      public List<Ticket> viewUserTickets(int usersId) throws SQLException {

        List<Ticket> userTickets = ticketTable.viewUserTickets(usersId);
        return userTickets;
    }

        public void updateTicket(int ticketId, int statusId) throws SQLException, TicketAlreadyUpdated, InvalidTicketID {

          if (ticketTable.getTicket(ticketId)) {
              ticketTable.updateTicket(ticketId, statusId);
          }
    }

    public List<Ticket> viewPendingTickets() throws SQLException {
          List<Ticket> pendingTickets = ticketTable.getPendingTickets();
          return pendingTickets;
    }

    public double viewTotalAmount() throws SQLException {

          double total = ticketTable.getTotalAmount();
          return total;
    }

}
