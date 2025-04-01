package cgb.transfert;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import cgb.transfert.entity.Transfer;

@SpringBootTest
@AutoConfigureMockMvc

//@WebMvcTest(TransferController.class)
@WithMockUser(username = "user")
public class TransferControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// Test for successful transfer creation
	@Test

	public void createTransferTest_Success() throws Exception {
		Transfer transfer = new Transfer();
		transfer.setAmount(10.0);
		transfer.setDescription("Test du transfer");
		transfer.setDestinationAccountNumber("123456789");
		transfer.setSourceAccountNumber("987654321");
		transfer.setTransferDate(LocalDate.parse("2018-12-06"));
		mockMvc.perform(post("/api/transfers").contentType(MediaType.APPLICATION_JSON).content(asJsonString(transfer)))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}

	@Test
	public void createTransferTest_Failure() throws Exception {
		Transfer transfer = new Transfer();
		transfer.setAmount(40000.0);
		transfer.setDescription("Test du transfer");
		transfer.setDestinationAccountNumber("123456789");
		transfer.setSourceAccountNumber("987654321");
		transfer.setTransferDate(LocalDate.parse("2018-12-06"));
		mockMvc.perform(post("/api/transfers").content(asJsonString(transfer)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	@Test
	public void deleteTransferTest_Failure() throws Exception {
		String id="1";
		mockMvc.perform(delete("/api/transfers/1"))
	       .andExpect(status().isNotFound()); // ou autre selon ton comportement
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().findAndRegisterModules().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	public void createTransferTest_InvalidIban() throws Exception {
	    Transfer transfer = new Transfer();
	    transfer.setAmount(50.0);
	    transfer.setDescription("IBAN faux");
	    transfer.setDestinationAccountNumber("FAUXIBAN123");
	    transfer.setSourceAccountNumber("987654321");
	    transfer.setTransferDate(LocalDate.now());

	    mockMvc.perform(post("/api/transfers")
	            .content(asJsonString(transfer))
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isBadRequest());
	}
}