package base;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 定数クラス
 */
public final class Const {

	/**
	 * デフォルトコンストラクタ
	 */
	protected Const() {

	}

	/**
	 * 1文字の英単語
	 */
	public static final ArrayList<String> oneCharList = new ArrayList<String>(
		Arrays.asList("a", "i")
	);

	/**
	 * 2文字の英単語
	 */
	public static final ArrayList<String> twoCharList = new ArrayList<String>(
		Arrays.asList(
			"am", "an", "as", "at", "be", "by", "do",
			"ex", "go", "he", "hi", "id", "if", "in",
			"is", "it", "me", "my", "no", "of", "oh",
			"ok", "on", "op", "or", "os", "ow", "ox",
			"so", "to", "up", "us", "we", "yo"
		)
	);
}
