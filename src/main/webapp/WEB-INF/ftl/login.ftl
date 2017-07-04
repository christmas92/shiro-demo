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
    <label for="exampleInputEmail1">username</label>
    <input name="username" type="text" class="form-control" id="exampleInputEmail1" placeholder="Username">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
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
        data: {username: $("input[name='username']").val(),password: $("input[name='password']").val()},
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