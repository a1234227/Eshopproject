<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <title>員工登入</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" data-integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" data-crossorigin="anonymous">
  <style>
    @import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css");
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@400&display=swap');

* {
    font-family: 'Noto Sans TC', sans-serif;
}
    .container{
      margin-top: 135px;
      text-align: center;
    }
    .container h1{
      color:#556D9C;
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
    <div class="container">
      <div class="row">
        <div class="col-sm">

        </div>
        <div class="col-sm">
          <h1>員工登入</h1>
          <br>
          <br>
          <form action="login" method="POST">
            <div class="form-group row">
              帳號
              <input type="text" class="form-control" name="employeeAccount">
          
            </div>
            <div class="form-group row">
              密碼
              <input type="password" class="form-control" name="employeePassword">
            </div>
            <br>            
            <button type="submit" class="btn btn-primary" value="login" name="buttonAction">登入</button>　　　　　　　　<!-- 請不要刪除這行全形空格，按鈕會跑版 -->
          	<button type="submit" class="btn btn-danger" value="register" name="buttonAction">註冊</button>
          </form>
          	<script>
		    var errori ='<%=request.getParameter("error")%>'
		    if(errori=='accountnotvaild' || errori=='deactive'){
		    	alert("此帳號無效，請洽詢管理員!");
		    }else if(errori=='config.admin'){
		    	alert("伺服器錯誤，請洽詢管理員!\n錯誤代碼：config.admin");
		    }else if(errori=='chance:4'){
		    	alert("登入失敗，如登入失敗達五次此帳號將會鎖定，目前還剩下4次機會!");
		    }else if(errori=='chance:3'){
		        alert("登入失敗，如登入失敗達五次此帳號將會鎖定，目前還剩下3次機會!");
		    }else if(errori=='chance:2'){
		        alert("登入失敗，如登入失敗達五次此帳號將會鎖定，目前還剩下2次機會!");
		    }else if(errori=='chance:1'){
		        alert("登入失敗，如登入失敗達五次此帳號將會鎖定，目前還剩下1次機會!");
		    }else if(errori=='restrict'){
		        alert("登入失敗超過次數，帳號已被鎖定，請洽管理員！");
		    }else if(errori=='restricted'){
		    	alert("此帳號已鎖定，請於鎖定解除後再重試！");
		    }
		    var errori ='<%=request.getParameter("register")%>'
			    if(errori=='yes'){
			        alert("註冊成功！");
			    }
			</script>
        </div>
        <div class="col-sm">

        </div>
      </div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" data-integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" data-crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" data-integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" data-crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" data-integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" data-crossorigin="anonymous"></script>
  </body>
</html>