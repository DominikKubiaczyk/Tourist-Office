
package main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client")
@EqualsAndHashCode(of ="id")
@NamedQueries({
    @NamedQuery(name = Item.FIND_ALL, query = "SELECT b FROM Item b")
})
public class Item 
{
    public static final String FIND_ALL = "Item.FIND_ALL";
    
    @Getter
    @Id
    UUID id = UUID.randomUUID(); 
    
    @Getter
    @Setter
    String name;
    
    @Getter
    @Setter
    Integer age;
    
    @Getter
    @Setter
    Integer amount;


}
