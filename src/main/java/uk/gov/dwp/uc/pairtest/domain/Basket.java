package uk.gov.dwp.uc.pairtest.domain;

public class Basket {
    private int ticketsAdult;
    private int ticketsChild;
    private int ticketsInfant;

    public Basket(int ticketsAdult, int ticketsChild, int ticketsInfant) {
        this.ticketsAdult = ticketsAdult;
        this.ticketsChild = ticketsChild;
        this.ticketsInfant = ticketsInfant;
    }

    public int getTicketsAdult() {
        return ticketsAdult;
    }

    public int getTicketsChild() {
        return ticketsChild;
    }

    public int getTicketsInfant() {
        return ticketsInfant;
    }
}
