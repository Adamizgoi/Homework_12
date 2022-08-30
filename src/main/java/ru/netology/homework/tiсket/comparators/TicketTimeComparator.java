package ru.netology.homework.tiсket.comparators;

import ru.netology.homework.tiсket.Ticket;

import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket o1, Ticket o2) {
        if (o1.getTime() > o2.getTime()) {
            return 1;
        } else if (o1.getTime() < o2.getTime()) {
            return -1;
        }
        return 0;
    }
}
