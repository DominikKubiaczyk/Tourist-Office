
package main.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import main.model.Item;
import static org.springframework.http.HttpStatus.CONFLICT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import main.services.ItemService;


@RestController
@RequestMapping("/items")
public class ItemController 
{
    final ItemService itemService;
    
    public ItemController(ItemService itemService)
    {
        this.itemService = itemService;
    }
    
    @GetMapping
    public List<Item> listItems()
    {
        return itemService.findAll();
    }
    @PostMapping
    public ResponseEntity<Void> addItem(@RequestBody Item item, UriComponentsBuilder uriBuilder)
    {
        if(itemService.find(item.getId()) == null)
        {
            itemService.save(item);
            URI location = uriBuilder.path("/items/{id}").buildAndExpand(item.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        else
        {
            return ResponseEntity.status(CONFLICT).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable UUID id)
    {
        Item item = itemService.find(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(@RequestBody Item item)
    {
        if(itemService.find(item.getId()) != null)
        {
            itemService.save(item);
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
