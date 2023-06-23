import org.junit.Assert;
import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.Basket;
import uk.gov.dwp.uc.pairtest.domain.BasketCalculator;

public class BasketCalculatorTest {
    @Test
    public void testCalculateNumberOfSeatsAdultsOnly() {
        Basket basket = new Basket(10, 0, 0);
        BasketCalculator basketCalculator = new BasketCalculator();
        Assert.assertEquals(basketCalculator.getNumberOfSeats(basket), 10);
    }

    @Test
    public void testCalculateNumberOfSeatsAdultsWitChild() {
        Basket basket = new Basket(10, 5, 0);
        BasketCalculator basketCalculator = new BasketCalculator();
        Assert.assertEquals(basketCalculator.getNumberOfSeats(basket), 15);
    }

    @Test
    public void testCalculateNumberOfSeatsAdultsWitInfants() {
        Basket basket = new Basket(10, 5, 5);
        BasketCalculator basketCalculator = new BasketCalculator();
        Assert.assertEquals(basketCalculator.getNumberOfSeats(basket), 15);
    }

    @Test
    public void testCalculateAmountForTicketsAdultsOnly() {
        Basket basket = new Basket(10, 0, 0);
        BasketCalculator basketCalculator = new BasketCalculator();
        Assert.assertEquals(basketCalculator.getTotalAmount(basket), 200);
    }

    @Test
    public void testCalculateAmountForTicketsAdultsAndChild() {
        Basket basket = new Basket(10, 5, 0);
        BasketCalculator basketCalculator = new BasketCalculator();
        Assert.assertEquals(basketCalculator.getTotalAmount(basket), 250);
    }

    @Test
    public void testCalculateAmountForTicketsAdultsAndChildAndInfants() {
        Basket basket = new Basket(10, 5, 5);
        BasketCalculator basketCalculator = new BasketCalculator();
        Assert.assertEquals(basketCalculator.getTotalAmount(basket), 250);
    }

    @Test
    public void testCalculateAmountForTicketsAdultsAndInfants() {
        Basket basket = new Basket(10, 0, 5);
        BasketCalculator basketCalculator = new BasketCalculator();
        Assert.assertEquals(basketCalculator.getTotalAmount(basket), 200);
    }
}
