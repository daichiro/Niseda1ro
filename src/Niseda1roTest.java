import static org.junit.Assert.*;

import org.junit.Test;


public class Niseda1roTest {
	@Test
	public void test() {
		Niseda1ro niseda1ro = new Niseda1ro();
		assertEquals(niseda1ro.selectReadOneLine("src/txt/statusesMock.txt"), "ほげほげ");
	}

}
