package com.revature.service;

import com.revature.exceptions.InvalidAmountException;
import com.revature.exceptions.InvalidCategoryException;
import com.revature.exceptions.InvalidTicketID;
import com.revature.model.Ticket;
import com.revature.repository.TicketTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketTable tt;

    @InjectMocks
    private TicketService ts;

    @Test
    public void testSubmitTicketLessThanZero() throws InvalidAmountException {

        Assertions.assertThrows(InvalidAmountException.class, () -> {

            ts.submitTicket(-100, "hjsdfb", 1, 1);
        });
    }

    @Test
    public void testSubmitTicketWrongCategoryId() throws InvalidCategoryException {

        Assertions.assertThrows(InvalidCategoryException.class, () -> {

            ts.submitTicket(100, "hhf", 5, 1);
        });
    }

    @Test
    public void testUpdateTicketDoesNotExist() throws InvalidTicketID {

        Assertions.assertThrows(InvalidTicketID.class, () -> {

            when(tt.getTicket(1)).thenThrow(InvalidTicketID.class);
            ts.updateTicket(1, 2);
        });
    }

    @Test
    public void testViewPendingTicketShouldReturnSameList() throws SQLException {

        when(tt.getPendingTickets()).thenReturn(new ArrayList<Ticket>());

        List<Ticket> tickets = tt.getPendingTickets();

        List<Ticket> actual = ts.viewPendingTickets();

        List<Ticket> expected = tickets;

        Assertions.assertEquals(expected, actual);

        }

}
