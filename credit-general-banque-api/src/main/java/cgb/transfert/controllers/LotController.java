package cgb.transfert.controllers;


import cgb.transfert.dto.LotRequest;
import cgb.transfert.dto.LotResponse;
import cgb.transfert.entity.Lot;
import cgb.transfert.services.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lots")
public class LotController {

    @Autowired
    private LotService lotService;

    @PostMapping
    public ResponseEntity<?> createLot(@RequestBody LotRequest request) {
        Lot lot = lotService.createLot(request);
        return ResponseEntity.ok().body(
            new LotResponse("Lot de virements en cours de traitement", lot.getId(), lot.getDateCreation())
        );
    }
}
