<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="base.Util" %>
<%@ page import="base.Operation" %>
<jsp:useBean id="params" class="base.BaseParams" scope="request" />

<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><jsp:getProperty name="params" property="title" /></title>
		<!-- BootstrapのCSS読み込み -->
		<link href="dist/bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<link href="dist/bootstrap3-ie10-viewport-bug-workaround/ie10-viewport-bug-workaround.css" rel="stylesheet">
		<!-- jQuery読み込み -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<!-- BootstrapのJS読み込み -->
		<script src="dist/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
		<!-- Bootstrap Datepicker読み込み -->
		<link rel="stylesheet" type="text/css" href="dist/bootstrap-datepicker-1.6.1-dist/css/bootstrap-datepicker.min.css">
		<script type="text/javascript" src="dist/bootstrap-datepicker-1.6.1-dist/js/bootstrap-datepicker.min.js"></script>
		<script type="text/javascript" src="dist/bootstrap-datepicker-1.6.1-dist/locales/bootstrap-datepicker.ja.min.js"></script>

		<!-- 個別CSS、JS読み込み -->
		<link href="css/style.css?date='<%= Util.getLastModified(config.getServletContext().getRealPath("css/style.css")) %>'" rel="stylesheet">
		<script type="text/javascript" src="js/default.js?date='<%= Util.getLastModified(config.getServletContext().getRealPath("js/default.js")) %>'"></script>

	</head>
	<body>
	<div class="container">
		<div>
			<div class="main">

				<nav class="navbar navbar-default navbar-fixed-top">
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#" onclick="javascript:document.forms['showMainMenuForm'].submit();">メインメニュー</a></li>
						</ul>
					</div>
				</nav>

				<header>
					<h1><jsp:getProperty name="params" property="title" /></h1>
				</header>

				<article>

					<form role="form" name="doRegistrationForm" method="POST" action="CaesarArray">
						<section class="inputBox">

							<div class="row">
								<div class="form-group">
									<div class="col-sm-1 input-label">
										<label for="target">入力</label>
									</div>
									<div class="col-sm-11">
										<textarea class="form-control" rows="8" id="target" name="target"><jsp:getProperty name="params" property="target" /></textarea>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="form-group">
									<div class="col-sm-1 input-label">
										<label for="operation">操作</label>
									</div>
									<div class="col-sm-8">
										<label class="radio-inline">
											<input type="radio" name="operation" value="<%=Operation.doEncryption%>"
											id="doEncryption"
											<%= params.isOperationEncryption() ? "checked" : "" %>>
											暗号化
										</label>
										<label class="radio-inline">
											<input type="radio" name="operation" value="<%=Operation.doDecryption%>"
											id="doDecryption"
											<%= params.isOperationDecryption() ? "checked" : "" %>>
											復号化
										</label>
										<label class="radio-inline">
											<input type="radio" name="operation" value="<%=Operation.doCryptanalysis%>"
											id="doCryptanalysis"
											<%= params.isOperationCryptanalysis() ? "checked" : "" %>>
											暗号解読
										</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<div class="col-sm-1 input-label">
										<label for="shift">シフト数</label>
									</div>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="shift" name="shift"
											value="<jsp:getProperty name="params" property="shift" />">
									</div>
									<div>
										<button type="submit" class="btn btn-primary">　実行　</button>
									</div>
								</div>
							</div>
						</section>

						<section class="outputBox">
							<div class="row">
								<div class="form-group">
									<div class="col-sm-1 input-label">
										<label for="message">結果</label>
									</div>
									<div class="col-sm-11">
										<textarea class="form-control" rows="8" id="message" name="message" readonly="readonly"><jsp:getProperty name="params" property="message" /></textarea>
									</div>
								</div>
							</div>

						</section>
					</form>
				</article>

				<footer>
					<div class="copyright">
						Copyright 2018 ARKSYSTEM.
					</div>
				</footer>
			</div><!-- end of main -->
		</div>
	</div>
	</body>
</html>