
package main.controllers;

import java.net.URI;
import java.util.List;
import static java.util.Objects.isNull;
import java.util.UUID;
import main.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import main.services.OrderService;
import org.springframework.web.bind.annotation.PutMapping;
import services.exceptions.OutOfStockException;

@RestController
@RequestMapping("/orders")
public class OrderController 
{
    final OrderService orderService;
    private Object uriBuilder;
    
    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }
    @GetMapping
    public List<Order> listOrders()
    {
        return orderService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable UUID id)
    {
        Order order = orderService.find(id);
        return isNull(order) ? ResponseEntity.notFound().build() : ResponseEntity.ok(order);
    }
    @PostMapping
    public ResponseEntity<Void> addOrder(@RequestBody Order order, UriComponentsBuilder uriBuilder)
    {
        try
        {
            orderService.placeOrder(order);
            URI location = uriBuilder.path("orders/{id}").buildAndExpand(order.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        catch (OutOfStockException ex)
        {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
