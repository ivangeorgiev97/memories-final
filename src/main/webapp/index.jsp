<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memories | login</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
    integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous" />
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div class="container">
		<h3>Добре дошли</h3>
	    <form id="loginForm" action="login" method="POST">
	        <div class="form-group">
	            <label for="username">Потребител</label>
	            <div class="input-group">
	                <input type="text" class="form-control" id="username" name="username" placeholder="Потребител" minlength="4" maxlength="254">
	            </div>
	        </div>
	        <div class="form-group">
	            <label for="password">Парола</label>
	            <div class="input-group">
	                <input type="password" class="form-control" id="password" name="password" placeholder="Парола" minlength="4" maxlength="254">
	            </div>
	        </div>
	        <input type="submit" class="btn btn-primary" value="Влез" />
	    </form>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>
    <script src="js/users.js"></script>
</body>
</html>
