<%@page import="tw.com.eshop.dao.customermemberdaoimpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tw.com.eshop.entity.Customer_member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <title>顧客清單管理/查詢</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="main.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" data-integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" data-crossorigin="anonymous">
<style>
<%@include file="main.css"%>
    /*全部連結hover效果*/
a:hover {
    text-decoration: none;
}
        /*頁面內文*/
    #our_content{
        margin-top: 10px;
        color: #3a3a3a;
        padding-left: 50px;
        padding-bottom:230px ;
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

    <!--側欄以下的頁面-->
<div class="container-fluid">
    <div class="row" id="total_content">
        <!--側欄-->
        <div class="col-sm-2" id="our_sidenav">
            <%@include file="sidenav.jsp"%>
        </div>
        <!--頁面內容-->
        <div class="col-sm-10" id="our_content">
          <!--查詢功能-->
                <h3>顧客清單管理/查詢</h3>
                <br>
                <div id="wfix">
                    <form action="custrud" method="POST">
                        <div class="form-group row"><div class="col-sm-3">
依<select class="custom-select" name="barvalue">
									<option value="姓名" selected>姓名</option>
									<option value="編號">編號</option>
									<option value="帳號">帳號</option>
									<option value="地址">地址</option></select>查詢
									<input class="form-control form-control-sm" type="text" name="inputlabel" required="required"><br>
									<button type="submit" class="btn btn-primary" value="search" name="buttonAction">送出</button>
                        </div></div>
                        
                    </form>
                    
            </div>
            <!--查詢表格-->
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">編號</th>
                        <th scope="col">帳號</th>
                        <th scope="col">姓名</th>
                        <th scope="col">地址</th>
                        <th scope="col">更新</th>
                        <th scope="col">刪除</th>
                    </tr>
                </thead>
                <tbody>
                <%
                customermemberdaoimpl custimpl = new customermemberdaoimpl();
                ArrayList<Customer_member> customer_member=(ArrayList<Customer_member>)request.getAttribute("custList");
				for (int i = 0; i < customer_member.size(); i++) {
				%>
                
                    <tr>
                        <th scope="row"><%=customer_member.get(i).getCust_id()%></th>
                        <td><%=customer_member.get(i).getCust_account()%></td>
                        <td><%=customer_member.get(i).getCust_name()%></td>
                        <td><%=customer_member.get(i).getCust_address()%></td>
                        
                    	<!-- 打印更新連結 -->
                    	<td>
                    	<a href="customer_update.jsp?cust_id=<%=customer_member.get(i).getCust_id()%>&cust_name=<%=customer_member.get(i).getCust_name()%>&cust_account=<%=customer_member.get(i).getCust_account()%>&cust_password=<%=customer_member.get(i).getCust_password()%>&cust_adress=<%=customer_member.get(i).getCust_address()%>">
                    	更新
                    	</a>
                    	</td>

						<td>
						<a href="customerdeleteservlet?cust_id=<%=customer_member.get(i).getCust_id()%>&status=delete">
						刪除
						</a>
						</td>
                    </tr>
                    <%
                    }
                    %>
                    </tbody>
            </table>
            <script>
			var custdelete ='<%=request.getParameter("delete")%>'
			if (custdelete == 'no') {
			alert("刪除資料失敗！");
			} else if (custdelete == 'yes') {
			alert("資料已經刪除成功！");};
			var custupdate ='<%=request.getParameter("update")%>'
			if (custupdate == 'no') {
			alert("更新資料失敗！");
			} else if (custupdate == 'yes') {
			alert("資料已經更新成功！");};
			</script>
            
        </div>
            
           
        
            <footer class="container-fluid" id="our_footer">Powered by 7team</footer>
    </div>

</div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" data-integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" data-crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" data-integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" data-crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" data-integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" data-crossorigin="anonymous"></script>
  </body>
</html>