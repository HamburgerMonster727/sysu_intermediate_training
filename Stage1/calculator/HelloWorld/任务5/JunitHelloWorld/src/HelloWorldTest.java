import static org.junit.Assert.*;

import org.junit.Test;

public class HelloWorldTest {
	@Test
	public void Test() {
		HelloWorld helloworld = new HelloWorld();
		assertEquals("Hello World!", helloworld.get());
	}
}
