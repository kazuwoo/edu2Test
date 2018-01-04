package base;

/**
 * パラメータの基底クラス
 */
public class BaseParams {
	/**
	 * タイトル
	 */
	protected String title;
	/**
	 * 操作
	 */
	protected Operation operation;

	/**
	 * タイトル取得
	 * @return タイトル
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タイトル設定
	 * @param title タイトル
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 操作取得
	 * @return 操作
	 */
	public Operation getOperation() {
		return operation;
	}

	/**
	 * 操作設定
	 * @param action 操作
	 */
	public void setOperation(Operation action) {
		this.operation = action;
	}

	/**
	 * 操作判定（暗号化）
	 * @return 操作が復号化、暗号解読以外ならtrue
	 */
	public boolean isOperationEncryption() {
		if (isOperationDecryption()) {
			return false;
		}
		if (isOperationCryptanalysis()) {
			return false;
		}
		return true;
	}

	/**
	 * 操作判定（復号化）
	 * @return 操作が復号化ならtrue
	 */
	public boolean isOperationDecryption() {
		if (Operation.doDecryption.equals(this.operation)) {
			return true;
		}
		return false;
	}

	/**
	 * 操作判定（暗号解読）
	 * @return 操作が暗号解読ならtrue
	 */
	public boolean isOperationCryptanalysis() {
		if (Operation.doCryptanalysis.equals(this.operation)) {
			return true;
		}
		return false;
	}
}
