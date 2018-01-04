<!--
// -------------------------------
// 共通系JavaSript
// -------------------------------

$(function() {
	// 暗号化選択
	$('#doEncryption').click(function (){
		$("#shift").prop('disabled', false);
	});
	// 復号化選択
	$('#doDecryption').click(function (){
		$("#shift").prop('disabled', false);
	});
	// 暗号解読選択
	$('#doCryptanalysis').click(function (){
		$("#shift").prop('disabled', true);
	});
});
// -->
