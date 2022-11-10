package marketit.ordersystem.domain.repository;

import marketit.ordersystem.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
