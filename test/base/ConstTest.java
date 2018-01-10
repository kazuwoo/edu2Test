package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ConstTest {

	@Test
	public void testConst() {
		Const con = new Const();
		assertEquals("コンストラクタのテスト", true, true);
	}

	@Test
	public void test() {

		ArrayList<String> oneCharList = Const.oneCharList;
		assertEquals("oneCharListのテスト(0)", oneCharList.get(0), "a");
		assertEquals("oneCharListのテスト(1)", oneCharList.get(1), "i");

		ArrayList<String> twoCharList = Const.twoCharList;
		assertEquals("twoCharListのテスト(0)", twoCharList.get(0), "am");
		assertEquals("twoCharListのテスト(1)", twoCharList.get(1), "an");
		assertEquals("twoCharListのテスト(2)", twoCharList.get(2), "as");
		assertEquals("twoCharListのテスト(3)", twoCharList.get(3), "at");
		assertEquals("twoCharListのテスト(4)", twoCharList.get(4), "be");
		assertEquals("twoCharListのテスト(5)", twoCharList.get(5), "by");
		assertEquals("twoCharListのテスト(6)", twoCharList.get(6), "do");
		assertEquals("twoCharListのテスト(7)", twoCharList.get(7), "ex");
		assertEquals("twoCharListのテスト(8)", twoCharList.get(8), "go");
		assertEquals("twoCharListのテスト(9)", twoCharList.get(9), "he");
		assertEquals("twoCharListのテスト(10)", twoCharList.get(10), "hi");
		assertEquals("twoCharListのテスト(11)", twoCharList.get(11), "id");
		assertEquals("twoCharListのテスト(12)", twoCharList.get(12), "if");
		assertEquals("twoCharListのテスト(13)", twoCharList.get(13), "in");
		assertEquals("twoCharListのテスト(14)", twoCharList.get(14), "is");
		assertEquals("twoCharListのテスト(15)", twoCharList.get(15), "it");
		assertEquals("twoCharListのテスト(16)", twoCharList.get(16), "me");
		assertEquals("twoCharListのテスト(17)", twoCharList.get(17), "my");
		assertEquals("twoCharListのテスト(18)", twoCharList.get(18), "no");
		assertEquals("twoCharListのテスト(19)", twoCharList.get(19), "of");
		assertEquals("twoCharListのテスト(20)", twoCharList.get(20), "oh");
		assertEquals("twoCharListのテスト(21)", twoCharList.get(21), "ok");
		assertEquals("twoCharListのテスト(22)", twoCharList.get(22), "on");
		assertEquals("twoCharListのテスト(23)", twoCharList.get(23), "op");
		assertEquals("twoCharListのテスト(24)", twoCharList.get(24), "or");
		assertEquals("twoCharListのテスト(25)", twoCharList.get(25), "os");
		assertEquals("twoCharListのテスト(26)", twoCharList.get(26), "ow");
		assertEquals("twoCharListのテスト(27)", twoCharList.get(27), "ox");
		assertEquals("twoCharListのテスト(28)", twoCharList.get(28), "so");
		assertEquals("twoCharListのテスト(29)", twoCharList.get(29), "to");
		assertEquals("twoCharListのテスト(30)", twoCharList.get(30), "up");
		assertEquals("twoCharListのテスト(31)", twoCharList.get(31), "us");
		assertEquals("twoCharListのテスト(32)", twoCharList.get(32), "we");
		assertEquals("twoCharListのテスト(33)", twoCharList.get(33), "yo");
	}

}
