package uk.gov.dwp.uc.pairtest.domain;

public class BasketCalculator {
    static final int PRICE_ADULT = 20;
    static final int PRICE_CHILD = 10;

    public int getTotalAmount(Basket basket) {
        return basket.getTicketsAdult() * PRICE_ADULT + basket.getTicketsChild() * PRICE_CHILD;
    }

    public int getNumberOfSeats(Basket basket) {
        return basket.getTicketsAdult() + basket.getTicketsChild();
    }
}
