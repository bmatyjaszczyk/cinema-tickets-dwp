package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.*;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
    BasketValidator validator;
    TicketPaymentService paymentService;
    SeatReservationService seatReservationService;
    BasketCalculator basketCalculator;
    ObjectMapper objectMapper;

    public TicketServiceImpl(
            BasketValidator validator,
            TicketPaymentService paymentService,
            SeatReservationService seatReservationService,
            BasketCalculator basketCalculator,
            ObjectMapper objectMapper
    ) {
        this.validator = validator;
        this.paymentService = paymentService;
        this.seatReservationService = seatReservationService;
        this.basketCalculator = basketCalculator;
        this.objectMapper = objectMapper;
    }

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        try {
            Basket basket = objectMapper.createBasket(ticketTypeRequests);
            this.validator.validateBasket(basket, accountId);

            int totalAmount = this.basketCalculator.getTotalAmount(basket);
            int totalNumberOfSeats = this.basketCalculator.getNumberOfSeats(basket);

            this.paymentService.makePayment(accountId, totalAmount);
            this.seatReservationService.reserveSeat(accountId, totalNumberOfSeats);
        } catch (InvalidPurchaseException exception) {
            //TODO: Handle this accordingly to the project requirements
            System.out.println(exception.getMessage());
        }
    }
}
