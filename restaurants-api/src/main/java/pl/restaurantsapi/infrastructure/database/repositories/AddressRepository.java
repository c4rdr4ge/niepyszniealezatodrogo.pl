package pl.restaurantsapi.infrastructure.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.restaurantsapi.infrastructure.database.entities.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
