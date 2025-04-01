package cgb.transfert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cgb.transfert.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}