package pl.webapp.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.buisness.dto.OrderDTO;
import pl.webapp.buisness.dto.mappers.OrderMapper;
import pl.webapp.buisness.dto.mappers.UserMapper;
import pl.webapp.infrastructure.database.entity.OrderEntity;
import pl.webapp.infrastructure.database.repositories.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderService {

    OrderRepository orderRepository;
    OrderMapper orderMapper;

    UserMapper userMapper;

    @Transactional
    public List<OrderDTO> getAllOrders(){
        return orderRepository.findAll().stream()
                .map(order -> orderMapper.map(order))
                .toList();
    }

    @Transactional
    public OrderDTO getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findAll().stream()
                .filter(order -> orderNumber.equals(order.getOrderNumber()))
                .map(order -> orderMapper.map(order))
                .findAny().orElseThrow(() -> new EntityNotFoundException("Order with orderNumber: [%s], not found.".formatted(orderNumber)));
    }

    @Transactional
    public List<OrderDTO> getUndeliveredOrders() {
        return orderRepository.findAll().stream()
                .filter(order -> !order.getOrderDelivered())
                .map(order -> orderMapper.map(order))
                .toList();
    }

    @Transactional
    public List<OrderDTO> getOrdersByRestaurantId(Integer restaurantId) {
        return orderRepository.findAll().stream()
                .filter(order -> restaurantId.equals(order.getOrderRestaurantId()))
                .map(order -> orderMapper.map(order))
                .toList();
    }

    @Transactional
    public void addNewOrder(OrderDTO orderDTO) {
        OrderEntity newOrder = OrderEntity.builder()
                .orderNumber(orderDTO.getOrderNumber())
                .orderAddressId(orderDTO.getOrderAddressId())
                .orderDateTime(orderDTO.getOrderDateTime())
                .orderDelivered(false)
                .orderRestaurantId(orderDTO.getOrderRestaurantId())
                .user(userMapper.map(orderDTO.getUser()))
                .build();
        orderRepository.save(newOrder);
    }

    @Transactional
    public void updateOrder(OrderDTO orderDTO) {
        OrderEntity updatedOrder = OrderEntity.builder()
                .orderId(orderDTO.getOrderId())
                .orderNumber(orderDTO.getOrderNumber())
                .orderAddressId(orderDTO.getOrderAddressId())
                .orderDateTime(orderDTO.getOrderDateTime())
                .orderDelivered(orderDTO.getOrderDelivered())
                .orderDeliveredDateTime(orderDTO.getOrderDeliveredDateTime())
                .orderAddInfo(orderDTO.getOrderAddInfo())
                .orderRestaurantId(orderDTO.getOrderRestaurantId())
                .user(userMapper.map(orderDTO.getUser()))
                .build();
        orderRepository.save(updatedOrder);
    }

    @Transactional
    public void deleteOrder(OrderDTO orderDTO) {
        orderRepository.delete(orderMapper.map(getOrderByOrderNumber(orderDTO.getOrderNumber())));
    }
}
