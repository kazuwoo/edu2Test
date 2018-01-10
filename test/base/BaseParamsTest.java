package base;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BaseParamsTest {

	BaseParams params = null;

	@Before
	public void init() {
		params = new BaseParams();
	}

	@Test
	public void testSetGetTitle() {
		String title = "タイトル";
		params.setTitle(title);
		assertEquals("setTitle, getTitleのテスト", title, params.getTitle());
	}

	@Test
	public void testSetGetErrorMessage() {
		String errorMessage = "エラーメッセージ";
		params.setErrorMessage(errorMessage);
		assertEquals("setErrorMessage,getErrorMessageのテスト", errorMessage, params.getErrorMessage());
	}


	@Test
	public void testAddErrorMessage() {
		String errorMessage1 = "エラーメッセージ1";
		String errorMessage2 = "エラーメッセージ2";
		params.setErrorMessage("");
		params.addErrorMessage(errorMessage1);
		params.addErrorMessage(errorMessage2);
		assertEquals("addErrorMessageのテスト", errorMessage1 + "<br>" + errorMessage2, params.getErrorMessage());
	}

	@Test
	public void testHasError() {
		params.setErrorMessage("");
		assertEquals("エラーなし", false, params.hasError());
		params.addErrorMessage("エラーメッセージ");
		assertEquals("エラーあり", true, params.hasError());
	}

	@Test
	public void testSetGetOperation() {
		params.setOperation(Operation.doEncryption);
		assertEquals("setOperation,getOperationのテスト", Operation.doEncryption, params.getOperation());
	}

	@Test
	public void testIsOperationEncryption() {
		params.setOperation(Operation.doEncryption);
		assertEquals("doEncryption", true, params.isOperationEncryption());
		params.setOperation(Operation.doDecryption);
		assertEquals("doDecryption", false, params.isOperationEncryption());
		params.setOperation(Operation.doCryptanalysis);
		assertEquals("doCryptanalysis", false, params.isOperationEncryption());
		params.setOperation(Operation.unknown);
		assertEquals("unknown", true, params.isOperationEncryption());
		params.setOperation(null);
		assertEquals("null", true, params.isOperationEncryption());
	}

	@Test
	public void testIsOperationDecryption() {
		params.setOperation(Operation.doEncryption);
		assertEquals("doEncryption", false, params.isOperationDecryption());
		params.setOperation(Operation.doDecryption);
		assertEquals("doDecryption", true, params.isOperationDecryption());
		params.setOperation(Operation.doCryptanalysis);
		assertEquals("doCryptanalysis", false, params.isOperationDecryption());
		params.setOperation(Operation.unknown);
		assertEquals("unknown", false, params.isOperationDecryption());
		params.setOperation(null);
		assertEquals("null", false, params.isOperationDecryption());
	}

	@Test
	public void testIsOperationCryptanalysis() {
		params.setOperation(Operation.doEncryption);
		assertEquals("doEncryption", false, params.isOperationCryptanalysis());
		params.setOperation(Operation.doDecryption);
		assertEquals("doDecryption", false, params.isOperationCryptanalysis());
		params.setOperation(Operation.doCryptanalysis);
		assertEquals("doCryptanalysis", true, params.isOperationCryptanalysis());
		params.setOperation(Operation.unknown);
		assertEquals("unknown", false, params.isOperationCryptanalysis());
		params.setOperation(null);
		assertEquals("null", false, params.isOperationCryptanalysis());
	}

}
