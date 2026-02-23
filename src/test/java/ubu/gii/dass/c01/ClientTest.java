package ubu.gii.dass.c01;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Diego Andaluz Arnanz
 * @author Rodrigo Rodríguez Fernández
 * @author Gadea Díez Prieto
 */
public class ClientTest {
	
	@Test
	@DisplayName("testClient")
	public void testClient() {
		Client c = new Client();
		assertNotNull(c);
		assert(c instanceof Client);
	}

}
