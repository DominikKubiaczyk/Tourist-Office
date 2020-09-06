
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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trips")
@EqualsAndHashCode(of ="id")
@NamedQueries({
    @NamedQuery(name = Trip.FIND_ALL, query = "SELECT b FROM Trip b")
})
public class Trip 
{
    public static final String FIND_ALL = "Trip.FIND_ALL";
    
    @Getter
    @Id
    UUID id = UUID.randomUUID();
    
    @Getter
    @Setter
    String name;
    
    @Getter
    @Setter
    String place;
    
    /*@Getter
    @Setter
    String transport;*/
    
    @Getter
    @Setter
    Integer capacity;
    
   /* @Getter
    @Temporal(TIMESTAMP)
    Date tripStartDate;
    
    @Getter
    @Temporal(TIMESTAMP)
    Date tripEndDate;*/
}
