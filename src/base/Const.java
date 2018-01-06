package base;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 定数クラス
 */
public class Const {
	/**
	 * 1文字の英単語
	 */
	public static final ArrayList<String> oneCharList = new ArrayList<String>(
		Arrays.asList("A", "I")
	);
	/**
	 * 2文字の英単語
	 */
	public static final ArrayList<String> twoCharList = new ArrayList<String>(
		Arrays.asList(
			"AM", "AN", "AS", "AT", "BE", "BY", "DO",
			"EX", "GO", "HE", "HI", "ID", "IF", "IN",
			"IS", "IT", "ME", "MY", "NO", "OF", "OH",
			"OK", "ON", "OP", "OR", "OS", "OW", "OX",
			"SO", "TO", "UP", "US", "WE", "YO"
		)
	);
}
