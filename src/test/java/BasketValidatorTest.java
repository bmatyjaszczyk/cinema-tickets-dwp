import org.junit.Before;
import org.junit.Test;
import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationService;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.TicketService;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.*;
import uk.gov.dwp.uc.pairtest.exception.AdultTicketMissingException;
import uk.gov.dwp.uc.pairtest.exception.InvalidNumberOfTicketsException;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;
import uk.gov.dwp.uc.pairtest.exception.TooManyInfantsException;

public class BasketValidatorTest {
    private BasketValidator validator;

    @Before
    public void setUp() {
        validator = new BasketValidator();
    }

    @Test(expected = InvalidNumberOfTicketsException.class)
    public void testPurchaseTicketsWrongNumberOfTickets() {
        Basket basket = new Basket(30, 3, 3);
        validator.validateBasket(basket, 1L);
    }

    @Test
    public void testPurchaseTicketsCorrectNumberOfTicketsNoException() {
        Basket basket = new Basket(13, 3, 2);
        validator.validateBasket(basket, 1L);
    }

    @Test
    public void testPurchaseTicketsOnlyAdultsNoException() {
        Basket basket = new Basket(15, 0, 0);
        validator.validateBasket(basket, 1L);
    }

    @Test(expected = AdultTicketMissingException.class)
    public void testPurchaseTicketsOnlyChildren() {
        Basket basket = new Basket(0, 15, 0);
        validator.validateBasket(basket, 1L);
    }

    @Test(expected = TooManyInfantsException.class)
    public void testPurchaseTicketsOnlyInfants() {
        Basket basket = new Basket(0, 0, 1);
        validator.validateBasket(basket, 1L);
    }

    @Test(expected = TooManyInfantsException.class)
    public void testPurchaseTicketsMoreInfantsThanAdults() {
        Basket basket = new Basket(3, 5, 5);
        validator.validateBasket(basket, 1L);
    }

    @Test
    public void testPurchaseTicketsCorrectAccountNumberNoException() {
        Basket basket = new Basket(2, 15, 0);
        validator.validateBasket(basket, 1L);
    }

    @Test(expected = InvalidPurchaseException.class)
    public void testPurchaseTicketsWrongAccountNumber() {
        Basket basket = new Basket(2, 15, 0);
        validator.validateBasket(basket, 0L);
    }
}
