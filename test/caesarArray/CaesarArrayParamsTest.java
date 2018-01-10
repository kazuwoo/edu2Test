package caesarArray;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CaesarArrayParamsTest {

	CaesarArrayParams params = null;

	@Before
	public void init() {
		params = new CaesarArrayParams();
	}

	@Test
	public void testSetGetShift() {
		String shift = "シフト数";
		params.setShift(shift);
		assertEquals("setShift,getShiftのテスト", shift, params.getShift());
	}

	@Test
	public void testSetGetTarget() {
		String target = "入力";
		params.setTarget(target);
		assertEquals("setTarget,getTargetのテスト", target, params.getTarget());
	}

	@Test
	public void testSetGetMessage() {
		String message = "出力";
		params.setMessage(message);
		assertEquals("setMessage,getMessageのテスト", message, params.getMessage());
	}

}
