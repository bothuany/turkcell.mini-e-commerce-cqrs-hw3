package com.turkcell.mini_e_commere_cqrs_hw3.controller;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.create.CreateOrderCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.delete.DeleteOrderCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.update.UpdateOrderStateCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.core.web.BaseController;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
import com.turkcell.mini_e_commere_cqrs_hw3.domain.service.application.OrderApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/orders")
public class OrderController extends BaseController {
    private final OrderApplicationService orderApplicationService;

    public OrderController(Pipeline pipeline, OrderApplicationService orderApplicationService) {
        super(pipeline);
        this.orderApplicationService = orderApplicationService;
    }

    @PostMapping()
    public ResponseEntity<OrderListingDto> createOrder(@AuthenticationPrincipal UserDetails userDetails) {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(UUID.fromString(userDetails.getUsername()));
        return ResponseEntity.ok(createOrderCommand.execute(pipeline));
    }

    @PostMapping("/admin/create-for-user/{userId}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<OrderListingDto> createOrderForUser(@PathVariable UUID userId) {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(userId);
        return ResponseEntity.ok(createOrderCommand.execute(pipeline));
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderListingDto> updateOrderState(@PathVariable Integer orderId) {
        UpdateOrderStateCommand updateOrderStateCommand = new UpdateOrderStateCommand(orderId);
        return ResponseEntity.ok(updateOrderStateCommand.execute(pipeline));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(orderId);
        return ResponseEntity.ok(deleteOrderCommand.execute(pipeline));
    }

    @GetMapping()
    public ResponseEntity<List<OrderListingDto>> getAllUserOrders() {
        List<OrderListingDto> orders = orderApplicationService.getAllUserOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderListingDto> getOrderById(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderApplicationService.getOrderById(orderId));
    }
}