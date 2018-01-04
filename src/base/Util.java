/**
 *
 */
package base;

import java.io.File;
import java.text.SimpleDateFormat;


/**
 * 共通処理
 */
public class Util {

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
}
