
package main.services;

import java.util.List;
import javax.persistence.EntityManager;
import main.model.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends EntityService<Item> 
{
    public ItemService(EntityManager em)
    {
        super(em,Item.class,Item::getId);
    }
    
    public List<Item> findAll()
    {
        return em.createNamedQuery(Item.FIND_ALL, Item.class).getResultList();
    }
}

