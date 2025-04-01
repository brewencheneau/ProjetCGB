package cgb.transfert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cgb.transfert.entity.Account;
import cgb.transfert.repository.AccountRepository;
import cgb.transfert.services.IbanGenerator;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        // Si la base est vide, on génère 20 comptes avec IBAN valides
        if (accountRepository.count() == 0) {
            insertSampleData(accountRepository);
        }
    }

    public static void insertSampleData(AccountRepository accountRepository) {
        for (int i = 0; i < 20; i++) {
            Account account = new Account();
            account.setAccountNumber(IbanGenerator.generateValidIban());
            account.setSolde(1000.0 + (i * 100)); // Exemple : 1000€, 1100€, etc.
            accountRepository.save(account);
        }

        System.out.println("✅ 20 comptes avec IBAN valides ont été ajoutés à la base !");
    }
}
