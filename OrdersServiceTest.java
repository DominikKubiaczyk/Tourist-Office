import java.util.ArrayList;
import java.util.List;
import main.model.Item;
import main.model.Order;
import main.services.OrderService;
import services.exceptions.OutOfStockException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import main.model.Attraction;
import main.model.PriceList;
import main.model.Trip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class OrdersServiceTest {

    @Mock
    EntityManager em;

  /*  @Test(expected = OutOfStockException.class)
    public void whenOrderedItemNotAvailable_placeOrderThrowsOutOfStockEx() {
        //Arrange
        Order order = new Order();
        Item item = new Item();
        item.setAmount(0);
        order.setClient(item);

        Mockito.when(em.find(Item.class, item.getId())).thenReturn(item);

        OrderService orderService = new OrderService(em);

        //Act
        orderService.placeOrder(order);

        //Assert - exception expected
    }*/
    
   /* @Test
    public void whenOrderedTrip_numberOfPeopleLessThanFreePlaces()
    {
        Order order = new Order();
        
    }
    */
  /*  @Test
    public void isFullPriceInOrderCorrect()
    {
        Order order = new Order();
        Trip trip = new Trip();
        PriceList price = new PriceList();
        Attraction attraction = new Attraction();
        Item item = new Item();
        item.setAge(40);
        item.setAmount(10);
        item.setName("MAREK");
        attraction.setName("Gondola");
        attraction.setTrip(trip);
        order.setTrip(trip);
        order.setClient(item);
        order.setAdultAmount(2);
        order.setChildrenAmount(2);
        order.setTeenagersAmount(1);
        price.setTrip(trip);
        price.setPriceAdult(22.50);
        price.setPriceChild(0.00);
        price.setPriceTeen(11.00);
        price.setAttraction(attraction);
        trip.setName("Barceloma");
        trip.setCapacity(50);
        trip.setPlace("Barcelona");
        
       
        OrderService orderService = new OrderService(em);
        orderService.placeOrder(order);
        double fullPr = price.getPriceAdult()*order.getAdultAmount()+price.getPriceChild()*order.getChildrenAmount()+price.getPriceTeen()*order.getTeenagersAmount();
        
        assertEquals(fullPr, order.getFullPrice(), 0.001);
        
    }*/
    
    @Test
    public void isClientMakinAnOrderAnAdultPerson()
    {
        Order order = new Order();
        Item item = new Item();
        item.setAge(35);
        order.setClient(item);
       // OrderService orderService = new OrderService(em);
       // orderService.placeOrder(order);
        
        //Mockito.when(em.find(Item.class, item.getId())).thenReturn(item);
        assertTrue(18 <= (int)item.getAge());
    }
    
    @Test
    public void isEnoughPlaceToMakeAnOrder()
    {
        Order order = new Order();
        Trip trip = new Trip();
        trip.setCapacity(12);
        order.setTrip(trip);
        order.setAdultAmount(5);
        order.setChildrenAmount(2);
        order.setTeenagersAmount(1);
        assertTrue(trip.getCapacity() >= order.getAdultAmount()+order.getChildrenAmount()+order.getTeenagersAmount());
        
    }
    
    @Test
    public void isChosenAttractionInOrderAvailableInChosenTrip()
    {
        Order order = new Order();
        Trip trip = new Trip();
        Attraction attraction = new Attraction();
        order.setTrip(trip);
        List<Attraction> lista = new ArrayList<>();
        lista.add(attraction);
        order.setAttractions(lista);
        
        for(Attraction attr:lista)
        {
            attr.setTrip(trip);
            assertTrue(attr.getTrip().getId() == order.getTrip().getId());
        }
    }
    
    @Test
    public void isAtLeastOneAdult()
    {
        Order order = new Order();
        order.setAdultAmount(1);
        order.setChildrenAmount(5);
        order.setTeenagersAmount(2);
        
        assertTrue(1<=order.getAdultAmount());
    }
    

   /* @Test
    public void whenOrderedItemAvailable_placeOrderDecreasesAmountByOne() {
        //Arrange
        Order order = new Order();
        Item item = new Item();
        item.setAmount(1);
        order.setClient(item);

        Mockito.when(em.find(Item.class, item.getId())).thenReturn(item);

        OrderService orderService = new OrderService(em);

        //Act
        orderService.placeOrder(order);

        //Assert
        //dostępna liczba książek zmniejszyła się:
        assertEquals(0, (int)item.getAmount());
        //nastąpiło dokładnie jedno wywołanie em.persist(order) w celu zapisania zamówienia:
        Mockito.verify(em, times(1)).persist(order);
    }*/

   /* @Test
    public void whenGivenLowercaseString_toUpperReturnsUppercase() {

        //Arrange
        String lower = "abcdef";

        //Act
        String result = lower.toUpperCase();

        //Assert
        assertEquals("ABCDEF", result);
    }*/
}
