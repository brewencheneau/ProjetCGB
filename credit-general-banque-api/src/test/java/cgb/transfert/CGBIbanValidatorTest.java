package cgb.transfert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import cgb.transfert.exceptions.ExceptionInvalidIbanFormat;
import cgb.transfert.exceptions.ExceptionInvalidUnCheckableIban;
import cgb.transfert.services.CGBIbanValidator;

@SpringBootTest
public class CGBIbanValidatorTest {

	@Test
	public void testIbanStructure_Success() throws ExceptionInvalidIbanFormat {
		assertTrue(CGBIbanValidator.getInstanceValidator().isIbanStructureValide("FR15325582615969757086959276"));
	}
	
	@Test
	public void testIbanStructure_Failure() {
	    assertThrows(ExceptionInvalidIbanFormat.class, () -> {
	        CGBIbanValidator.getInstanceValidator().isIbanStructureValide("FRJESUISFAUX220245");
	    });
	}
	
	@Test
	public void testIban_Success() throws Exception{
		assertTrue(CGBIbanValidator.getInstanceValidator().isIbanValide("GB33BUKB20201555555555"));
	}
	
	@Test
	public void testIban_Failure() {
	    String iban = "FRJESUISFAUX220245";

	    assertThrows(ExceptionInvalidUnCheckableIban.class, () -> {
	        CGBIbanValidator.getInstanceValidator().isIbanValide(iban);
	    });
	}

	
	@Test
	public void testIbanCodePays_Success() throws Exception {
		assertEquals("GB", CGBIbanValidator.getInstanceValidator().getCodePays("GB33BUKB20201555555555"));
	}
	
	@Test
	public void testIbanControlNumber_Success() throws Exception {
		assertEquals("33", CGBIbanValidator.getInstanceValidator().getControlNumber("GB33BUKB20201555555555"));
	}
	 @Test
	    public void testIban_Valide_Complet() throws Exception {
	        String iban = "GB33BUKB20201555555555"; // IBAN correct

	        assertTrue(CGBIbanValidator.getInstanceValidator().isIbanStructureValide(iban));
	        assertTrue(CGBIbanValidator.getInstanceValidator().isIbanValide(iban));
	    }

	 @Test
	 public void testIban_LongueurInvalide() {
	     String tooShort = "FR12";
	     assertThrows(ExceptionInvalidIbanFormat.class, () -> {
	         CGBIbanValidator.getInstanceValidator().isIbanStructureValide(tooShort);
	     });
	 }

	 @Test
	 public void testIban_RegexInvalide() {
	     String badSyntax = "FR00####INVALID";
	     assertThrows(ExceptionInvalidIbanFormat.class, () -> {
	         CGBIbanValidator.getInstanceValidator().isIbanStructureValide(badSyntax);
	     });
	 }

	    @Test 
	    public void testIban_CRCInvalide() throws Exception {
	        String iban = "FR7600001007941234567890185";

	        // Structure correcte
	        assertTrue(CGBIbanValidator.getInstanceValidator().isIbanStructureValide(iban));

	        // On s'attend à une ExceptionInvalidUnCheckableIban
	        assertThrows(
	            ExceptionInvalidUnCheckableIban.class,
	            () -> CGBIbanValidator.getInstanceValidator().isIbanValide(iban),
	            "Une exception doit être levée pour CRC invalide"
	        );
}
	    @Test
	    public void testIban_Extraction() throws Exception {
	        String iban = "FR7630006000011234567890189";

	        CGBIbanValidator validator = CGBIbanValidator.getInstanceValidator();

	        assertEquals("FR", validator.getCodePays(iban));
	        assertEquals("76", validator.getControlNumber(iban));
	    }	
}