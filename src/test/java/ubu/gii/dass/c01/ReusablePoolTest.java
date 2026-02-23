/**
 * 
 */
package ubu.gii.dass.c01;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * @author Diego Andaluz Arnanz
 * @author Rodrigo Rodríguez Fernández
 * @author Gadea Díez Prieto
 */
public class ReusablePoolTest {
	
	private static ReusablePool pool;

	@BeforeAll
	public static void setUp() {
		pool = ReusablePool.getInstance();
	}

	@AfterAll
	public static void tearDown() throws Exception {
		// Liberamos posibles instancias ocupadas
        try {
            while (true) {
                pool.acquireReusable();
            }
        } catch (Exception ignored) {
        }
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	@DisplayName("testGetInstance")

	public void testGetInstance() {
		assertNotNull(pool);

        ReusablePool pool2 = ReusablePool.getInstance();

        assertNotNull(pool2);
        
        assertTrue(pool == pool2, "getInstance should return same instance");
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	@DisplayName("testAcquireReusable")

	public void testAcquireReusable() throws Exception {
		Reusable r1 = pool.acquireReusable();
		Reusable r2 = pool.acquireReusable();

		assertNotNull(r1);
		assertNotNull(r2);
		assertTrue(r1 != r2);
		
		pool.releaseReusable(r1);
		pool.releaseReusable(r2);
		
	}

	/**
	 * Test method for
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	@DisplayName("testReleaseReusable")

	public void testReleaseReusable() throws Exception {
		
		Reusable r1 = pool.acquireReusable();
		pool.releaseReusable(r1);

		assertThrows(DuplicatedInstanceException.class, () -> {
			pool.releaseReusable(r1);
		});
	}
	
	@Test
	@DisplayName("testAcquireReusableThrowsNotFreeInstanceException")
	public void testAcquireReusableThrowsNotFreeInstanceException() throws Exception {
		
		Reusable r1 = pool.acquireReusable();
		Reusable r2 = pool.acquireReusable();

		assertThrows(NotFreeInstanceException.class, () -> {
			pool.acquireReusable();
		});

		pool.releaseReusable(r1);
		pool.releaseReusable(r2);
	}
	
}
