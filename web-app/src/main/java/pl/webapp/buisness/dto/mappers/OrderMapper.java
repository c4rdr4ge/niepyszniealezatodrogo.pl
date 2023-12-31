package pl.webapp.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.webapp.buisness.dto.OrderDTO;
import pl.webapp.infrastructure.database.entity.OrderEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    OrderDTO map(OrderEntity orderEntity);

    OrderEntity map(OrderDTO orderDTO);
}
