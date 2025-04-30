package cgb.transfert.services;

import cgb.transfert.dto.LotRequest;
import cgb.transfert.dto.VirementRequest;
import cgb.transfert.entity.Account;
import cgb.transfert.entity.Lot;
import cgb.transfert.entity.Transfer;
import cgb.transfert.repository.AccountRepository;
import cgb.transfert.repository.LotRepository;
import cgb.transfert.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LotService {

	@Autowired
	private LotRepository lotRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferRepository transferRepository;

    public Lot createLot(LotRequest request) {
        Lot lot = new Lot();
        lot.setDateCreation(LocalDate.now());
        lot.setSourceAccountNumber(request.getSourceAccountNumber());
        lot.setEtat("EnCours");

        List<Transfer> transfertsReussis = new ArrayList<>();

        // Cherche le compte source
        Account sourceAccount = accountRepository.findById(request.getSourceAccountNumber())
                .orElseThrow(() -> new RuntimeException("Compte source introuvable"));

        for (VirementRequest v : request.getTransfers()) {
            try {
                // Vérifie le compte destination
                Account destinationAccount = accountRepository.findById(v.getDestinationAccountNumber())
                        .orElseThrow(() -> new RuntimeException("Compte destinataire introuvable : " + v.getDestinationAccountNumber()));

                // Vérifie le solde
                if (sourceAccount.getSolde() >= v.getAmount()) {
                    sourceAccount.setSolde(sourceAccount.getSolde() - v.getAmount());
                    destinationAccount.setSolde(destinationAccount.getSolde() + v.getAmount());

                    accountRepository.save(sourceAccount);
                    accountRepository.save(destinationAccount);

                    // Crée le transfert
                    Transfer t = new Transfer();
                    t.setSourceAccountNumber(sourceAccount.getAccountNumber());
                    t.setDestinationAccountNumber(destinationAccount.getAccountNumber());
                    t.setAmount(v.getAmount());
                    t.setDescription(v.getDescription());
                    t.setTransferDate(LocalDate.now());

                    transferRepository.save(t);
                    transfertsReussis.add(t);
                } else {
                    // Pas assez d'argent pour ce virement
                    System.out.println("Fonds insuffisants pour le virement vers " + v.getDestinationAccountNumber());
                }
            } catch (Exception e) {
                System.out.println("Erreur lors du traitement d’un virement : " + e.getMessage());
            }
        }

        // État du lot final
        if (transfertsReussis.isEmpty()) {
            lot.setEtat("Echec");
        } else if (transfertsReussis.size() < request.getTransfers().size()) {
            lot.setEtat("Partiel");
        } else {
            lot.setEtat("Succès");
        }

        lot.setTransfers(transfertsReussis);

        return lotRepository.save(lot);

    }
}