package com.revature.controller;

import com.revature.exceptions.InvalidAmountException;
import com.revature.exceptions.InvalidCategoryException;
import com.revature.exceptions.InvalidTicketID;
import com.revature.exceptions.TicketAlreadyUpdated;
import com.revature.model.Ticket;
import com.revature.model.User;
import com.revature.repository.TicketTable;
import com.revature.service.TicketService;
import io.javalin.Javalin;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketController {

   private TicketService ticketService = new TicketService();

   public void mapEndPoints(Javalin app) {

      app.post("/submit", (ctx) ->{

         HttpSession session = ctx.req.getSession();
         User currentUser = (User) session.getAttribute("current user");

         if (currentUser == null) {

            ctx.status(400);
            ctx.result("You are not logged in");

         } if (currentUser.getRolesId() == 2) {

             try {
               Ticket information = ctx.bodyAsClass(Ticket.class);
               ticketService.submitTicket(information.getAmount(), information.getDescription(), information.getCategoriesId(), currentUser.getId());
               ctx.result("You have successfully submitted a ticket, to view all your submissions please head to the view page");

            } catch (InvalidAmountException ae) {
               ctx.status(400);
               ctx.result(ae.getMessage());
            } catch (SQLException se) {
               ctx.status(500);
            } catch (InvalidCategoryException ce) {
                ctx.status(400);
                ctx.result(ce.getMessage());
             }


         } else {
            ctx.status(400);
            ctx.result("You are logged in as a manager and cannot submit a ticket, please logout and login with employee credentials");
         }

      });


      app.get("/view", (ctx) ->{

          HttpSession session = ctx.req.getSession();
          User currentUser = (User) session.getAttribute("current user");

          if (currentUser == null) {

              ctx.status(400);
              ctx.result("You are not logged in");
          } else if (currentUser.getRolesId() == 1) {

              List<Ticket> allTickets = ticketService.viewAllTickets();
              ctx.json(allTickets);
          } else {

              List<Ticket> userTickets = ticketService.viewUserTickets(currentUser.getId());
              ctx.json(userTickets);
          }

      });

       app.get("/pending", (ctx) ->{

           HttpSession session = ctx.req.getSession();
           User currentUser = (User) session.getAttribute("current user");

           if (currentUser == null) {

               ctx.status(400);
               ctx.result("You are not logged in");
           } else if (currentUser.getRolesId() == 2) {

               ctx.status(400);
               ctx.result("This page is reserved for managers only");
           } else {

               List<Ticket> pendingTickets = ticketService.viewPendingTickets();
               ctx.json(pendingTickets);
           }

       });

      app.patch("/update/{ticketId}", (ctx) ->{

          HttpSession session = ctx.req.getSession();
          User currentUser = (User) session.getAttribute("current user");

          if (currentUser == null) {
              ctx.status(400);
              ctx.result("You are not logged in");
          } else if (currentUser.getRolesId() == 2) {
              ctx.status(400);
              ctx.result("You do not have authorization to update tickets");
          } else {
              int ticketId = Integer.parseInt(ctx.pathParam("ticketId"));
              Ticket status = ctx.bodyAsClass(Ticket.class);

              try {

                  ticketService.updateTicket(ticketId, status.getStatusId());
                  ctx.result("You have successfully updated the ticket");
              } catch (InvalidTicketID te) {
                  ctx.status(400);
                  ctx.result(te.getMessage());
              } catch (TicketAlreadyUpdated ue) {
                ctx.status(400);
                ctx.result(ue.getMessage());
              }
          }

      });

       app.get("/total", (ctx) ->{

           HttpSession session = ctx.req.getSession();
           User currentUser = (User) session.getAttribute("current user");

           if (currentUser == null) {

               ctx.status(400);
               ctx.result("You are not logged in");
           } else if (currentUser.getRolesId() == 2) {

               ctx.status(400);
               ctx.result("This page is reserved for managers only");
           } else {

               String total = String.valueOf(ticketService.viewTotalAmount());
               ctx.result(total);
           }

       });

   }

}
