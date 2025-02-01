package com.turkcell.mini_e_commere_cqrs_hw3.controller;

import an.awesome.pipelinr.Pipeline;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.create.CreateOrderCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.delete.DeleteOrderCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.commands.order.update.UpdateOrderStateCommand;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.order.getall.GetUsersAllOrdersQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.application.queries.order.getbyid.GetOrderByIdQuery;
import com.turkcell.mini_e_commere_cqrs_hw3.core.web.BaseController;
import com.turkcell.mini_e_commere_cqrs_hw3.dto.order.OrderListingDto;
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

    public OrderController(Pipeline pipeline) {
        super(pipeline);
    }

    @PostMapping()
    public ResponseEntity<OrderListingDto> createOrder(@AuthenticationPrincipal UserDetails userDetails) {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(UUID.fromString(userDetails.getUsername()));
        return ResponseEntity.ok(createOrderCommand.execute(pipeline));
    }

    @PostMapping("/admin/create-for-user/{userId}")
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
    public ResponseEntity<List<OrderListingDto>> getUsersAllOrders(@AuthenticationPrincipal UserDetails userDetails) {
        GetUsersAllOrdersQuery getUsersAllOrdersQuery = new GetUsersAllOrdersQuery(userDetails.getUsername());
        return ResponseEntity.ok(getUsersAllOrdersQuery.execute(pipeline));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderListingDto> getOrderById(@PathVariable Integer orderId) {
        GetOrderByIdQuery getOrderByIdQuery = new GetOrderByIdQuery(orderId);
        return ResponseEntity.ok(getOrderByIdQuery.execute(pipeline));
    }
}