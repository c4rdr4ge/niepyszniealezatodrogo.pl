package pl.restaurantsapi.infrastructure.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.restaurantsapi.infrastructure.database.entities.AvailableAddressEntity;

import java.util.Optional;

@Repository
public interface AvailableAddressRepository extends JpaRepository<AvailableAddressEntity, Integer> {

}
