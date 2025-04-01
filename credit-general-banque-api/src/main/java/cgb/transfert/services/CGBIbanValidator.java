package cgb.transfert.services;

import org.apache.commons.validator.routines.IBANValidator;

import cgb.transfert.exceptions.ExceptionInvalidIbanFormat;
import cgb.transfert.exceptions.ExceptionInvalidUnCheckableIban;

/**
 * Classe utilitaire pour la validation et l'extraction des informations d'un IBAN.
 * Implémentée selon le pattern Singleton.
 */
public class CGBIbanValidator {

    // Instance unique de la classe (Singleton)
    private static CGBIbanValidator instance;

    // Constructeur privé
    private CGBIbanValidator() {}

    /**
     * Retourne l'instance unique du validateur
     */
    public static CGBIbanValidator getInstanceValidator() {
        if (instance == null) {
            instance = new CGBIbanValidator();
        }
        return instance;
    }

    /**
     * Vérifie que la structure de l'IBAN est correcte :
     * - commence par 2 lettres (code pays)
     * - suivi de 2 chiffres (clé de contrôle)
     * - et le reste contient uniquement lettres/chiffres
     */
    public boolean isIbanStructureValide(String iban) throws ExceptionInvalidIbanFormat {
        if (iban == null || iban.length() < 15 || iban.length() > 34) {
            throw new ExceptionInvalidIbanFormat("Longueur IBAN invalide");
        }

        if (!iban.matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]+$")) {
            throw new ExceptionInvalidIbanFormat("Format IBAN invalide");
        }

        return true;
    }

    /**
     * Vérifie que l'IBAN est valide (avec la clé CRC)
     * en utilisant la bibliothèque Apache Commons Validator
     */
    public boolean isIbanValide(String iban) throws ExceptionInvalidUnCheckableIban {
        if (!IBANValidator.getInstance().isValid(iban)) {
            throw new ExceptionInvalidUnCheckableIban("IBAN bien formé mais CRC invalide !");
        }
        return true;
    }

    /**
     * Extrait le code pays d’un IBAN (2 premières lettres).
     *
     * @param iban l'IBAN à analyser
     * @return le code pays (ex: "FR", "DE", "GB")
     * @throws Exception si l’IBAN est trop court
     */
    public String getCodePays(String iban) throws Exception {
        if (iban == null || iban.length() < 2) {
            throw new Exception("IBAN trop court pour extraire le code pays");
        }
        return iban.substring(0, 2);
    }

    /**
     * Extrait la clé de contrôle d’un IBAN (2 chiffres après le code pays).
     *
     * @param iban l'IBAN à analyser
     * @return la clé de contrôle (ex: "76", "33")
     * @throws Exception si l’IBAN est trop court
     */
    public String getControlNumber(String iban) throws Exception {
        if (iban == null || iban.length() < 4) {
            throw new Exception("IBAN trop court pour extraire le code de contrôle");
        }
        return iban.substring(2, 4);
    }
    /**
     * Extrait le BBAN (Basic Bank Account Number) d’un IBAN.
     * Il s'agit de tout ce qui vient après les 4 premiers caractères (code pays + clé).
     *
     * @param iban l'IBAN à analyser
     * @return la partie BBAN
     * @throws Exception si l’IBAN est trop court
     */
    public String getBBAN(String iban) throws Exception {
        if (iban == null || iban.length() < 5) {
            throw new Exception("IBAN trop court pour extraire le BBAN");
        }
        return iban.substring(4);
    }
}
