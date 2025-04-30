package cgb.transfert.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import cgb.transfert.entity.Transfer;

@Entity
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCreation;

    private String etat; // EnCours, Succès, Echec, etc.

    private String sourceAccountNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "lot_id") // permet de relier chaque virement au lot
    private List<Transfer> transfers;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }
}
