package ru.netology.homework.tiсket;

import lombok.Getter;

@Getter
public class TicketRepo {
    private Ticket[] repo = new Ticket[0];

    public void save(Ticket newTicket) {
        if (saveCheck(newTicket)) {
            Ticket[] tmp = new Ticket[repo.length + 1];
            int copyToIndex = 0;

            for (Ticket randomTicket : repo) {
                tmp[copyToIndex] = randomTicket;
                copyToIndex++;
            }
            tmp[tmp.length - 1] = newTicket;
            repo = tmp;
        }
    }


    private boolean saveCheck(Ticket someTicket) {
        //проверка, что аэропорт вылета и аэропорт прилета не совпали
        if (someTicket.getArrival() == someTicket.getDeparture()) {
            return false;
        }
        // проверка, что нельзя сохранить с двумя одинаковыми айди
        for (Ticket randomTicket : repo) {
            if (someTicket.getId() == randomTicket.getId()) {
                return false;
            }
        }

        // проверка, что id не отрицательное число
        if (someTicket.getId() < 0) {
            return false;
        }
        return true;
    }

    public void removeById(int id) {
        if (removeCheck(id)) {
            Ticket[] tmp = new Ticket[repo.length - 1];
            int copyToIndex = 0;

            for (Ticket randomTicket : repo) {
                if (randomTicket.getId() != id) {
                    tmp[copyToIndex] = randomTicket;
                    copyToIndex++;
                }
            }
            repo = tmp;
        }
    }

    public boolean removeCheck(int id) {
        // проверка, что объект с таким id существует
        for (Ticket randomTicket : repo) {
            if ( randomTicket.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
