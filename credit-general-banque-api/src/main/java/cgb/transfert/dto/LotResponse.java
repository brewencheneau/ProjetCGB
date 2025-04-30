package cgb.transfert.dto;

import java.time.LocalDate;

public class LotResponse {

    private String message;
    private Long lotId;
    private LocalDate dateCreation;

    public LotResponse(String message, Long lotId, LocalDate dateCreation) {
        this.message = message;
        this.lotId = lotId;
        this.dateCreation = dateCreation;
    }

    public String getMessage() {
        return message;
    }

    public Long getLotId() {
        return lotId;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }
}
