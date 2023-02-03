package com.revature.service;

import com.revature.exceptions.InvalidLoginException;
import com.revature.exceptions.InvalidUsername;
import com.revature.exceptions.PasswordsDontMatchException;
import com.revature.model.NewUser;
import com.revature.model.User;
import com.revature.repository.UserTable;

import javax.naming.SizeLimitExceededException;
import java.sql.SQLException;
import java.lang.String;

public class UserService {

    private UserTable userTable = new UserTable();

    public void register(String firstName, String lastName, String username, String password, String passwordAgain) throws SQLException, PasswordsDontMatchException, InvalidUsername {

        if (!password.equals(passwordAgain)) {

            throw new PasswordsDontMatchException("Your passwords do not match please try again");
        } else if (username.contains(" ")) {

            throw new InvalidUsername("Invalid username, please try again");
        } else {
                userTable.addNewUser(firstName, lastName, username, password);
        }
    }

    public User login(String username, String password) throws SQLException, InvalidLoginException {

        User user = userTable.authenticate(username, password);

        if (user == null) {
            throw new InvalidLoginException("Invalid username or password, please try again");
        } else {
            return user;
        }
    }

}




