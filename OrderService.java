
package main.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import main.model.Item;
import main.model.Order;
import main.model.PriceList;
import main.model.Trip;
import static org.aspectj.util.LangUtil.isEmpty;
import org.springframework.stereotype.Service;
import services.exceptions.OutOfStockException;

@Service
public class OrderService extends EntityService<Order>
{
    public OrderService(EntityManager em) 
    {
        super(em, Order.class, Order::getId);
    }
    
    public List<Order> findAll()
    {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
    
    
    @Transactional
    public void placeOrder(Order order)
    {
       
        
        Item client = order.getClient();
        Item clientFind = em.find(Item.class, client.getId());
        Trip trip = order.getTrip();        
        Trip tripFind = em.find(Trip.class, trip.getId());
        List<PriceList> prices = em.createNamedQuery(PriceList.FIND_ALL,PriceList.class).getResultList();
        PriceList price = new PriceList();
        for (PriceList priceX: prices)
        {
            if (priceX.getTrip().getId()==tripFind.getId())
            {
                price = priceX;
                break;
            }
        }
        
        int amount = order.getAdultAmount()+order.getChildrenAmount()+order.getTeenagersAmount();
        double fullPrice = price.getPriceAdult()*order.getAdultAmount()+price.getPriceChild()*order.getChildrenAmount()+price.getPriceTeen()*order.getTeenagersAmount();
    
        if(tripFind.getCapacity()<=amount || client.getAge()<18)
            throw new OutOfStockException();
        else
        {
            int changeCapacity = tripFind.getCapacity()-amount;
            tripFind.setCapacity(changeCapacity);
            order.setFullPrice(fullPrice);
        }

        save(order);
    }
}
