package caesarArray;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import base.Operation;

public class CaesarArrayServletTest {

	HttpServletRequest request = null;
	HttpServletResponse response = null;
	CaesarArrayServlet servlet = null;
	CaesarArrayParams params = null;

	@Before
	public void init() {
		try {
			// request responseをmock化
			request = mock(HttpServletRequest.class);
			response = mock(HttpServletResponse.class);
			// requestDispatcherをmock化
			RequestDispatcher rd = mock(RequestDispatcher.class);
			// forwardが呼ばれたらnothingを返すように設定（whenはvoidメソッドには使えない）
			doNothing().when(rd).forward(request, response);
			// getRequestDispatcherがよばれたらmockを返すように設定
			when(request.getRequestDispatcher(anyString())).thenReturn(rd);
			Answer<Object> answer = new Answer<Object>(){
				@Override
				public String answer(InvocationOnMock invocation) throws Throwable {
					Object args[] = invocation.getArguments();
					params = (CaesarArrayParams)args[1];
					return null;
				}
			};
			doAnswer(answer).when(request).setAttribute(eq("params"), anyObject());

			servlet = new CaesarArrayServlet();
		} catch (ServletException e) {
			fail("initで例外発生（ServletException）");
		} catch (IOException e) {
			fail("initのテストで例外発生（IOException）");
		}
	}

