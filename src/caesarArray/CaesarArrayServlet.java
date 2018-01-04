package caesarArray;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import base.BaseServlet;
import base.Operation;

/**
 * カエサル暗号（配列）Servletクラス
 * Servlet implementation class caesarArrayServlet
 */
@WebServlet("/CaesarArray")
public class CaesarArrayServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see BaseServlet#baseServlet()
     */
    public CaesarArrayServlet() {
        super();
    }

	/**
	 * カエサル暗号（配列）の本処理
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CaesarArrayParams params = getParameter(request);
		String message = null;

		switch(params.getOperation()) {
		case doEncryption:
			// 暗号化

			// 入力チェック
			if (!validate(params)) {

				// 暗号化処理
				message = doEncryption(params.getShift(), params.getTarget());
			}
			break;
		case doDecryption:
			// 復号化

			//入力チェック
			if (!validate(params)) {
				// 復号化処理
				message = doDecryption(params.getShift(), params.getTarget());
			}
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
	 * パラメータ取得
	 * @request リクエストパラメータ
	 * @return パラメータ
	 */
	protected CaesarArrayParams getParameter(HttpServletRequest request) {
		CaesarArrayParams params = new CaesarArrayParams();

		// 操作取得
		String operationBuf	= StringUtils.defaultString(request.getParameter("operation"));
		Operation operation	= Operation.defaultValueOf(operationBuf);

		// シフト数取得
		String shift	= StringUtils.defaultString(request.getParameter("shift"));

		// 入力文取得
		String target	= StringUtils.defaultString(request.getParameter("target"));

		params.setOperation(operation);
		params.setShift(shift);
		params.setTarget(target);

		return params;
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

	/**
	 * 入力チェック
	 * @param shift シフト数
	 * @param target 入力文
	 * @return true エラーなし
	 */
	protected boolean validate(CaesarArrayParams params) {

		params.addErrorMessage("エラーだよ");
		params.addErrorMessage("エラーだよ２");

		return false;
	}
}
