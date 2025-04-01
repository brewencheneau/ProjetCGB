package cgb.transfert.services;

import java.util.Random;
import java.math.BigInteger;

public class IbanGenerator {

    private static final String COUNTRY_CODE = "FR";
    private static final int IBAN_LENGTH = 27; // Longueur totale d'un IBAN français
    private static final Random RANDOM = new Random();

    /**
     * Génère un IBAN fictif valide pour la France.
     *
     * @return Un IBAN fictif valide.
     */
    public static String generateValidIban() {
        // Générer un BBAN fictif (sans les chiffres de contrôle)
        StringBuilder bban = new StringBuilder(IBAN_LENGTH - 4); // 4 = 2 lettres pays + 2 chiffres de contrôle
        for (int i = 0; i < bban.capacity(); i++) {
            bban.append(RANDOM.nextInt(10)); // Utiliser des chiffres pour simplifier car on peut aussi utiliser des lettres.
        }

        // Calculer les chiffres de contrôle
        String checkDigits = calculateCheckDigits(COUNTRY_CODE, bban.toString());

        // Assembler l'IBAN complet
        return COUNTRY_CODE + checkDigits + bban.toString();
    }

    /**
     * Calcule les chiffres de contrôle pour un IBAN donné.
     *
     * @param countryCode Le code du pays.
     * @param bban Le BBAN (sans les chiffres de contrôle).
     * @return Les chiffres de contrôle calculés.
     */
    private static String calculateCheckDigits(String countryCode, String bban) {
        String rearranged = bban + countryCode + "00";

        // Conversion lettres → chiffres (A = 10 ... Z = 35)
        StringBuilder numericIban = new StringBuilder();
        for (char c : rearranged.toCharArray()) {
            if (Character.isLetter(c)) {
                numericIban.append((int) c - 55); // 'A' = 65 → 10
            } else {
                numericIban.append(c);
            }
        }

        // Calcul du modulo 97
        String numericStr = numericIban.toString();
        int mod = mod97(numericStr);
        int checkDigits = 98 - mod;

        return String.format("%02d", checkDigits);
    }


    private static int mod97(String numericIban) {
        BigInteger bigInt = new BigInteger(numericIban);
        return bigInt.mod(BigInteger.valueOf(97)).intValue();
    }



    public static void main(String[] args) {
        String validIban = generateValidIban();
        System.out.println("Generated Valid IBAN: " + validIban);
    }
}
