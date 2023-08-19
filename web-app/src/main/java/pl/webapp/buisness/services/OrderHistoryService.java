package pl.webapp.buisness.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.buisness.dto.OrderHistoryDTO;
import pl.webapp.buisness.dto.mappers.OrderHistoryMapper;
import pl.webapp.buisness.dto.mappers.OrderMapper;
import pl.webapp.buisness.dto.mappers.UserMapper;
import pl.webapp.infrastructure.database.entity.OrderHistoryEntity;
import pl.webapp.infrastructure.database.repositories.OrderHistoryRepository;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderHistoryService {

    OrderHistoryRepository orderHistoryRepository;
    OrderHistoryMapper orderHistoryMapper;

    OrderMapper orderMapper;
    UserMapper userMapper;

    @Transactional
    public List<OrderHistoryDTO> getAllOrderHistory(){
        return orderHistoryRepository.findAll().stream()
                .map(orderHistory -> orderHistoryMapper.map(orderHistory))
                .toList();
    }

    @Transactional
    public List<OrderHistoryDTO> getOrderHistoryByUserName(String username) {
        return orderHistoryRepository.findAll().stream()
                .filter(orderHistory -> username.equals(orderHistory.getUser().getUserName()))
                .map(order -> orderHistoryMapper.map(order))
                .toList();
    }

    @Transactional
    public void addNewOrderHistory(OrderHistoryDTO orderHistoryDTO) {
        OrderHistoryEntity newOrderHistory = OrderHistoryEntity.builder()
                .order(orderMapper.map(orderHistoryDTO.getOrder()))
                .user(userMapper.map(orderHistoryDTO.getUser()))
                .build();
        orderHistoryRepository.save(newOrderHistory);
    }
}
