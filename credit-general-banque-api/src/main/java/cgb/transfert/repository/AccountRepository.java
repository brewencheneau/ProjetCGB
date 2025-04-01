package cgb.transfert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cgb.transfert.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}

