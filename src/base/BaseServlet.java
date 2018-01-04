package base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * すべてのServletの基底クラス
 * Servlet implementation class baseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * パラメータ
	 */
	protected BaseParams params;

	/**
	 * パラメータ取得
	 * @return パラメータ
	 */
	protected BaseParams getParams() {
		return params;
	}

	/**
	 * パラメータ設定
	 * @param params パラメータ
	 */
	protected void setParams(BaseParams params) {
		this.params = params;
	}

    /**
     * コンストラクタ
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
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

}
