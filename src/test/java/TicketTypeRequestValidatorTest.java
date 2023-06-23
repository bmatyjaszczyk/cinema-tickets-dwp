import org.junit.Before;
import org.junit.Test;
import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationService;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.TicketService;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.BasketCalculator;
import uk.gov.dwp.uc.pairtest.domain.ObjectMapper;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.BasketValidator;
import uk.gov.dwp.uc.pairtest.exception.AdultTicketMissingException;
import uk.gov.dwp.uc.pairtest.exception.InvalidNumberOfTicketsException;
import uk.gov.dwp.uc.pairtest.exception.TooManyInfantsException;

public class TicketTypeRequestValidatorTest {
    private TicketService ticketService;

    @Before
    public void setUp() {
        BasketValidator validator = new BasketValidator();
        TicketPaymentService paymentService = new TicketPaymentServiceImpl();
        SeatReservationService seatReservationService = new SeatReservationServiceImpl();
        BasketCalculator basketCalculator = new BasketCalculator();
        ObjectMapper objectMapper = new ObjectMapper();

        this.ticketService = new TicketServiceImpl(validator, paymentService, seatReservationService, basketCalculator, objectMapper);
    }

    @Test(expected = InvalidNumberOfTicketsException.class)
    public void testPurchaseTicketsWrongNumberOfTickets() {
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 30);
        ticketService.purchaseTickets(1L, request);
    }

    @Test
    public void testPurchaseTicketsCorrectNumberOfTickets() {
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 20);
        ticketService.purchaseTickets(1L, request);
    }

    @Test
    public void testPurchaseTicketsOnlyAdultsMultipleRequests() {
        TicketTypeRequest request1 = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 3);
        TicketTypeRequest request2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5);
        TicketTypeRequest request3 = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2);
        ticketService.purchaseTickets(1L, request1, request2, request3);
    }

    @Test(expected = AdultTicketMissingException.class)
    public void testPurchaseTicketsOnlyChildren() {
        TicketTypeRequest request1 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3);
        TicketTypeRequest request2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5);
        TicketTypeRequest request3 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2);
        ticketService.purchaseTickets(1L, request1, request2, request3);
    }

    @Test(expected = TooManyInfantsException.class)
    public void testPurchaseTicketsOnlyInfants() {
        TicketTypeRequest request1 = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 7);
        ticketService.purchaseTickets(1L, request1);
    }

    @Test(expected = TooManyInfantsException.class)
    public void testPurchaseTicketsMoreInfantsThanAdults() {
        TicketTypeRequest request1 = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 3);
        TicketTypeRequest request2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5);
        TicketTypeRequest request3 = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 7);
        ticketService.purchaseTickets(1L, request1, request2, request3);
    }
}
