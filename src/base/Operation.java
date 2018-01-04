package base;

/**
 * 操作定義
 */
public enum Operation {

	/** 暗号化 */
	doEncryption
	/** 復号化 */
	,doDecryption
	/** 解読 */
	,doCryptanalysis
	/** 不明 */
	,unknown
	;
	/**
	 * Action解析<br>
	 * 解析できない場合はunknownを返す。
	 * @param value
	 * @return Action
	 */
	public static Operation defaultValueOf(String value) {
		Operation action = null;
		try {
			action = Operation.valueOf(value);
		} catch (IllegalArgumentException e) {
			action = unknown;
		}
		return action;
	}
}
