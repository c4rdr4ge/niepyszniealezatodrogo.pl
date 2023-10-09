package pl.webapp.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.webapp.buisness.dto.OrderHistoryDTO;
import pl.webapp.infrastructure.database.entity.OrderHistoryEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderHistoryMapper {

    OrderHistoryDTO map(OrderHistoryEntity orderHistoryEntity);

    OrderHistoryEntity map(OrderHistoryDTO orderHistoryDTO);
}
