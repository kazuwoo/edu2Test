package base;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTest {

	@Test
	public void testUtil() {
		Util util = new Util();
		assertEquals("コンストラクタのテスト", true, true);
	}

	@Test
	public void testGetLastModified() {
		assertEquals("存在しないファイル", "19700101090000", Util.getLastModified("UtilTest.java"));
	}

	@Test
	public void testStr2int() {
		assertEquals("最小値", Integer.MIN_VALUE, Util.str2int(String.valueOf(Integer.MIN_VALUE)));
		assertEquals("最小値-1", 0, Util.str2int(String.valueOf(-2147483649L)));
		assertEquals("最大値", Integer.MAX_VALUE, Util.str2int(String.valueOf(Integer.MAX_VALUE)));
		assertEquals("最大値+1", 0, Util.str2int(String.valueOf(2147483648L)));
		assertEquals("数字以外", 0, Util.str2int("a"));
		assertEquals("空文字", 0, Util.str2int(""));
		assertEquals("null", 0, Util.str2int(null));
	}

	@Test
	public void testIsHalfWidth() {
		assertEquals("許容される値のすべて", true, Util.isHalfWidth("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n"));
		assertEquals("全角英字を含む", false, Util.isHalfWidth("Ａabcde"));
		assertEquals("全角数字を含む", false, Util.isHalfWidth("１abcde"));
		assertEquals("全角記号を含む", false, Util.isHalfWidth("％abcde"));
		assertEquals("全角ひらがなを含む", false, Util.isHalfWidth("あabcde"));
		assertEquals("全角カタカナを含む", false, Util.isHalfWidth("アabcde"));
		assertEquals("空文字", false, Util.isHalfWidth(""));
		assertEquals("null", false, Util.isHalfWidth(null));
	}

	@Test
	public void testIsIncludeUpperCase() {
		assertEquals("英大文字を含まない", false, Util.isIncludeUpperCase("abcdefghijklmnopqrstuvwxyz0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n"));
		assertEquals("Aを含む", true, Util.isIncludeUpperCase("Abcdefghijklmnopqrstuvwxyz0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n"));
		assertEquals("Zを含む", true, Util.isIncludeUpperCase("abcdefghijklmnopqrstuvwxyZ0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n"));
	}

	@Test
	public void testIsIncludeLowerCase() {
		assertEquals("英小文字を含まない", false, Util.isIncludeLowerCase("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n"));
		assertEquals("aを含む", true, Util.isIncludeLowerCase("aBCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n"));
		assertEquals("zを含む", true, Util.isIncludeLowerCase("ABCDEFGHIJKLMNOPQRSTUVWXYz0123456789 !\"#$%&\'()*+-.,/:;<=>?@[\\]^_`{|}~\r\n"));
	}

	@Test
	public void testIsNumber() {
		assertEquals("許容される値のすべて", true, Util.isNumber("1234567890"));
		assertEquals("全角英字を含む", false, Util.isNumber("Ａ12345"));
		assertEquals("全角数字を含む", false, Util.isNumber("１12345"));
		assertEquals("全角記号を含む", false, Util.isNumber("％12345"));
		assertEquals("全角ひらがなを含む", false, Util.isNumber("あ12345"));
		assertEquals("全角カタカナを含む", false, Util.isNumber("ア12345"));
		assertEquals("改行を含む", false, Util.isNumber("\r\n12345"));
		assertEquals("空文字", false, Util.isNumber(""));
		assertEquals("null", false, Util.isNumber(null));
	}

	@Test
	public void testWordSplit() {
		String[] strArray = new String[]{
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
				"0", "1", "2", "3", "4", "5", "6", "7", "89"
		};
		assertArrayEquals("全ての分割文字を含む", strArray, Util.wordSplit("A B!C\"D#E$F%G&H\'I(J)*K+L-M.N,O/P:Q;R<S=T>U?V@W[X\\Y]Z^0_1`2{3|4}5~6\r7\n89"));
	}

}
