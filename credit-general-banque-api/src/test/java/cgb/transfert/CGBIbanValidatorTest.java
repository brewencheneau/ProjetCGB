package cgb.transfert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
	public void testIban_Success() {
		assertTrue(CGBIbanValidator.getInstanceValidator().isIbanValide("GB33BUKB20201555555555"));
	}
	
	@Test
	public void testIban_Failure() {
		assertFalse(CGBIbanValidator.getInstanceValidator().isIbanValide("FRJESUISFAUX220245"));
	}
	
	@Test
	public void testIbanCodePays_Success() throws Exception {
		assertEquals("GB", CGBIbanValidator.getInstanceValidator().getCodePays("GB33BUKB20201555555555"));
	}
	
	@Test
	public void testIbanControlNumber_Success() throws Exception {
		assertEquals("33", CGBIbanValidator.getInstanceValidator().getControlNumber("GB33BUKB20201555555555"));
	}
	
}
