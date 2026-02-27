package com.nisum.assignment.controllers;

import com.nisum.assignment.dtos.CreateOrderRequestDto;
import com.nisum.assignment.dtos.OrderDto;
import com.nisum.assignment.dtos.UpdateOrderStatusRequestDto;
import com.nisum.assignment.entities.OrderStatus;
import com.nisum.assignment.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequestDto orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable  UUID id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto>> getCustomerOrders(@PathVariable  UUID customerId) {
        return ResponseEntity.ok(orderService.getCustomerOrders(customerId));
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable UUID id, @RequestBody UpdateOrderStatusRequestDto orderStatusReq) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, orderStatusReq.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.HeadersBuilder<?> deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent();
    }
}
