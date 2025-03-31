package cgb.transfert;

public class ExceptionInvalidIbanFormat extends Exception {
    public ExceptionInvalidIbanFormat(String message) {
        super(message); // Appelle le constructeur de Exception
    }
}
