package cgb.transfert.dto; 

import java.util.List;

public class LotRequest {

    private String sourceAccountNumber;
    private List<VirementRequest> transfers;

    // Getters & Setters

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public List<VirementRequest> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<VirementRequest> transfers) {
        this.transfers = transfers;
    }
}

