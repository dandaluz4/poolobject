package ubu.gii.dass.c01;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
/**
 * @author Diego Andaluz Arnanz
 * @author Rodrigo Rodríguez Fernández
 * @author Gadea Díez Prieto
 */
public class ClientTest {

    @AfterEach
    void resetSingleton() throws Exception {
        Field instance = ReusablePool.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    @DisplayName("testClient")
    public void testClient() {
        assertDoesNotThrow(() -> {
            Client.main(new String[]{});
        });
    }
	
    @Test
    @DisplayName("testClientThrowsException")
    public void testClientThrowsException() throws Exception {
        ReusablePool pool = ReusablePool.getInstance();
        pool.acquireReusable();
        pool.acquireReusable();

        assertThrows(NotFreeInstanceException.class, () -> {
            Client.main(new String[]{});
        });
    }

    @Test
    @DisplayName("testClientConstructor")
    public void testClientConstructor() {
        Client c = new Client();
        assertNotNull(c);
        assert(c instanceof Client);
    }

}
