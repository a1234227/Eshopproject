<%@page import="java.util.ArrayList"%>
<%@page import="tw.com.eshop.entity.Product_enum"%>
<%@page import="tw.com.eshop.dao.productenumdaoimpl"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js" data-integrity="sha512-ElRFoEQdI5Ht6kZvyzXhYG9NqjtkmlkfYk0wr6wHxU9JEHakS7UJZNeml5ALk+8IKlU6jDgMabC3vkumRokgJA==" data-crossorigin="anonymous" data-referrerpolicy="no-referrer"></script>
<title>商品種類管理</title>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="main.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	data-integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	data-crossorigin="anonymous">
<style>
<%@include file ="main.css"%>
	/*全部連結hover效果*/ a:hover {
	text-decoration: none;
}
/*頁面內文*/
#our_content {
	margin-top: 10px;
	color: #3a3a3a;
	text-align: center;
	padding-bottom: 425px;
}

.btn-secondary {
	border-style: none;
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

.btn-danger:hover {
	background: linear-gradient(260.76deg, #ca7a89 -0.06%, #b84d62 96.04%);
	color: #c9c9c9;
	transition: 1s;
}
</style>
</head>
<body>
<!--導航條-->
<%@include file="topnav.jsp"%>
	<!--頂欄以下的頁面-->
	<div class="container-fluid">
		<div class="row" id="total_content">
			<!--側欄-->
			<div class="col-sm-2" id="our_sidenav">
				<%@include file="sidenav.jsp"%>
			</div>
<%
productenumdaoimpl proenimpl = new productenumdaoimpl();
ArrayList<Product_enum> enumList = proenimpl.queryAll();
%>

			<!--頁面內容-->
			<div class="col-sm-10" id="our_content">
				<h3>商品種類管理</h3>
				<br>
				<p><span style="background-color: #7683a8;padding: 10px;border-radius: 1px;color: #FFFFFF">總共 <%=enumList.size()%> 個商品種類</span></p>
				
				<div style="text-align: right; width: 825px;">
					<a href="kind_add.jsp"><button class="btn btn-danger">新增商品種類</button></a>
				</div>
				<br>
				<div class="container">
					<div class="row">
						<div class="col-sm-3"></div>
						<div class="col-sm-6">
							<table class="table">
								<thead class="thead">
									<tr>
										<th scope="col">編號</th>
										<th scope="col">名稱</th>
										<th scope="col">更新</th>
										<th scope="col">刪除</th>
									</tr>
								</thead>
								<tbody>
									<!-- 要這樣打印，必須為這個頁面寫個"productkindservlet"，並setAttribute，商品與顧客查詢頁面同理 -->
									<!-- 幫我留意一下這邊ArrayList getAttribute名稱是否正確 -->



									<%									

									
									for (int i = 0; i < enumList.size(); i++) {
									%>

									<tr>
										<th scope="row"><%=enumList.get(i).getEnum_id()%></th>
										<td><%=enumList.get(i).getEnum_name()%></td>

										<!-- 打印更新連結 -->
										<td><a
											href="kind_update.jsp?enum_id=<%=enumList.get(i).getEnum_id()%>&enum_name=<%=enumList.get(i).getEnum_name()%>"
											style="color: #ff5773;"> 更新 </a></td>

										<!-- 打印刪除連結，進度問題暫時沒寫彈出窗 -->
										<td><a
											href="prokindcud?enum_id=<%=enumList.get(i).getEnum_id()%>&buttonAction=delete"
											style="color: #ff5773;"> 刪除 </a></td>

									</tr>
									<%
									}
									%>
								</tbody>
							</table>
							<script>
				   				var proadd ='<%=request.getParameter("add")%>'
								if (proadd == 'no') {
									alert("添加資料失敗！");
								} else if (proadd == 'yes') {
									alert("添加資料成功！");
								} else if (proadd == 'duplicate') {
									alert("已有相同的產品種類！")};
								var prodelete ='<%=request.getParameter("delete")%>'
								if (prodelete == 'no') {
									alert("刪除資料失敗！");
								} else if (prodelete == 'yes') {
									alert("資料以成功刪除！");
								};
								var proupdate ='<%=request.getParameter("update")%>'
									if (proupdate == 'no') {
									alert("更新資料失敗！");
								} else if (proupdate == 'yes') {
									alert("資料以成功更新！");
								};
							</script>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>


			</div>
			<footer class="container-fluid" id="our_footer">Powered by
				7team</footer>
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