
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
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

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
    public void testPurchaseTicketsWithCorrectAccountNumberAndRequestsNoException() {
        TicketTypeRequest request1 = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 3);
        TicketTypeRequest request2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 3);
        TicketTypeRequest request3 = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 3);

        ticketService.purchaseTickets(1L, request1, request2, request3);
    }
}
