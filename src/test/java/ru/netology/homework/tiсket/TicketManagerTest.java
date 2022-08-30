package ru.netology.homework.tiсket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketManagerTest {
    TicketRepo repo = new TicketRepo();
    TicketManager manager = new TicketManager(repo);

    Ticket ticket1 = new Ticket(1, 100, "ARH", "ASF", 1010);
    Ticket ticket2 = new Ticket(2, 110, "ARH", "GUW", 201);
    Ticket ticket3 = new Ticket(3, 20109, "ATH", "ASF", 545);
    Ticket ticket4 = new Ticket(4, 40123, "SCO", "GZP", 567);
    Ticket ticket5 = new Ticket(5, 4578, "ARH", "ASF", 5788);
    Ticket ticket6 = new Ticket(6, 45547, "ARH", "ASF", 3467);
    Ticket ticket7 = new Ticket(7, 459970, "ARH", "AZN", 2345);
    Ticket ticket8 = new Ticket(8, 4578, "ARH", "ASF", 5678);
    Ticket ticket9error = new Ticket(0, 4567, "ARH", "GZP", 34567);

    @BeforeEach
    public void prepareTestRepo() {
        manager.save(ticket1);
        manager.save(ticket2);
        manager.save(ticket3);
        manager.save(ticket4);
        manager.save(ticket5);
        manager.save(ticket6);
        manager.save(ticket7);
        manager.save(ticket8);
    }

    @Test
    public void shouldNotSaveWithIdZero() {
        manager.save(ticket9error);
        Ticket[] expected = { ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, ticket7, ticket8};
        Ticket[] actual = repo.getRepo();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        manager.removeById(1);

        Ticket[] expected = { ticket2, ticket3, ticket4, ticket5, ticket6, ticket7, ticket8};
        Ticket[] actual = repo.getRepo();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByDifferentId() {
        manager.removeById(1);
        manager.removeById(5);
        manager.removeById(6);
        manager.removeById(8);

        Ticket[] expected = { ticket2, ticket3, ticket4, ticket7};
        Ticket[] actual = repo.getRepo();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByArrivalAndDepartureAndShowFromMinCostToMax() {
        Ticket[] expected = { ticket1, ticket5, ticket8, ticket6};
        Ticket[] actual = manager.findAll("ARH", "ASF");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotCrashIfSearchByWithEmptyRepo() {
        manager.removeById(1);
        manager.removeById(2);
        manager.removeById(3);
        manager.removeById(4);
        manager.removeById(5);
        manager.removeById(6);
        manager.removeById(7);
        manager.removeById(8);

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("ARH", "ASF");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotCrashIfSearchByWithRepoWithoutTicketsNeeded() {
        manager.removeById(1);
        manager.removeById(5);
        manager.removeById(6);
        manager.removeById(8);

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("ARH", "ASF");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfNameOfAirportConsistMoreLettersThanFullWord() {

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("ARHS", "ASFO");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfMarchOnlyDepartureAirport() {

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("ARH", "RANDOM");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchIfMarchOnlyArrivalAirport() {

        Ticket[] expected = {};
        Ticket[] actual = manager.findAll("RANDOM", "ASF");

        Assertions.assertArrayEquals(expected, actual);
    }
}
