package base;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;;

public class BaseServletTest {

    // request responseをmock化
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);

	@Test
	public void testBaseServlet() {
		BaseServlet servlet = new BaseServlet();
		assertEquals("コンストラクタのテスト", true, true);
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
		BaseServlet servlet = new BaseServlet();
		try {
			servlet.doGet(request, response);
		} catch (ServletException e1) {
			fail("doGetのテストで例外発生（ServletException）");
		} catch (IOException e1) {
			fail("doGetのテストで例外発生（IOException）");
		}
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		BaseServlet servlet = new BaseServlet();
		try {
			servlet.doPost(request, response);
		} catch (ServletException e1) {
			fail("doGetのテストで例外発生（ServletException）");
		} catch (IOException e1) {
			fail("doGetのテストで例外発生（IOException）");
		}
	}

}
