package cgb.transfert;

import cgb.transfert.exceptions.ExceptionInvalidIbanFormat;
import cgb.transfert.exceptions.ExceptionInvalidUnCheckableIban;
import cgb.transfert.services.CGBIbanValidator;
import cgb.transfert.services.IbanGenerator;

public class IbanTestRunner {
    public static void main(String[] args) {
        try {
            String generatedIban = IbanGenerator.generateValidIban();
            System.out.println("IBAN généré : " + generatedIban);

            CGBIbanValidator validator = CGBIbanValidator.getInstanceValidator();

            // Vérifie la structure
            if (validator.isIbanStructureValide(generatedIban)) {
                System.out.println("✅ Structure IBAN valide");
            }

            // Vérifie la validité CRC
            if (validator.isIbanValide(generatedIban)) {
                System.out.println("✅ CRC IBAN valide");
            } else {
                throw new ExceptionInvalidUnCheckableIban("❌ CRC invalide pour : " + generatedIban);
            }

        } catch (ExceptionInvalidIbanFormat e) {
            System.out.println("❌ Erreur de format : " + e.getMessage());
        } catch (ExceptionInvalidUnCheckableIban e) {
            System.out.println("❌ Erreur CRC : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Autre erreur : " + e.getMessage());
        }
    }
}