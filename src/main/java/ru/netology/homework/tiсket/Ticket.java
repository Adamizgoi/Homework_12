package ru.netology.homework.tiсket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Ticket implements Comparable<Ticket> {
    private int id;
    private int cost; // допущение, что стоимость без копеек
    private String departure; // аэропорт вылета
    private String arrival; // аэропорт прилета
    private int time; // время в минутах

    @Override
    public int compareTo(Ticket o) {
        if (this.getCost() > o.getCost()) {
            return 1;
        } else if (this.getCost() < o.getCost()) {
            return -1;
        } else {
            return 0;
        }
    }
}
