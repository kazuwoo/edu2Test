/**
 *
 */
package base;

import java.io.File;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;


/**
 * 共通処理
 */
public class Util {

	/**
	 * デフォルトコンストラクタ
	 */
	protected Util() {

	}

	/** 最終更新日時取得用書式定義 */
	private static SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	/**
	 * ファイルの最終更新日時を返す
	 * @param path 対象となるファイル
	 * @return 最終更新日時（yyyyMMddhhmmss)
	 */
	public static String getLastModified(String path) {
		File file = new File(path);
		Long lastModified = file.lastModified();
		return timestampFormat.format(lastModified);
	}

	/**
	 * 文字列をintに変換して返す。変換できない場合は0を返す。
	 * @param str 変換対象の文字列
	 * @return intに変換した値
	 */
	public static int str2int(String str) {
		int val = 0;
		try {
			// intに変換
			val = Integer.parseInt(str);
		} catch(Exception NumberFormatException) {
			// 変換できないので0を返す
			val = 0;
		}
		return val;
	}

	/**
	 * 半角英数字記号チェック<br>
	 * 許される文字：abcdefghijklmnopqrstuvwxyz0123456789半角空白!"#$%&'()*+-.,/:;<=>?@[\]^_`{|}~改行
	 * @param str チェック対象の文字列
	 * @return すべて半角英数字記号ならtrue
	 */
	public static boolean isHalfWidth(String str) {
		String target = StringUtils.defaultString(str);
		return target.matches("[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~\r\n]+");
	}

	/**
	 * 大文字チェック
	 * @param str チェック対象の文字列
	 * @return 文字列中に英大文字を含んでいればtrue
	 */
	public static boolean isIncludeUpperCase(String str) {
		String target = StringUtils.defaultString(str);
		return target.replaceAll("\r\n", "").matches(".*[A-Z].*");
	}

	/**
	 * 小文字チェック
	 * @param str チェック対象の文字列
	 * @return 文字列中に英小文字を含んでいればtrue
	 */
	public static boolean isIncludeLowerCase(String str) {
		String target = StringUtils.defaultString(str);
		return target.replaceAll("\r\n", "").matches(".*[a-z].*");
	}

	/**
	 * 数字チェック
	 * @param str
	 * @param str チェック対象の文字列
	 * @return すべて数字ならtrue
	 */
	public static boolean isNumber(String str) {
		String target = StringUtils.defaultString(str);
		return target.matches("[0-9]+");
	}

	/**
	 * 単語分割。文章を単語の区切りで分割する。
	 * @param str 分割対象の文字列
	 * @return 単語に分割された文字列の配列
	 */
	public static String[] wordSplit(String str) {
		String target = StringUtils.defaultString(str);
		return target.split("[ -/:-@\\[-\\`\\{-\\~\r\n]+");
	}
}
