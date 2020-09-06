
package main.model;

import java.util.Date;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "prices")
@EqualsAndHashCode(of = "id")
@NamedQueries({
    @NamedQuery(name = PriceList.FIND_ALL, query = "SELECT b FROM PriceList b")
})

public class PriceList 
{
    public static final String FIND_ALL = "PriceList.FIND_ALL";
    
    @Getter
    @Id
    UUID id = UUID.randomUUID(); 
    
    @Getter
    @Setter
    Double priceAdult;
    
    @Getter
    @Setter
    Double priceTeen;
    
    @Getter
    @Setter
    Double priceChild;
    
    @Getter
    @Setter
    @Temporal(TIMESTAMP)
    Date fromDay;
    
    @Getter
    @Temporal(TIMESTAMP)
    Date toDay;
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "trip_id")
    Trip trip;
    
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "attraction_id")
    Attraction attraction;
}
