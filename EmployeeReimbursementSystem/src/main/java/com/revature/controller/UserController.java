package com.revature.controller;

import com.revature.exceptions.InvalidLoginException;
import com.revature.exceptions.InvalidUsername;
import com.revature.exceptions.PasswordsDontMatchException;
import com.revature.model.NewUser;
import com.revature.model.Ticket;
import com.revature.model.User;
import com.revature.service.TicketService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import jdk.jfr.StackTrace;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class UserController {

   private UserService userService = new UserService();
   private TicketService ticketService = new TicketService();

    public void mapEndPoints(Javalin app) {

        app.post("/register", (ctx) ->{

            NewUser information = ctx.bodyAsClass(NewUser.class);

            try {
                userService.register(information.getFirstName(), information.getLastName(), information.getUsername(), information.getPassword(), information.getPasswordAgain());

                ctx.result("You have successfully registered, to access the system please go the login page");
            } catch (PasswordsDontMatchException pe) {
                ctx.status(400);
                ctx.result(pe.getMessage());
            } catch (InvalidUsername ue) {
                ctx.status(400);
                ctx.result(ue.getMessage());
            } catch (SQLException se) {
                ctx.status(500);
                ctx.result("Username already exists, please choose a different username");
            }
        });

        app.post("/login", (ctx) ->{

            User credentials = ctx.bodyAsClass(User.class);

            try {
                User user = userService.login(credentials.getUsername(), credentials.getPassword());

                HttpSession session = ctx.req.getSession();
                session.setAttribute("current user", user);
                ctx.result("You have successfully logged in");
            } catch (InvalidLoginException le) {
                ctx.status(400);
                ctx.result(le.getMessage());
            }

        });



        app.post("/logout", (ctx) ->{

            ctx.req.getSession().invalidate();
            ctx.result("You have successfully logged out");
        });


    }
}
