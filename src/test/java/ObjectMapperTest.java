import org.junit.Assert;
import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.Basket;
import uk.gov.dwp.uc.pairtest.domain.ObjectMapper;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public class ObjectMapperTest {
    @Test
    public void testCreateBasket1Request() {
        TicketTypeRequest request1 = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 3);
        Basket basket = ObjectMapper.createBasket(request1);

        Assert.assertEquals(basket.getTicketsAdult(), 3);
    }

    @Test
    public void testCreateBasket3Requests() {
        TicketTypeRequest request1 = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 3);
        TicketTypeRequest request2 = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 5);
        TicketTypeRequest request3 = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 2);
        Basket basket = ObjectMapper.createBasket(request1, request2, request3);

        Assert.assertEquals(basket.getTicketsAdult(), 3);
        Assert.assertEquals(basket.getTicketsChild(), 5);
        Assert.assertEquals(basket.getTicketsInfant(), 2);
    }
}
