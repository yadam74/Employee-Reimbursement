package com.revature;

import com.revature.controller.TicketController;
import com.revature.controller.UserController;
import com.revature.model.User;
import com.revature.repository.UserTable;
import io.javalin.Javalin;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create();

        UserController uc = new UserController();
        TicketController tc = new TicketController();

       uc.mapEndPoints(app);
       tc.mapEndPoints(app);

        app.start(8080);

    }
}
