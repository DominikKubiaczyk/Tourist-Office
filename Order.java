
package main.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.MERGE;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@EqualsAndHashCode(of = "id")

public class Order 
{
    @Getter
    @Id
    UUID id = UUID.randomUUID();
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "client_id")
    Item client;
    
    @Getter
    @Setter
    @ManyToMany(cascade = {MERGE})
    List<Attraction> attractions = new ArrayList<>();
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "trip_id")
    Trip trip;
    
    @Getter
    @Setter
    Double fullPrice;
    
    @Getter
    @Setter
    Integer adultAmount;
    
    @Getter
    @Setter
    Integer teenagersAmount;
    
    @Getter
    @Setter
    Integer childrenAmount;
    
    @Getter
    @Temporal(TIMESTAMP)
    Date orderDate;
    
    
    @PrePersist
    public void prePersist()
    {
        orderDate = new Date();
    }
}
