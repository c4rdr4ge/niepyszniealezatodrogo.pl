package pl.restaurantsapi.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.restaurantsapi.buisness.dto.AvailableAddressDTO;
import pl.restaurantsapi.infrastructure.database.entities.AvailableAddressEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AvailableAddressMapper {

    public AvailableAddressDTO map(AvailableAddressEntity availableAddressEntity);

    public AvailableAddressEntity map(AvailableAddressDTO availableAddressDTO);
}
