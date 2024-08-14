package ir.javapro.seesion3.repository;

import ir.javapro.seesion3.model.ShoppingCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCardRepository extends JpaRepository<ShoppingCard,Long> {
}
