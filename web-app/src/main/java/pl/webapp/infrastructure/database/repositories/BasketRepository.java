package pl.webapp.infrastructure.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.webapp.infrastructure.database.entity.BasketEntity;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, Integer> {
}
