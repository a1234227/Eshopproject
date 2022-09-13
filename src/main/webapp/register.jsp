<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
<title>員工註冊</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	data-integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	data-crossorigin="anonymous">
<style>
@import
	url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css")
	;

@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@400&display=swap')
	;

* {
	font-family: 'Noto Sans TC', sans-serif;
}

.container {
	margin-top: 60px;
	text-align: center;
}

.container h1 {
	color: #556D9C;
}

.btn-primary {
	background: linear-gradient(260.76deg, #8BC6EC -0.06%, #9599E2 96.06%);
	border-style: none;
}

.btn-primary:hover {
	background: linear-gradient(260.76deg, #6e9cbb -0.06%, #7376af 96.06%);
	color: #c9c9c9;
	transition: 1s;
}

.btn-danger {
	background: linear-gradient(260.76deg, #FF99AC -0.06%, #FF6A88 96.04%);
	border-style: none;
}
.btn-secondary{
	border-style: none;
}
.btn-danger:hover {
	background: linear-gradient(260.76deg, #ca7a89 -0.06%, #b84d62 96.04%);
	color: #c9c9c9;
	transition: 1s;
}

#notice {
	color: #ff6493;
	font-size: 12px;
	padding-left: 5px;
}
</style>
</head>

<body>

	<div class="container">
	<div style="text-align: left;margin-left: 80px;color:#639fff; font-size: 18px;"><a href="index.jsp" style="text-decoration: none"><img src="img/back.png">返回首頁</a></div>
		<div class="row">
			<div class="col-sm"></div>
			
			<div class="col-sm">
				<h1>員工註冊</h1>
				<br>
				<form action="register" method="POST">
					<div class="form-group row">
						帳號
						<input type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" class="form-control" name="employeeAccount"
							 placeholder="必填，請輸入英文與數字" required="required" pattern="[a-z0-9A-Z]{0,20}" title="必填，請輸入20位以下的英文與數字">

					</div>
					<div class="form-group row">
						密碼
						<input type="password" onkeyup="this.value=this.value.replace(/\s+/g,'')" class="form-control"
							name="employeePassword" placeholder="必填，請輸入至少6位的英文與數字" required="required" pattern="[a-zA-Z0-9]{6,20}" title="必填，請輸入6位以上20位以下的英文與數字">
					</div>
					<div class="form-group row">
						姓名
						<input type="text"  class="form-control" name="employeeName" placeholder="必填" required="required">
					</div>
					<div class="form-group row">
						連絡電話 <input type="tel" onkeyup="this.value=this.value.replace(/\s+/g,'')" class="form-control" name="employeePhone" placeholder="必填，請輸入數字" required="required" pattern="[0-9]{0,}" title="必填，請輸入數字">
					</div>
					<br>
					<button type="submit" class="btn btn-primary" value="register"
						name="buttonAction">送出</button>　　　　　　　　<!-- 請不要刪除這行全形空格，按鈕會跑版 -->
					<button type="reset" class="btn btn-secondary">重填</button>
					<!--重填就清空表單-->

					
				</form>
				<script>
				 var itemadd ='<%=request.getParameter("add")%>';
					if (itemadd == 'no') {
						alert("添加資料失敗！");
					}else if (itemadd == 'duplicate') {
						alert("您註冊的帳號已經存在！");
					}else if (itemadd == 'empty'){
						alert("您有欄位沒有填寫！");}
				</script>
			</div>
			<div class="col-sm"></div>
		</div>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		data-integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		data-crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		data-integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		data-crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		data-integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		data-crossorigin="anonymous"></script>
</body>

</html>