package pl.webapp.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.webapp.buisness.dto.OrderMenuPositionDTO;
import pl.webapp.infrastructure.database.entity.OrderMenuPositionEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMenuPositionMapper {

    public OrderMenuPositionDTO map(OrderMenuPositionEntity orderMenuPositionEntity);

    public OrderMenuPositionEntity map(OrderMenuPositionDTO orderMenuPositionDTO);
}
