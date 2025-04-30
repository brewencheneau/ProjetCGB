package cgb.transfert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cgb.transfert.entity.Lot;

public interface LotRepository extends JpaRepository<Lot, Long> {
}
