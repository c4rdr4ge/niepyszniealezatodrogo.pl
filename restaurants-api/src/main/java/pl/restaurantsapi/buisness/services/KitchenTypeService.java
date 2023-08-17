package pl.restaurantsapi.buisness.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.restaurantsapi.buisness.dto.KitchenTypeDTO;
import pl.restaurantsapi.buisness.dto.mappers.KitchenTypeMapper;
import pl.restaurantsapi.infrastructure.database.entities.KitchenTypeEntity;
import pl.restaurantsapi.infrastructure.database.repositories.KitchenTypeRepository;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class KitchenTypeService {

    KitchenTypeRepository kitchenTypeRepository;
    KitchenTypeMapper kitchenTypeMapper;

    @Transactional
    public KitchenTypeDTO getKitchenTypeById(Integer kitchenTypeId) {
        Optional<KitchenTypeEntity> kitchenTypeEntity = kitchenTypeRepository.findById(kitchenTypeId);
        if(kitchenTypeEntity.isPresent()) {
            return kitchenTypeMapper.map(kitchenTypeEntity.get());
        }else {
            throw new EntityNotFoundException("KitchenType with kitchenTypeId: [%s], not found".formatted(kitchenTypeId));
        }
    }

    @Transactional
    public void addNewKitchenType(KitchenTypeDTO kitchenTypeDTO) {
        KitchenTypeEntity kitchenTypeEntity = KitchenTypeEntity.builder()
                .kitchenTypeName(kitchenTypeDTO.getKitchenTypeName())
                .build();
    }
}
