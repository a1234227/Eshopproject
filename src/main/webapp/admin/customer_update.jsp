<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tw.com.eshop.entity.Customer_member"%>
<!doctype html>
<html lang="en">
  <head>
    <title>更新顧客資訊</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="main.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" data-integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" data-crossorigin="anonymous">
<style>
<%@include file="main.css" %>
    /*全部連結hover效果*/
	a:hover {
	    text-decoration: none;
	}
    /*頁面內文*/
    #our_content{
        margin-top: 10px;
        color: #3a3a3a;
        text-align: center;
	padding-bottom: 365px;
    }

    .btn-secondary{
            border-style: none;
    }
    
    .btn-primary{
        background: linear-gradient(260.76deg, #8BC6EC -0.06%, #9599E2 96.06%);
        border-style: none;
    }

    .btn-primary:hover{
        background: linear-gradient(260.76deg, #6e9cbb -0.06%, #7376af 96.06%);
        color: #c9c9c9;
        transition: 1s;
    }
    .btn-danger{
    background: linear-gradient(260.76deg, #FF99AC -0.06%, #FF6A88 96.04%);
    border-style: none;
    }
    .btn-danger:hover{
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
           <h3>更新顧客資訊</h3>
           <br>
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                 
                </div>
                <div class="col-sm-6">
                <form action="custrud" method="POST">
                    <div class="form-group row">
                        顧客姓名
                        <div class="col-sm-10">
                            <input class="form-control form-control-sm" type="text" name="cust_name" value="<%=request.getParameter("cust_name")%>" required="required">
                        </div>
                    </div>                   
                    <div class="form-group row">
                        顧客帳號
                        <div class="col-sm-10">
                            <input class="form-control form-control-sm" type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" name="cust_account" value="<%=request.getParameter("cust_account")%>" required="required" pattern="[a-z0-9A-Z]{0,20}" title="必填，請輸入20位以下的英文與數字">
                        </div>
                    </div>  
                    <div class="form-group row">
                        顧客密碼
                        <div class="col-sm-10">
                            <input class="form-control form-control-sm" type="password" onkeyup="this.value=this.value.replace(/\s+/g,'')" name="cust_password" value="<%=request.getParameter("cust_password")%>" required="required">
                        </div>
                    </div> 
                    <div class="form-group row">
                        顧客地址
                        <div class="col-sm-10">
                            <input class="form-control form-control-sm" type="text" name="cust_address" value="<%=request.getParameter("cust_address")%>" required="required">
                        </div>
                    </div>
                    <div id="our_button">
                        <br><button type="submit" class="btn btn-primary">送出</button>                        
                        　　　　　　　　　　　<!-- 請不要刪除這行全形空格，按鈕會跑版 -->
                        <button type="reset" class="btn btn-secondary">取消重填</button>
                        <input type="hidden" name="buttonAction" value="update">
						<input type="hidden" name="cust_id" value="<%=request.getParameter("cust_id")%>">
                    </div>
                </form>
                <!-- 暫時沒寫更新提示JS -->
                </div>
                <div class="col-sm-3">
                    
                </div>
            </div>
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