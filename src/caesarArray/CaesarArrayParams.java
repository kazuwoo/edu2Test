package caesarArray;

import base.BaseParams;

/**
 * カエサル暗号（配列）パラメータクラス
 **/
public class CaesarArrayParams extends BaseParams {
	/**
	 * シフト数
	 */
	protected String shift;
	/**
	 * 入力文
	 */
	protected String target;
	/**
	 * 出力文
	 */
	protected String message;

	/**
	 * シフト数取得
	 * @return シフト数
	 */
	public String getShift() {
		return shift;
	}

	/**
	 * シフト数設定
	 * @param shift シフト数
	 */
	public void setShift(String shift) {
		this.shift = shift;
	}

	/**
	 * 入力文取得
	 * @return 入力文
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * 入力文取得
	 * @param target 入力文
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * 出力文取得
	 * @return 出力文
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 出力文設定
	 * @param message 出力文
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
