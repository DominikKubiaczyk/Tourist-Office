
package main.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import main.model.Trip;
import main.services.TripService;
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
@RequestMapping("/trips")
public class TripController 
{
    final TripService tripService;
    public TripController(TripService tripService)
    {
        this.tripService = tripService;
    }
    
    @GetMapping
    public List<Trip> listTrips()
    {
        return tripService.findAll();
    }
    
    @PostMapping
    public ResponseEntity<Void> addTrip(@RequestBody Trip trip, UriComponentsBuilder uriBuilder)
    {
        if(tripService.find(trip.getId()) == null)
        {
            tripService.save(trip);
            URI location = uriBuilder.path("/trips/{id}").buildAndExpand(trip.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        else
        {
            return ResponseEntity.status(CONFLICT).build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable UUID id)
    {
        Trip trip = tripService.find(id);
        return trip != null ? ResponseEntity.ok(trip) : ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTrip(@RequestBody Trip trip)
    {
        if(tripService.find(trip.getId()) != null)
        {
            tripService.save(trip);
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }
    
}
