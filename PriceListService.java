
package main.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import main.model.PriceList;
import org.springframework.stereotype.Service;

@Service
public class PriceListService extends EntityService<PriceList>
{
    public PriceListService(EntityManager em)
    {
        super(em, PriceList.class, PriceList::getId);
    }
    public List<PriceList> findAll()
    {
        return em.createNamedQuery(PriceList.FIND_ALL,PriceList.class).getResultList();
    }
}
