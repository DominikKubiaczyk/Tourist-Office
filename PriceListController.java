
package main.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import main.model.PriceList;
import main.services.PriceListService;
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


@RestController
@RequestMapping("/prices")

public class PriceListController 
{
    final PriceListService priceListService;
    
    public PriceListController(PriceListService priceListService)
    {
        this.priceListService = priceListService;
    }
    @GetMapping
    public List<PriceList> listPrice()
    {
        return priceListService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<Void> addPrice(@RequestBody PriceList price, UriComponentsBuilder uriBuilder)
    {
        if(priceListService.find(price.getId()) == null)
        {
            priceListService.save(price);
            URI location = uriBuilder.path("/prices/{id}").buildAndExpand(price.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        else
        {
            return ResponseEntity.status(CONFLICT).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<PriceList> getPrice(@PathVariable UUID id)
    {
        PriceList price = priceListService.find(id);
        return price != null ? ResponseEntity.ok(price) : ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePrice(@RequestBody PriceList price)
    {
        if(priceListService.find(price.getId()) != null)
        {
            priceListService.save(price);
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
