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

	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
