package by.scooter.controller;

import by.scooter.api.sevice.ClientService;
import by.scooter.api.sevice.SubscriptionService;
import by.scooter.dto.pricing.SubscriptionDTO;
import by.scooter.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final ClientService clientService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, ClientService clientService) {
        this.subscriptionService = subscriptionService;
        this.clientService = clientService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping("/subscription/{id}")
    public ResponseEntity<SubscriptionDTO> getById(@PathVariable Long id) {
        SubscriptionDTO subscription = subscriptionService.getById(id);
        clientService.checkOwner(subscription.getOwnerId());
        return ResponseEntity.ok(subscription);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping("/clients/{client_id}/subscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getAllByClientId(@PathVariable(name = "client_id") Long clientId,
                                                                  @RequestParam(required = false) Integer page,
                                                                  @RequestParam(required = false) Integer size) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"))) {
            clientService.checkOwner(clientId);
        }
        return ResponseEntity.ok(subscriptionService.getAllByClientId(clientId, page, size));
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/subscription")
    public ResponseEntity<Void> add(@RequestBody @Valid SubscriptionDTO subscription, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        subscriptionService.addSubscription(subscription);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/subscription/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        subscriptionService.removeSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PutMapping("/subscription/{id}")
    public ResponseEntity<Void> renewSubscription(@PathVariable Long id,
                                                  @RequestBody @Valid SubscriptionDTO subscription,
                                                  BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }

        subscriptionService.renewSubscription(id, subscription);
        return ResponseEntity.noContent().build();
    }
}
