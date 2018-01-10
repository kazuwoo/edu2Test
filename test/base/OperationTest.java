package base;

import static org.junit.Assert.*;

import org.junit.Test;

public class OperationTest {

	@Test
	public void testDefaultValueOf() {
		assertEquals("doEncryption", Operation.doEncryption, Operation.defaultValueOf("doEncryption"));
		assertEquals("doDecryption", Operation.doDecryption, Operation.defaultValueOf("doDecryption"));
		assertEquals("doCryptanalysis", Operation.doCryptanalysis, Operation.defaultValueOf("doCryptanalysis"));
		assertEquals("unknown", Operation.unknown, Operation.defaultValueOf("unknown"));
		assertEquals("空文字", Operation.unknown, Operation.defaultValueOf(""));
		assertEquals("null", Operation.unknown, Operation.defaultValueOf(null));
	}

}
