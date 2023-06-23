
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
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;;
import uk.gov.dwp.uc.pairtest.domain.BasketValidator;
import uk.gov.dwp.uc.pairtest.exception.InvalidAccountNumberException;

public class TicketServiceImplTest {
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

    @Test
    public void testPurchaseTicketsCorrectAccountNumber() {
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 20);
        ticketService.purchaseTickets(1L, request);
    }

    @Test(expected = InvalidAccountNumberException.class)
    public void testPurchaseTicketsWrongAccountNumber() {
        TicketTypeRequest request = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 20);
        ticketService.purchaseTickets(0L, request);
    }
}
