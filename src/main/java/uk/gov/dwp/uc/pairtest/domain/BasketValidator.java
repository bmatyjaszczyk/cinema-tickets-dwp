package uk.gov.dwp.uc.pairtest.domain;

import uk.gov.dwp.uc.pairtest.exception.AdultTicketMissingException;
import uk.gov.dwp.uc.pairtest.exception.InvalidAccountNumberException;
import uk.gov.dwp.uc.pairtest.exception.InvalidNumberOfTicketsException;
import uk.gov.dwp.uc.pairtest.exception.TooManyInfantsException;

public class BasketValidator {
    static final int MAX_TICKETS_AT_ONCE = 20;
    public void validateBasket(Basket basket, Long accountId) {
        if (accountId <= 0) {
            throw new InvalidAccountNumberException();
        }

        if (basket.getTicketsAdult() + basket.getTicketsChild() + basket.getTicketsInfant() > BasketValidator.MAX_TICKETS_AT_ONCE) {
            throw new InvalidNumberOfTicketsException();
        }

        if (basket.getTicketsChild() > 0 && basket.getTicketsAdult() == 0) {
            throw new AdultTicketMissingException();
        }

        if (basket.getTicketsInfant() > basket.getTicketsAdult()) {
            throw new TooManyInfantsException();
        }
    }
}
