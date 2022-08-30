package ru.netology.homework.tiсket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.netology.homework.tiсket.comparators.TicketTimeComparator;

import java.util.Arrays;
import java.util.Comparator;

@AllArgsConstructor
public class TicketManager {
    private TicketRepo repo;

    public void save(Ticket newTicket) {
        if (saveCheck(newTicket)) {
            repo.save(newTicket);
        }
    }

    private boolean saveCheck(Ticket someTicket) {
        // проверка, что айди не равен 0
        return someTicket.getId() != 0 ? true : false;
    }

    public void removeById(int id) {
        repo.removeById(id);
    }

    // выдача билетов от низкой цены до высокой (при совпадении аэропортов вылета и прилета)
    public Ticket[] findAll(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] searchResult = searchByFromTo(from, to);
        Arrays.sort(searchResult, comparator);

        return searchResult;
    }

    private Ticket[] searchByFromTo(String from, String to) {
        Ticket[] tmp;
        int tmpLength = 0;

        for (Ticket randomTicket : repo.getRepo()) {
            if (matches(from, to, randomTicket)) {
                tmpLength++;
            }
        }

        tmp = new Ticket[tmpLength];
        int copyToIndex = 0;
        for (Ticket randomTicket : repo.getRepo()) {
            if (matches(from, to, randomTicket)) {
                tmp[copyToIndex] = randomTicket;
                copyToIndex++;
            }
        }
        return tmp;
    }

    private boolean matches(String from, String to, Ticket someTicket) {
        return someTicket.getDeparture().contains(from) && someTicket.getArrival().contains(to) ? true : false;
    }
}
