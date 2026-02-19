/**
 * 
 */
package ubu.gii.dass.c01;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Disabled;

/**
 * @author alumno
 *
 */
public class ReusablePoolTest {

	@BeforeAll
	public static void setUp() {
	}

	@AfterAll
	public static void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	@DisplayName("testGetInstance")

	public void testGetInstance() {
		ReusablePool pool1 = ReusablePool.getInstance();
		ReusablePool pool2 = ReusablePool.getInstance();

		assertTrue(pool1 == pool2, "getInstance should return same instance");
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	@DisplayName("testAcquireReusable")

	public void testAcquireReusable() throws Exception {
		ReusablePool pool = ReusablePool.getInstance();
		
		try {

			Reusable r1 = pool.acquireReusable();
			Reusable r2 = pool.acquireReusable();
			assertNotNull(r1);
			assertNotNull(r2);

			assertTrue(r1 instanceof Reusable);
			assertTrue(r2 instanceof Reusable);

			assertTrue(r1.util().contains("Using the reusable object"));

			Reusable r3 = pool.acquireReusable();
		} catch (NotFreeInstanceException e) {

			assertTrue(e instanceof NotFreeInstanceException);
		}

	}

	/**
	 * Test method for
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	@DisplayName("testReleaseReusable")

	public void testReleaseReusable() throws Exception {
		ReusablePool pool = ReusablePool.getInstance();

		Reusable r1 = pool.acquireReusable();
		pool.releaseReusable(r1);

		try {
			pool.releaseReusable(r1);
			assertTrue(false);
		} catch (DuplicatedInstanceException e) {
			assertTrue(true);
		}
	}

}
