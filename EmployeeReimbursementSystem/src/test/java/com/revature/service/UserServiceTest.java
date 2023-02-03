package com.revature.service;


import com.revature.exceptions.InvalidLoginException;
import com.revature.exceptions.InvalidUsername;
import com.revature.exceptions.PasswordsDontMatchException;
import com.revature.model.User;
import com.revature.repository.UserTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)

public class UserServiceTest {

    @Mock
    private UserTable ut;

    @InjectMocks
    private UserService us;

    @Test
    public void testRegisterPasswordsAreNotEqual() throws SQLException, PasswordsDontMatchException, InvalidUsername {

        Assertions.assertThrows(PasswordsDontMatchException.class, () -> {
            us.register("yasin", "adam", "yadam", "12345", "12344");
        });
    }

    @Test
    public void testRegisterUsernameHasSpaces() throws SQLException, PasswordsDontMatchException, InvalidUsername {

        Assertions.assertThrows(InvalidUsername.class, () -> {
            us.register("yasin", "adam", "yad am", "12345", "12345");
        });
    }

    @Test
    public void testLoginUserIsNull() throws SQLException, InvalidLoginException {

        when(ut.authenticate("ghjg","1gjg5" )).thenReturn(null);

        Assertions.assertThrows(InvalidLoginException.class, () -> {
            us.login("ghjg", "1gjg5");
        });
    }

}
