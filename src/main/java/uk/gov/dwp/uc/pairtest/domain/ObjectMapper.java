package uk.gov.dwp.uc.pairtest.domain;

/**
 * This logic could be inside a controller
 */
public class ObjectMapper {
    public static Basket createBasket(TicketTypeRequest... ticketTypeRequests) {
        int totalAmountOfTicketsAdult = 0;
        int totalAmountOfTicketsChild = 0;
        int totalAmountOfTicketsInfants = 0;

        for (TicketTypeRequest ticketTypeRequest : ticketTypeRequests) {
            if (ticketTypeRequest.getTicketType() == TicketTypeRequest.Type.ADULT) {
                totalAmountOfTicketsAdult += ticketTypeRequest.getNoOfTickets();
            } else if (ticketTypeRequest.getTicketType() == TicketTypeRequest.Type.CHILD) {
                totalAmountOfTicketsChild += ticketTypeRequest.getNoOfTickets();
            }
            else {
                totalAmountOfTicketsInfants += ticketTypeRequest.getNoOfTickets();
            }
        }

        return new Basket(totalAmountOfTicketsAdult, totalAmountOfTicketsChild, totalAmountOfTicketsInfants);
    }
}
