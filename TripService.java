
package main.services;

import java.util.List;
import javax.persistence.EntityManager;
import main.model.Trip;
import org.springframework.stereotype.Service;

@Service
public class TripService extends EntityService<Trip>
{
    public TripService(EntityManager em)
    {
        super(em, Trip.class, Trip::getId);
    }
    
    public List<Trip> findAll()
    {
        return em.createNamedQuery(Trip.FIND_ALL, Trip.class).getResultList();
    }
}
