package base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


/**
 * すべてのServletの基底クラス
 * Servlet implementation class baseServlet
 */
@WebServlet("/baseServlet")
public class baseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * コンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public baseServlet() {
        super();
    }

	/**
	 * doGet処理。強制的にdoPostを呼び出す。
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 本処理
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * パラメータ取得
	 * @request リクエストパラメータ
	 * @return パラメータ
	 */
	protected Params getParameter(HttpServletRequest request) {
		Params params = new Params();

		// 操作取得
		String operationBuf	= StringUtils.defaultString(request.getParameter("operation"));
		Operation operation	= Operation.defaultValueOf(operationBuf);

		// シフト数取得
		String shift	= StringUtils.defaultString(request.getParameter("shift"));

		// キーワード取得
		String keyword	= StringUtils.defaultString(request.getParameter("keyword"));

		// 入力文取得
		String target	= StringUtils.defaultString(request.getParameter("target"));

		params.setOperation(operation);
		params.setShift(shift);
		params.setKeyword(keyword);
		params.setTarget(target);

		return params;
	}

}
