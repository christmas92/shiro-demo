<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>shiro</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  </head>
  <body>
<div class="container" style="width: 30%">
  <div class="form-group">
    <label for="username">username</label>
    <input name="username" type="text" class="form-control" id="username" placeholder="Username">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input name="password" type="password" class="form-control" id="password" placeholder="Password">
  </div>
  <div class="checkbox">
    <label for="rememberMe">
      <input type="checkbox" name="rememberMe" id="rememberMe" value="1"> 记住密码
    </label>
  </div>
  <button id="submit" type="submit" class="btn btn-default">Submit</button>
</div>
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>  
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  <script type="text/javascript">
    $("#submit").click(function(){
      $.ajax({
        type: "POST",
        url: "/shiro/login",
        data: {
        	username: $("input[name='username']").val(),
        	password: $("input[name='password']").val(),
        	rememberMe: $("input[name='rememberMe']:checked").val()
        },
        dataType: "json",
        success: function (data) {
          if (data == true) {
            window.location.href = "/shiro/index"
          } else {
            console.log(data)
          }
        },
        error: function (xhr, t) {
          console.log(xhr)
          console.log(t)
        }
      })
    })

  </script>
  </body>
</html>