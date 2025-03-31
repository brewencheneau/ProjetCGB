package cgb.transfert;

/**
 * Exception abstraite représentant une erreur liée à un IBAN invalide.
 */
public abstract class ExceptionInvalideIBAN extends Exception {
    
    public ExceptionInvalideIBAN(String message) {
        super(message);
    }
}
