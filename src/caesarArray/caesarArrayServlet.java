package caesarArray;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.Params;
import base.baseServlet;

/**
 * カエサル暗号（配列）Servletクラス
 * Servlet implementation class caesarArrayServlet
 */
@WebServlet("/caesarArray")
public class caesarArrayServlet extends baseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see baseServlet#baseServlet()
     */
    public caesarArrayServlet() {
        super();
    }

	/**
	 * カエサル暗号（配列）の本処理
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Params params = getParameter(request);
		String message = null;

		switch(params.getOperation()) {
		case doEncryption:
			// 暗号化処理
			message = doEncryption(params.getShift(), params.getTarget());
			break;
		case doDecryption:
			// 復号化処理
			message = doDecryption(params.getShift(), params.getTarget());
			break;
		case doCryptanalysis:
			// 暗号解読処理
			message = doCryptanalysis(params.getTarget());
			break;
		default:
			// 初期表示
			message = "";
			break;
		}

		// 結果を設定
		params.setTitle("カエサル暗号（配列）");
		params.setMessage(message);
		request.setAttribute("params", params);

		// 画面に結果を返す
		getServletConfig().getServletContext().getRequestDispatcher("/jsp/caesarArray.jsp").forward(request, response);
	}

	/**
	 * 暗号化処理
	 * @params shift シフト数
	 * @param target 平文
	 * @return 暗号化したメッセージ
	 */
	protected String doEncryption(String shift, String target) {
		String message = "doEncryption";

		//　ここに暗号化処理を書く

		return message;
	}

	/**
	 * 復号化処理
	 * @params shift シフト数
	 * @param target 暗号文
	 * @return 復号化したメッセージ
	 */
	protected String doDecryption(String shift, String target) {
		String message = "doDecryption";

		//　ここに復号化処理を書く

		return message;
	}

	/**
	 * 暗号解読処理
	 * @params shift シフト数
	 * @param target 暗号
	 * @return 解読したメッセージ
	 */
	protected String doCryptanalysis(String target) {
		String message = "doCryptanalysis";

		//　ここに暗号解読処理を書く

		return message;
	}

}
