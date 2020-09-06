
package main.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import main.model.Attraction;
import main.services.AttractionService;
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
@RequestMapping("/attractions")

public class AttractionController 
{
    final AttractionService attractionService;
    
    public AttractionController(AttractionService attractionService)
    {
        this.attractionService = attractionService;
    }
    @GetMapping
    public List<Attraction> listAttraction()
    {
        return attractionService.findAll();
    }
    @PostMapping
    public ResponseEntity<Void> addItem(@RequestBody Attraction attraction, UriComponentsBuilder uriBuilder)
    {
        if(attractionService.find(attraction.getId()) == null)
        {
            attractionService.save(attraction);
            URI location = uriBuilder.path("/attractions/{id}").buildAndExpand(attraction.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        else
        {
            return ResponseEntity.status(CONFLICT).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Attraction> getAttraction(@PathVariable UUID id)
    {
        Attraction attraction = attractionService.find(id);
        return attraction != null ? ResponseEntity.ok(attraction) : ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAttraction(@RequestBody Attraction attraction)
    {
        if(attractionService.find(attraction.getId()) != null)
        {
            attractionService.save(attraction);
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
