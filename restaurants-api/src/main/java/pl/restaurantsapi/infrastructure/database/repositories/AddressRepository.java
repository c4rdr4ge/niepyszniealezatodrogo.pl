package pl.restaurantsapi.infrastructure.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.restaurantsapi.buisness.dto.AddressDTO;
import pl.restaurantsapi.infrastructure.database.entities.AddressEntity;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
    Optional<AddressEntity> findByAddressStreet(String addressStreet);
}
