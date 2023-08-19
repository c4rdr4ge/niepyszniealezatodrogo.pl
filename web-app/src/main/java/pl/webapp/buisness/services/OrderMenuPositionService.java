package pl.webapp.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webapp.buisness.dto.OrderMenuPositionDTO;
import pl.webapp.buisness.dto.mappers.OrderMapper;
import pl.webapp.buisness.dto.mappers.OrderMenuPositionMapper;
import pl.webapp.infrastructure.database.entity.OrderMenuPositionEntity;
import pl.webapp.infrastructure.database.repositories.OrderMenuPositionRepository;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderMenuPositionService {

    OrderMenuPositionRepository orderMenuPositionRepository;
    OrderMenuPositionMapper orderMenuPositionMapper;

    OrderMapper orderMapper;

    @Transactional
    public List<OrderMenuPositionDTO> getAllOrderMenuPosition(){
        return orderMenuPositionRepository.findAll().stream()
                .map(orderMenuPosition -> orderMenuPositionMapper.map(orderMenuPosition))
                .toList();
    }

    @Transactional
    public OrderMenuPositionDTO getOrderMenuPositionByMenuPositionId(Integer menuPositionId) {
        return orderMenuPositionRepository.findAll().stream()
                .filter(orderMenuPosition -> menuPositionId.equals(orderMenuPosition.getMenuPositionId()))
                .map(orderMenuPosition -> orderMenuPositionMapper.map(orderMenuPosition))
                .findAny().orElseThrow(() -> new EntityNotFoundException("OrderMenuPosition with menuPositionId: [%s], not found.".formatted(menuPositionId)));
    }

    @Transactional
    public void addOrderMenuPosition(OrderMenuPositionDTO orderMenuPositionDTO) {
        OrderMenuPositionEntity newOrderMenuPosition = OrderMenuPositionEntity.builder()
                .menuPositionId(orderMenuPositionDTO.getMenuPositionId())
                .order(orderMapper.map(orderMenuPositionDTO.getOrder()))
                .build();
        orderMenuPositionRepository.save(newOrderMenuPosition);
    }
}
