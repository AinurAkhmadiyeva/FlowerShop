package FinalProject.FlowerShop.service;

import FinalProject.FlowerShop.dto.OrderDto;
import FinalProject.FlowerShop.dto.UserDto;
import FinalProject.FlowerShop.mapper.OrderMapper;
import FinalProject.FlowerShop.mapper.UserMapper;
import FinalProject.FlowerShop.model.Order;
import FinalProject.FlowerShop.model.User;
import FinalProject.FlowerShop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserMapper userMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
    }

    public OrderDto placeOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        order.setStatus("Pending");
        return orderMapper.toDto(orderRepository.save(order));
    }

    public List<OrderDto> findOrdersByUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return orderRepository.findByUser(user).stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    public List<OrderDto> findOrdersByStatus(String status) {
        return orderRepository.findByStatus(status).stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    public void updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }

    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return orderMapper.toDto(order);
    }

    // Other order-related methods
}