	@Test
	public void testDoPost() {
		try {
			// 初期表示のテスト
			when(request.getParameter("operation")).thenReturn(null);
			when(request.getParameter("target")).thenReturn(null);
			when(request.getParameter("shift")).thenReturn(null);
			servlet.doPost(request, response);
			assertEquals("初期表示のテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
			assertEquals("初期表示のテスト(エラーメッセージ)", "", params.getErrorMessage());
			assertEquals("初期表示のテスト(シフト数)", "", params.getShift());
			assertEquals("初期表示のテスト(入力)", "", params.getMessage());
			assertEquals("初期表示のテスト(出力)", "", params.getTarget());
			assertEquals("初期表示のテスト(操作)", Operation.unknown, params.getOperation());

			// 暗号化時入力エラーのテスト
			when(request.getParameter("operation")).thenReturn("doEncryption");
			when(request.getParameter("target")).thenReturn(null);
			when(request.getParameter("shift")).thenReturn(null);
			servlet.doPost(request, response);
			assertEquals("暗号化時入力エラーのテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
			assertEquals("暗号化時入力エラーのテスト(エラーメッセージ)", "[入力文]を入力してください。<br>[シフト数]を入力してください。", params.getErrorMessage());
			assertEquals("暗号化時入力エラーのテスト(シフト数)", "", params.getShift());
			assertEquals("暗号化時入力エラーのテスト(入力)", "", params.getTarget());
			assertEquals("暗号化時入力エラーのテスト(出力)", "", params.getMessage());
			assertEquals("暗号化時入力エラーのテスト(操作)", Operation.doEncryption, params.getOperation());

			// 暗号化時のテスト
			when(request.getParameter("operation")).thenReturn("doEncryption");
			when(request.getParameter("target")).thenReturn("abcdefghijklmnopqrstuvwxyz0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n");
			when(request.getParameter("shift")).thenReturn("1");
			servlet.doPost(request, response);
			assertEquals("暗号化時のテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
			assertEquals("暗号化時のテスト(エラーメッセージ)", "", params.getErrorMessage());
			assertEquals("暗号化時のテスト(シフト数)", "1", params.getShift());
			assertEquals("暗号化時のテスト(入力)", "abcdefghijklmnopqrstuvwxyz0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n", params.getTarget());
			assertEquals("暗号化時のテスト(出力)", "BCDEFGHIJKLMNOPQRSTUVWXYZA0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n", params.getMessage());
			assertEquals("暗号化時のテスト(操作)", Operation.doEncryption, params.getOperation());

			// 復号化時入力エラーのテスト
			when(request.getParameter("operation")).thenReturn("doDecryption");
			when(request.getParameter("target")).thenReturn(null);
			when(request.getParameter("shift")).thenReturn(null);
			servlet.doPost(request, response);
			assertEquals("復号化時入力エラーのテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
			assertEquals("復号化時入力エラーのテスト(エラーメッセージ)", "[入力文]を入力してください。<br>[シフト数]を入力してください。", params.getErrorMessage());
			assertEquals("復号化時入力エラーのテスト(シフト数)", "", params.getShift());
			assertEquals("復号化時入力エラーのテスト(入力)", "", params.getTarget());
			assertEquals("復号化時入力エラーのテスト(出力)", "", params.getMessage());
			assertEquals("復号化時入力エラーのテスト(操作)", Operation.doDecryption, params.getOperation());

			// 復号化時のテスト
			when(request.getParameter("operation")).thenReturn("doDecryption");
			when(request.getParameter("target")).thenReturn("BCDEFGHIJKLMNOPQRSTUVWXYZA0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n");
			when(request.getParameter("shift")).thenReturn("1");
			servlet.doPost(request, response);
			assertEquals("復号化時のテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
			assertEquals("復号化時のテスト(エラーメッセージ)", "", params.getErrorMessage());
			assertEquals("復号化時のテスト(シフト数)", "1", params.getShift());
			assertEquals("復号化時のテスト(入力)", "BCDEFGHIJKLMNOPQRSTUVWXYZA0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n", params.getTarget());
			assertEquals("復号化時のテスト(出力)", "abcdefghijklmnopqrstuvwxyz0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n", params.getMessage());
			assertEquals("復号化時のテスト(操作)", Operation.doDecryption, params.getOperation());

			// 暗号解読時入力エラーのテスト
			when(request.getParameter("operation")).thenReturn("doCryptanalysis");
			when(request.getParameter("target")).thenReturn(null);
			when(request.getParameter("shift")).thenReturn(null);
			servlet.doPost(request, response);
			assertEquals("暗号解読時入力エラーのテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
			assertEquals("暗号解読時入力エラーのテスト(エラーメッセージ)", "[入力文]を入力してください。", params.getErrorMessage());
			assertEquals("暗号解読時入力エラーのテスト(シフト数)", "", params.getShift());
			assertEquals("暗号解読時入力エラーのテスト(入力)", "", params.getTarget());
			assertEquals("暗号解読時入力エラーのテスト(出力)", "", params.getMessage());
			assertEquals("暗号解読時入力エラーのテスト(操作)", Operation.doCryptanalysis, params.getOperation());

			// 暗号解読時（失敗）のテスト
			when(request.getParameter("operation")).thenReturn("doCryptanalysis");
			when(request.getParameter("target")).thenReturn("BCDEFGHIJKLMNOPQRSTUVWXYZA0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n");
			when(request.getParameter("shift")).thenReturn(null);
			servlet.doPost(request, response);
			assertEquals("暗号解読時のテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
			assertEquals("暗号解読時のテスト(エラーメッセージ)", "", params.getErrorMessage());
			assertEquals("暗号解読時のテスト(シフト数)", "", params.getShift());
			assertEquals("暗号解読時のテスト(入力)", "BCDEFGHIJKLMNOPQRSTUVWXYZA0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n", params.getTarget());
			assertEquals("暗号解読時のテスト(出力)", "解読できませんでした。", params.getMessage());
			assertEquals("暗号解読時のテスト(操作)", Operation.doCryptanalysis, params.getOperation());

			// 暗号解読時（成功）のテスト
			when(request.getParameter("operation")).thenReturn("doCryptanalysis");
			when(request.getParameter("target")).thenReturn("BCD JT EFG");
			when(request.getParameter("shift")).thenReturn(null);
			servlet.doPost(request, response);
			assertEquals("暗号解読時のテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
			assertEquals("暗号解読時のテスト(エラーメッセージ)", "", params.getErrorMessage());
			assertEquals("暗号解読時のテスト(シフト数)", "", params.getShift());
			assertEquals("暗号解読時のテスト(入力)", "BCD JT EFG", params.getTarget());
			assertEquals("暗号解読時のテスト(出力)", "abc is def", params.getMessage());
			assertEquals("暗号解読時のテスト(操作)", Operation.doCryptanalysis, params.getOperation());

		} catch (ServletException e1) {
			fail("doGetのテストで例外発生（ServletException）");
		} catch (IOException e1) {
			fail("doGetのテストで例外発生（IOException）");
		}
	}

	@Test
	public void testCaesarArrayServlet() {
		CaesarArrayServlet servlet = new CaesarArrayServlet();
		assertEquals("コンストラクタのテスト", true, true);
	}

	@Test
	public void testGetParameter() {

		CaesarArrayParams params = null;

		// 初期表示
		when(request.getParameter("operation")).thenReturn(null);
		when(request.getParameter("shift")).thenReturn(null);
		when(request.getParameter("target")).thenReturn(null);
		params = servlet.getParameter(request);
		assertEquals("初期表示のテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
		assertEquals("初期表示のテスト(エラーメッセージ)", "", params.getErrorMessage());
		assertEquals("初期表示のテスト(シフト数)", "", params.getShift());
		assertEquals("初期表示のテスト(入力)", "", params.getTarget());
		assertEquals("初期表示のテスト(出力)", "", params.getMessage());
		assertEquals("初期表示のテスト(操作)", Operation.unknown, params.getOperation());

		// 暗号化
		when(request.getParameter("operation")).thenReturn("doEncryption");
		when(request.getParameter("shift")).thenReturn("2");
		when(request.getParameter("target")).thenReturn("abc");
		params = servlet.getParameter(request);
		assertEquals("暗号化のテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
		assertEquals("暗号化のテスト(エラーメッセージ)", "", params.getErrorMessage());
		assertEquals("暗号化のテスト(シフト数)", "2", params.getShift());
		assertEquals("暗号化のテスト(入力)", "abc", params.getTarget());
		assertEquals("暗号化のテスト(出力)", "", params.getMessage());
		assertEquals("暗号化のテスト(操作)", Operation.doEncryption, params.getOperation());

		// 復号化
		when(request.getParameter("operation")).thenReturn("doDecryption");
		when(request.getParameter("shift")).thenReturn("3");
		when(request.getParameter("target")).thenReturn("ABC");
		params = servlet.getParameter(request);
		assertEquals("復号化のテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
		assertEquals("復号化のテスト(エラーメッセージ)", "", params.getErrorMessage());
		assertEquals("復号化のテスト(シフト数)", "3", params.getShift());
		assertEquals("復号化のテスト(入力)", "ABC", params.getTarget());
		assertEquals("復号化のテスト(出力)", "", params.getMessage());
		assertEquals("復号化のテスト(操作)", Operation.doDecryption, params.getOperation());

		// 暗号解読
		when(request.getParameter("operation")).thenReturn("doCryptanalysis");
		when(request.getParameter("shift")).thenReturn(null);
		when(request.getParameter("target")).thenReturn("ABC");
		params = servlet.getParameter(request);
		assertEquals("暗号解読のテスト(タイトル)", "カエサル暗号（配列）", params.getTitle());
		assertEquals("暗号解読のテスト(エラーメッセージ)", "", params.getErrorMessage());
		assertEquals("暗号解読のテスト(シフト数)", "", params.getShift());
		assertEquals("暗号解読のテスト(入力)", "ABC", params.getTarget());
		assertEquals("暗号解読のテスト(出力)", "", params.getMessage());
		assertEquals("暗号解読のテスト(操作)", Operation.doCryptanalysis, params.getOperation());
	}

	@Test
	public void testDoEncryption() {
		String shift;
		String target;
		String message;

		shift = "1";
		target= "abcdefghijklmnopqrstuvwxyz0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n";
		message = servlet.doEncryption(shift, target);
		assertEquals("暗号化のテスト", "BCDEFGHIJKLMNOPQRSTUVWXYZA0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n", message);

		shift = "25";
		target= "abcdefghijklmnopqrstuvwxyz0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n";
		message = servlet.doEncryption(shift, target);
		assertEquals("暗号化のテスト", "ZABCDEFGHIJKLMNOPQRSTUVWXY0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n", message);
	}

	@Test
	public void testDoDecryption() {
		String shift;
		String target;
		String message;

		shift = "1";
		target= "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n";
		message = servlet.doDecryption(shift, target);
		assertEquals("復号化のテスト", "zabcdefghijklmnopqrstuvwxy0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n", message);

		shift = "25";
		target= "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n";
		message = servlet.doDecryption(shift, target);
		assertEquals("復号化のテスト", "bcdefghijklmnopqrstuvwxyza0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n", message);
	}

	@Test
	public void testDoCryptanalysis() {
		String target;
		String message;

		target= "YMJWJ NX FQBFDX FS JFXNJW BFD YT IT NY.";
		message = servlet.doCryptanalysis(target);
		assertEquals("暗号解読のテスト（2文字単語を含む）", "there is always an easier way to do it.", message);

		target= "C BUPY U XLYUG";
		message = servlet.doCryptanalysis(target);
		assertEquals("暗号解読のテスト（1文字単語を含む）", "i have a dream", message);

		target= "ABCDEFG";
		message = servlet.doCryptanalysis(target);
		assertEquals("暗号解読のテスト（解読不能）", "解読できませんでした。", message);
	}

	@Test
public void testValidate() {

		CaesarArrayParams params = new CaesarArrayParams();

		boolean rtn = false;

		// 初期表示 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		// 初期表示（正常系）
		params.setOperation(Operation.unknown);
		params.setTarget("");
		params.setShift("");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("初期表示のテスト(正常系)", "", params.getErrorMessage());
		assertEquals("初期表示のテスト(正常系)", true, rtn);

		// 暗号化 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		// 暗号化の入力文未入力チェック（正常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("abcdefghijklmnopqrstuvwxyz0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化の入力文未入力チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号化の入力文未入力チェック（正常系）", true, rtn);

		// 暗号化の入力文未入力チェック（異常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化の入力文未入力チェック（異常系）", "[入力文]を入力してください。", params.getErrorMessage());
		assertEquals("暗号化の入力文未入力チェック（異常系）", false, rtn);

		// 暗号化の入力文全角チェック（異常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("abcあいうdef");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化の入力文未入力チェック（異常系）", "[入力文]は半角英数字記号で入力してください。", params.getErrorMessage());
		assertEquals("暗号化の入力文未入力チェック（異常系）", false, rtn);

		// 暗号化の入力文小文字チェック（異常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("abcDEF");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化の入力文小文字チェック（異常系）", "暗号化時、[入力文]は小文字で入力してください。", params.getErrorMessage());
		assertEquals("暗号化の入力文小文字チェック（異常系）", false, rtn);

		// 暗号化のシフト数最小値-1チェック（異常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("abc");
		params.setShift("0");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化のシフト数-1チェック（異常系）", "[シフト数]は1～25の範囲で入力してください。", params.getErrorMessage());
		assertEquals("暗号化のシフト数-1チェック（異常系）", false, rtn);

		// 暗号化のシフト数最小値チェック（正常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("abc");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化のシフト数チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号化のシフト数チェック（正常系）", true, rtn);

		// 暗号化のシフト数最大値チェック（正常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("abc");
		params.setShift("25");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化のシフト数最大値チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号化のシフト数最大値チェック（正常系）", true, rtn);

		// 暗号化のシフト数最大値+1チェック（異常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("abc");
		params.setShift("26");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化のシフト数最大値+1チェック（異常系）", "[シフト数]は1～25の範囲で入力してください。", params.getErrorMessage());
		assertEquals("暗号化のシフト数最大値+1チェック（異常系）", false, rtn);

		// 暗号化のシフト数英小文字チェック（異常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("abc");
		params.setShift("1a");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化のシフト数英小文字チェック（異常系）", "[シフト数]は半角数字で入力してください。", params.getErrorMessage());
		assertEquals("暗号化のシフト数英小文字チェック（異常系）", false, rtn);

		// 暗号化のシフト数英大文字チェック（異常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("abc");
		params.setShift("1A");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化のシフト数英小文字チェック（異常系）", "[シフト数]は半角数字で入力してください。", params.getErrorMessage());
		assertEquals("暗号化のシフト数英小文字チェック（異常系）", false, rtn);

		// 暗号化のシフト記号チェック（異常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("abc");
		params.setShift("1%");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化のシフト数記号チェック（異常系）", "[シフト数]は半角数字で入力してください。", params.getErrorMessage());
		assertEquals("暗号化のシフト数記号チェック（異常系）", false, rtn);

		// 暗号化のシフト全角チェック（異常系）
		params.setOperation(Operation.doEncryption);
		params.setTarget("abc");
		params.setShift("1あ");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号化のシフト数最大値+1チェック（異常系）", "[シフト数]は半角数字で入力してください。", params.getErrorMessage());
		assertEquals("暗号化のシフト数最大値+1チェック（異常系）", false, rtn);

		// 復号化 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		// 復号化の入力文未入力チェック（正常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化の入力文未入力チェック（正常系）", "", params.getErrorMessage());
		assertEquals("復号化の入力文未入力チェック（正常系）", true, rtn);

		// 復号化の入力文未入力チェック（異常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化の入力文未入力チェック（異常系）", "[入力文]を入力してください。", params.getErrorMessage());
		assertEquals("復号化の入力文未入力チェック（異常系）", false, rtn);

		// 復号化の入力文全角チェック（異常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("ABCあいうDEF");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化の入力文未入力チェック（異常系）", "[入力文]は半角英数字記号で入力してください。", params.getErrorMessage());
		assertEquals("復号化の入力文未入力チェック（異常系）", false, rtn);

		// 復号化の入力文大文字チェック（異常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("abcDEF");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化の入力文大文字チェック（異常系）", "復号化、暗号解読時、[入力文]は大文字で入力してください。", params.getErrorMessage());
		assertEquals("復号化の入力文大文字チェック（異常系）", false, rtn);

		// 復号化のシフト数最小値-1チェック（異常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("ABC");
		params.setShift("0");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化のシフト数-1チェック（異常系）", "[シフト数]は1～25の範囲で入力してください。", params.getErrorMessage());
		assertEquals("復号化のシフト数-1チェック（異常系）", false, rtn);

		// 復号化のシフト数最小値チェック（正常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("ABC");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化のシフト数チェック（正常系）", "", params.getErrorMessage());
		assertEquals("復号化のシフト数チェック（正常系）", true, rtn);

		// 復号化のシフト数最大値チェック（正常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("ABC");
		params.setShift("25");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化のシフト数最大値チェック（正常系）", "", params.getErrorMessage());
		assertEquals("復号化のシフト数最大値チェック（正常系）", true, rtn);

		// 復号化のシフト数最大値+1チェック（異常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("ABC");
		params.setShift("26");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化のシフト数最大値+1チェック（異常系）", "[シフト数]は1～25の範囲で入力してください。", params.getErrorMessage());
		assertEquals("復号化のシフト数最大値+1チェック（異常系）", false, rtn);

		// 復号化のシフト数英小文字チェック（異常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("ABC");
		params.setShift("1a");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化のシフト数英小文字チェック（異常系）", "[シフト数]は半角数字で入力してください。", params.getErrorMessage());
		assertEquals("復号化のシフト数英小文字チェック（異常系）", false, rtn);

		// 復号化のシフト数英大文字チェック（異常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("ABC");
		params.setShift("1A");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化のシフト数英小文字チェック（異常系）", "[シフト数]は半角数字で入力してください。", params.getErrorMessage());
		assertEquals("復号化のシフト数英小文字チェック（異常系）", false, rtn);

		// 復号化のシフト記号チェック（異常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("ABC");
		params.setShift("1%");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化のシフト数記号チェック（異常系）", "[シフト数]は半角数字で入力してください。", params.getErrorMessage());
		assertEquals("復号化のシフト数記号チェック（異常系）", false, rtn);

		// 復号化のシフト全角チェック（異常系）
		params.setOperation(Operation.doDecryption);
		params.setTarget("ABC");
		params.setShift("1あ");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("復号化のシフト数最大値+1チェック（異常系）", "[シフト数]は半角数字で入力してください。", params.getErrorMessage());
		assertEquals("復号化のシフト数最大値+1チェック（異常系）", false, rtn);

		// 暗号解読 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		// 暗号解読の入力文未入力チェック（正常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読の入力文未入力チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号解読の入力文未入力チェック（正常系）", true, rtn);

		// 暗号解読の入力文未入力チェック（異常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読の入力文未入力チェック（異常系）", "[入力文]を入力してください。", params.getErrorMessage());
		assertEquals("暗号解読の入力文未入力チェック（異常系）", false, rtn);

		// 暗号解読の入力文全角チェック（異常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("ABCあいうDEF");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読の入力文未入力チェック（異常系）", "[入力文]は半角英数字記号で入力してください。", params.getErrorMessage());
		assertEquals("暗号解読の入力文未入力チェック（異常系）", false, rtn);

		// 暗号解読の入力文小文字チェック（異常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("abcDEF");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読の入力文小文字チェック（異常系）", "復号化、暗号解読時、[入力文]は大文字で入力してください。", params.getErrorMessage());
		assertEquals("暗号解読の入力文小文字チェック（異常系）", false, rtn);

		// 暗号解読のシフト数最小値-1チェック（正常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("ABC");
		params.setShift("0");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読のシフト数-1チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号解読のシフト数-1チェック（正常系）", true, rtn);

		// 暗号解読のシフト数最小値チェック（正常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("ABC");
		params.setShift("1");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読のシフト数チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号解読のシフト数チェック（正常系）", true, rtn);

		// 暗号解読のシフト数最大値チェック（正常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("ABC");
		params.setShift("25");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読のシフト数最大値チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号解読のシフト数最大値チェック（正常系）", true, rtn);

		// 暗号解読のシフト数最大値+1チェック（正常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("ABC");
		params.setShift("26");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読のシフト数最大値+1チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号解読のシフト数最大値+1チェック（正常系）", true, rtn);

		// 暗号解読のシフト数英小文字チェック（正常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("ABC");
		params.setShift("1a");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読のシフト数英小文字チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号解読のシフト数英小文字チェック（正常系）", true, rtn);

		// 暗号解読のシフト数英大文字チェック（正常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("ABC");
		params.setShift("1A");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読のシフト数英小文字チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号解読のシフト数英小文字チェック（正常系）", true, rtn);

		// 暗号解読のシフト記号チェック（正常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("ABC");
		params.setShift("1%");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読のシフト数記号チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号解読のシフト数記号チェック（正常系）", true, rtn);

		// 暗号解読のシフト全角チェック（正常系）
		params.setOperation(Operation.doCryptanalysis);
		params.setTarget("ABC");
		params.setShift("1あ");
		params.setErrorMessage("");
		rtn = servlet.validate(params);
		assertEquals("暗号解読のシフト数最大値+1チェック（正常系）", "", params.getErrorMessage());
		assertEquals("暗号解読のシフト数最大値+1チェック（正常系）", true, rtn);


	}
}
