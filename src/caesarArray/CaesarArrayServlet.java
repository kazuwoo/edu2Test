package caesarArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import base.BaseServlet;
import base.Operation;
import base.Util;

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

		// 文字化け対策
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// パラメータ取得
		CaesarArrayParams params = getParameter(request);
		String message = "";

		switch(params.getOperation()) {
		case doEncryption:
			// 暗号化

			// 入力チェック
			if (validate(params)) {

				// 暗号化処理
				message = doEncryption(params.getShift(), params.getTarget());
			}
			break;
		case doDecryption:
			// 復号化

			//入力チェック
			if (validate(params)) {
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

		// アルファベット初期化
		ArrayList<String> alphaList = new ArrayList<String>(
			Arrays.asList(
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
				"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
			)
		);

		// シフト数取得
		int scnt = Integer.parseInt(shift);

		// 入力文を1文字ずつ分割
		String[] targetArray = target.split("");
		StringBuilder sb = new StringBuilder();
		for (String ch: targetArray) {

			// アルファベットから検索
			int fpos = 0;
			do {
				if (alphaList.get(fpos).equals(ch)) {
					// 見つかった
					break;
				}
			} while ( ++fpos < alphaList.size());

			// 暗号文に変換
			if (fpos == alphaList.size()) {
				// 見つからなかった文字は変換しない
				sb.append(ch);
			} else {
				// 暗号文に変換するため、シフト後の位置を算出
				int tpos = (fpos + scnt) % alphaList.size();
				// 暗号文用のアルファベットを取得し、大文字に変換
				sb.append(alphaList.get(tpos).toUpperCase());
			}
		}

		return sb.toString();
	}

	/**
	 * 復号化処理
	 * @params shift シフト数
	 * @param target 暗号文
	 * @return 復号化したメッセージ
	 */
	protected String doDecryption(String shift, String target) {

		// アルファベット初期化
		ArrayList<String> alphaList = new ArrayList<String>(
			Arrays.asList(
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
				"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
			)
		);

		// シフト数取得
		int scnt = Integer.parseInt(shift);

		// 入力文を1文字ずつ分割
		String[] targetArray = target.split("");
		StringBuilder sb = new StringBuilder();
		for (String ch: targetArray) {
			// アルファベットから検索
			int fpos = 0;
			do {
				if (alphaList.get(fpos).equals(ch)) {
					// 見つかった
					break;
				}
			} while ( ++fpos < alphaList.size());

			// 平文に変換
			if (fpos == alphaList.size()) {
				// 見つからなかった文字は変換しない
				sb.append(ch);
			} else {
				// 平文に変換するため、シフト後の位置を算出
				int tpos = (fpos + alphaList.size() - scnt) % alphaList.size();
				// 平文用のアルファベットを取得
				sb.append(alphaList.get(tpos).toUpperCase());
			}
		}

		return sb.toString();
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

		boolean rtn = true;

		// 入力文未入力チェック
		if (params.getTarget().length() == 0) {
			params.addErrorMessage("[入力]文を入力してください。");
			rtn = false;
		} else {
			// 入力文半角チェック
			if (!Util.isHalfWidth(params.getTarget())) {
				params.addErrorMessage("[入力]文は半角英小文字数字で入力してください。");
				rtn = false;
			}
		}

		// シフト数値チェック（1～26以外ならエラー）
		int shift = Util.str2int(params.getShift());
		if (1 > shift || shift > 25) {
			params.addErrorMessage("[シフト数]は1～25の範囲で入力してください。");
			rtn = false;
		}

		return rtn;
	}
}
