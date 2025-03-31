package cgb.transfert;

/**
 * Exception levée quand l’IBAN est bien formé mais que le CRC est invalide ou non vérifiable.
 */
public class ExceptionInvalidUnCheckableIban extends ExceptionInvalideIBAN {

    public ExceptionInvalidUnCheckableIban(String message) {
        super(message);
    }
}
