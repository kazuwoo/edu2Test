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
import base.Const;
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

		// 入力チェック
		if (validate(params)) {

			switch(params.getOperation()) {
			case doEncryption:
				// 暗号化
				message = doEncryption(params.getShift(), params.getTarget());
				break;
			case doDecryption:
				// 復号化
				message = doDecryption(params.getShift(), params.getTarget());
				break;
			case doCryptanalysis:
				// 暗号解読処理
				message = doCryptanalysis(params.getTarget());
				break;
			default:
				// 初期表示
				break;
			}

		}

		// 結果を設定
		params.setMessage(message);
		request.setAttribute("params", params);

		// 画面に結果を返す
		request.getRequestDispatcher("/jsp/caesarArray.jsp").forward(request, response);
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

		params.setTitle("カエサル暗号（配列）");
		params.setErrorMessage("");
		params.setOperation(operation);
		params.setShift(shift);
		params.setTarget(target);
		params.setMessage("");


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
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
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
				sb.append(alphaList.get(tpos).toLowerCase());
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

		int matchCountMax = 0;		// 解読に成功した単語の最大数
		String candidacy = null;	// 解読に成功したと思われる文章の候補

		// シフト数を1ずつ変えて復号化してみる
		for (int shift = 1; shift < 26; shift++) {

			// いったん復号化
			String sentence = doDecryption(Integer.toString(shift), target);
			// 復号化した文章を単語で分割
			String[] words = Util.wordSplit(sentence);

			int matchCount = 0;	// 既知の単語と一致した数
			int checkCount = 0;	// 1文字単語、2文字単語の数

			// 1文字単語、2文字単語の内、既知の単語と一致した数を数える
			for (int pos = 0; pos < words.length; pos++) {
				// 1文字単語か？
				if (words[pos].length() == 1) {
					// 1文字単語
					checkCount++;
					// 既知の単語と一致するか
					for (String o: Const.oneCharList) {
						if (o.equals(words[pos])) {
							// 既知の単語と一致したので、解読成功と思われる
							matchCount++;
						}
					}
				}

				// 2文字単語か？
				if (words[pos].length() == 2) {
					// 2文字単語
					checkCount++;
					// 既知の単語と一致するか
					for (String t: Const.twoCharList) {
						if (t.equals(words[pos])) {
							// 2文字単語
							matchCount++;
						}
					}
				}
			}

			// 過去の1文字単語、2文字単語の数と比較し最大となる復号結果を正解とみなす
			if (matchCount > matchCountMax) {
				matchCountMax = matchCount;
				candidacy = sentence;
			}

			// すべての1文字単語、2文字単語の解読に成功したら、解読終了
			if (checkCount == matchCountMax) {
				break;
			}
		}

		if (candidacy == null) {
			// 解読失敗
			candidacy = "解読できませんでした。";
		}
		return candidacy;
	}

	/**
	 * 入力チェック
	 * @param shift シフト数
	 * @param target 入力文
	 * @return true エラーなし
	 */
	protected boolean validate(CaesarArrayParams params) {

		boolean rtn = true;

		switch(params.getOperation()) {
		case doEncryption:
		case doDecryption:
		case doCryptanalysis:

			// 入力文未入力チェック
			if (params.getTarget().length() == 0) {
				params.addErrorMessage("[入力文]を入力してください。");
				rtn = false;
			} else {
				// 入力文半角チェック
				if (!Util.isHalfWidth(params.getTarget())) {
					params.addErrorMessage("[入力文]は半角英数字記号で入力してください。");
					rtn = false;
				}

				if (params.getOperation().equals(Operation.doEncryption)) {

					// 暗号化時は小文字のみ入力可
					if (Util.isIncludeUpperCase(params.getTarget())) {
						params.addErrorMessage("暗号化時、[入力文]は小文字で入力してください。");
						rtn = false;
					}
				} else {

					// 復号化、暗号解読時は大文字のみ入力可
					if (Util.isIncludeLowerCase(params.getTarget())) {
						params.addErrorMessage("復号化、暗号解読時、[入力文]は大文字で入力してください。");
						rtn = false;
					}
				}
			}

			if (params.getOperation().equals(Operation.doEncryption)
				||params.getOperation().equals(Operation.doDecryption)) {

				// シフト数未入力チェック
				if (params.getShift().length() == 0) {
					params.addErrorMessage("[シフト数]を入力してください。");
					rtn = false;
				} else{

					// シフト数半角チェック
					if (!Util.isNumber(params.getShift())) {
						params.addErrorMessage("[シフト数]は半角数字で入力してください。");
						rtn = false;
					} else {

						// シフト数値チェック（1～26以外ならエラー）
						int shift = Util.str2int(params.getShift());
						if (1 > shift || shift > 25) {
							params.addErrorMessage("[シフト数]は1～25の範囲で入力してください。");
							rtn = false;
						}
					}
				}
			}

			break;

		default:

			// 初期表示時は何もしない
			break;

		}

		return rtn;
	}
}
