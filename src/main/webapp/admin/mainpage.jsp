<%@page import="tw.com.eshop.dao.customermemberdaoimpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tw.com.eshop.entity.Customer_member"%>
<%@page import="tw.com.eshop.entity.Product"%>
<%@page import="tw.com.eshop.dao.productdaoimpl"%>
<%@page import="tw.com.eshop.entity.Product_enum"%>
<%@page import="tw.com.eshop.dao.productenumdaoimpl"%>
<%@page import="tw.com.eshop.dao.adminmemberdaoimpl"%>
<%@page import="tw.com.eshop.entity.Admin_member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js" data-integrity="sha512-ElRFoEQdI5Ht6kZvyzXhYG9NqjtkmlkfYk0wr6wHxU9JEHakS7UJZNeml5ALk+8IKlU6jDgMabC3vkumRokgJA==" data-crossorigin="anonymous" data-referrerpolicy="no-referrer"></script>
    <title>首頁</title>
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
    a:hover{
        text-decoration: none;
    }
    
    /*頁面內文*/
    #our_content{
        margin-top: 50px;
        padding-left: 50px;
        color: #3a3a3a;
        height: 585px;
    }
    .father{
        height: 150px;
        text-align: center;
    }
    @media(max-width:600px){
    #our_content{
    height: 700px;
   }
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
        <!--頁面內容-->
        <div class="col-sm-10" id="our_content">
           <h1>首頁</h1>
           <h3>經營狀況</h3>
                           <%
                customermemberdaoimpl custimpl = new customermemberdaoimpl();
                ArrayList<Customer_member> customer_member=custimpl.queryAll();
                
                productdaoimpl proimpl = new productdaoimpl();
                ArrayList<Product> proList=proimpl.queryAll();
                
				productenumdaoimpl proenimpl = new productenumdaoimpl();
				ArrayList<Product_enum> enumList = proenimpl.queryAll();
				
                adminmemberdaoimpl adminimpl = new adminmemberdaoimpl();
                ArrayList<Admin_member> admin_member=adminimpl.queryAll();
								%>
           <canvas id="myChart" width="400" height="100"></canvas>
<script>
const ctx = document.getElementById('myChart').getContext('2d');
const myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['顧客數', '商品數', '商品種類', '員工數'],
        datasets: [{
            label: '# of Votes',
            data: [<%=customer_member.size()%>, <%=proList.size()%>, <%=enumList.size()%>, <%=admin_member.size()%>],
            backgroundColor: [
                'rgba(255, 46, 95,0.6)',
                'rgba(255, 181, 77, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(112, 119, 255, 0.6)'
            ]
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
</script>
<hr style="border-width: 5px;">
           <h3>常用功能</h3>
        <div class="father">
         	<a href="product_add.jsp"><img src="img/add_product.png" style="height: 95%;"></a>
            <a href="product_search.jsp"><img src="img/search_product.png"  style="height: 95%;"></a>
			<a href="customer_search.jsp"><img src="img/manager_cust.png"  style="height: 95%;"></a>
			<a href="employee_search.jsp"><img src="img/manager_emp.png"  style="height: 95%;"></a>
            </div>
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