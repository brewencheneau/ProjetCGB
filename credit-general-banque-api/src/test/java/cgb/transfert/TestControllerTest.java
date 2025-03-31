package cgb.transfert;

import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
@WebMvcTest(TestController.class)
@WithMockUser(username = "user")
public class TestControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test

	public void testVideTest() throws Exception {
		mockMvc.perform(get("/test/")).andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string("Racine sous test "));
	}

	@Test

	public void obtenirTacheTest() throws Exception {
		int id = 1;
		mockMvc.perform(get("/test/{id}", id)).andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8")).andExpect(content().string("Recu : 1"));
	}

	@Test

	public void obtenirTacheTest2() throws Exception {
		int id = 2;
		mockMvc.perform(get("/test/{id}", id)).andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8")).andExpect(content().string("Recu : 2"));
	}

	@Test
	void contextLoads() {
	}
}
