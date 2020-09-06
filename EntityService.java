
package main.services;

import java.util.UUID;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


abstract public class EntityService<T>
{
    final EntityManager em;
    private final Class<T> entityClass;
    private final Function<T,Object> idSupplier;
    
    public EntityService(EntityManager em, Class<T> entityClass, Function<T, Object> idSupplier)
    {
        this.em = em;
        this.entityClass = entityClass;
        this.idSupplier = idSupplier;
    }
    
    @Transactional
    public void save(T entity)
    {
        if(em.find(entityClass, idSupplier.apply(entity))==null)
        {
            em.persist(entity);
        }
        else
        {
            em.merge(entity);
        }
    }
    
    public T find(UUID id)
    {
        return em.find(entityClass,id);
    }
    
    
}
