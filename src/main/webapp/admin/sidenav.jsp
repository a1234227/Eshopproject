<%@page import="java.io.PrintWriter"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <title>Title</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
     <style>
         /*全部連結hover效果*/
	a:hover {
	    text-decoration: none;
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
.btn-secondary{
        border-style: none;
}
  </style>
  </head>

  <body>
            <!--商品選單-->
            <details open id="detail_text">
                <summary>商品<img src="img/polygon.png" id="polygon"></summary>
                <div id="list_item"><a href="product_search.jsp"><i class="bi bi-bag-fill" style="color: #ff4049;"></i>商品清單管理/查詢</a></div>
                <div id="list_item"><a href="product_add.jsp"><i class="bi bi-clipboard-plus-fill" style="color: #ff5e0d;"></i>新增商品</a></div>
				<div id="list_item"><a href="product_kind.jsp"><i class="bi bi-box2-heart-fill" style="color: #ffa20d;"></i>商品種類管理</a></div><!-- kinddeleteservlet -->
            </details>
            <!--顧客選單-->
            <details open id="detail_text">
                <summary>顧客<img src="img/polygon.png" id="polygon"></summary>
                <div id="list_item"><a href="customer_search.jsp"><i class="bi bi-person-lines-fill" style="color: #669e1e;"></i>顧客清單管理/查詢</a></div>
            </details>
            <!--訂單選單
            <details open id="detail_text">
                <summary>訂單<img src="img/polygon.png" id="polygon"></summary>
                <div id="list_item"><a href="order_search.jsp">訂單清單管理/查詢</a></div>
            </details>-->
            <!--員工選單-->
            <details open id="detail_text">
                <summary>員工<img src="img/polygon.png" id="polygon"></summary>
                <div id="list_item"><a href="employee_search.jsp"><i class="bi bi-filter-square-fill" style="color: #4363e0;"></i>員工清單管理/查詢</a></div>
                <div id="list_item"><a href="employee_add.jsp"><i class="bi bi-plus-circle-fill" style="color: #7543e0;"></i>新增員工</a></div>
            </details>
            <%
            HttpSession httpSession = request.getSession();
            %>
            <br>
            <span style="padding: 5px;margin-left:50px; background-color: #4c5a7d;color: #FFFFFF;border-radius: 3px;">歡迎 <%= (String)httpSession.getAttribute("adminname")%>!</span>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  </body>
</html>