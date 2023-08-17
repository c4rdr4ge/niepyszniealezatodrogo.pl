package pl.webapp.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.webapp.buisness.dto.OrderHistoryDTO;
import pl.webapp.infrastructure.database.entity.OrderHistoryEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderHistoryMapper {

    public OrderHistoryDTO map(OrderHistoryEntity orderHistoryEntity);

    public OrderHistoryEntity map(OrderHistoryDTO orderHistoryDTO);
}
