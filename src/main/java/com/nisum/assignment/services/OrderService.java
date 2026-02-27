package com.nisum.assignment.services;

import com.nisum.assignment.dtos.CreateOrderRequestDto;
import com.nisum.assignment.dtos.ItemDto;
import com.nisum.assignment.dtos.OrderDto;
import com.nisum.assignment.entities.*;
import com.nisum.assignment.repositories.CustomerRepository;
import com.nisum.assignment.repositories.ItemRepository;
import com.nisum.assignment.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ItemRepository itemRepository;


    public OrderDto createOrder(CreateOrderRequestDto orderRequest) {

        // Fetch customer
        CustomerEntity customer = customerRepository.findById(orderRequest.customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Validate & fetch all items
        List<ItemEntity> items = orderRequest.getOrderItems().stream()
                .map(orderItem -> itemRepository.findById(orderItem.getItemId())
                        .orElseThrow(() -> new RuntimeException("Item of ID = " + orderItem.getItemId() + "not found")))
                .toList();

        // Create Order
        OrderEntity order = OrderEntity.builder()
                .customer(customer)
                .status(OrderStatus.INPROGRESS)
                .build();

        // Create OrderItems
        List<OrderItem> orderItems = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            ItemEntity item = items.get(i);
            int quantity = orderRequest.getOrderItems().get(i).getQuantity();

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .item(item)
                    .quantity(quantity)
                    .build();

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        OrderEntity savedOrder = orderRepository.save(order);
        OrderDto response = new OrderDto();
        response.id = savedOrder.getId();
        response.customerId = savedOrder.getCustomer().getId();
        response.items = savedOrder.getOrderItems().stream().map(oi -> {
            ItemDto itemDto = new ItemDto();
            itemDto.id = oi.getItem().getId();
            itemDto.name = oi.getItem().getName();
            itemDto.price = oi.getItem().getPrice();
            itemDto.quantity = oi.getQuantity();
            return itemDto;
        }).toList();

        return response;
    }

    public OrderDto getOrderById(UUID id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderDto response = new OrderDto();
        response.id = order.getId();
        response.customerId = order.getCustomer().getId();
        response.items = order.getOrderItems().stream().map(oi -> {
            ItemDto itemDto = new ItemDto();
            itemDto.id = oi.getItem().getId();
            itemDto.name = oi.getItem().getName();
            itemDto.price = oi.getItem().getPrice();
            itemDto.quantity = oi.getQuantity();
            return itemDto;
        }).toList();

        return response;
    }

    public List<OrderDto> getCustomerOrders(UUID customerId) {
        List<OrderEntity> orders = orderRepository.findByCustomerIdWithItems(customerId);

        return orders.stream().map(order -> {
            OrderDto response = new OrderDto();
            response.id = order.getId();
            response.customerId = order.getCustomer().getId();
            response.items = order.getOrderItems().stream().map(oi -> {
                ItemDto itemDto = new ItemDto();
                itemDto.id = oi.getItem().getId();
                itemDto.name = oi.getItem().getName();
                itemDto.price = oi.getItem().getPrice();
                itemDto.quantity = oi.getQuantity();
                return itemDto;
            }).toList();
            return response;
        }).toList();
    }

    public OrderDto updateOrderStatus(UUID id, OrderStatus status) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        OrderEntity updatedOrder = orderRepository.save(order);

        OrderDto response = new OrderDto();
        response.id = updatedOrder.getId();
        response.customerId = updatedOrder.getCustomer().getId();
        response.items = updatedOrder.getOrderItems().stream().map(oi -> {
            ItemDto itemDto = new ItemDto();
            itemDto.id = oi.getItem().getId();
            itemDto.name = oi.getItem().getName();
            itemDto.price = oi.getItem().getPrice();
            itemDto.quantity = oi.getQuantity();
            return itemDto;
        }).toList();

        return response;
    }

    public void deleteOrder(UUID id) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if(order.getStatus() != OrderStatus.CANCELLED){
            throw new RuntimeException("Only orders with CANCELLED status can be deleted");
        }

        orderRepository.delete(order);
    }

}
