package pl.restaurantsapi.buisness.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.restaurantsapi.buisness.dto.AddressDTO;
import pl.restaurantsapi.infrastructure.database.entities.AddressEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    public AddressDTO map(AddressEntity addressEntity);

    public AddressEntity map(AddressDTO addressDTO);
}
