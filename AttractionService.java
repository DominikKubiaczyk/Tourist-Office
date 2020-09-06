
package main.services;

import java.util.List;
import javax.persistence.EntityManager;
import main.model.Attraction;
import org.springframework.stereotype.Service;

@Service
public class AttractionService extends EntityService<Attraction> 
{
    public AttractionService(EntityManager em)
    {
        super(em, Attraction.class, Attraction::getId);
    }
    
    public List<Attraction> findAll()
    {
        return em.createNamedQuery(Attraction.FIND_ALL, Attraction.class).getResultList();
    }
}
