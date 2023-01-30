<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Prijava</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="css/login.css" rel="stylesheet" />
</head>
<body>
	<div class="form container mt-3">
		<h4>Administratorska aplikacija</h4>
		<form action="Controller?action=login" method="post">
			<div class="mb-3 mt-3">
				<input type="text" class="form-control" id="username"
					placeholder="Korisničko ime" name="username" required />
			</div>
			<div class="mb-3">
				<input type="password" class="form-control" id="pwd"
					placeholder="Lozinka" name="password" required />
			</div>
			<button type="submit" class="btn btn-primary">Prijavite se</button>
		</form>
		<% if (session.getAttribute("loginMessage") != null) { %>
			<div style="margin-top: 20px" class="alert alert-danger">
    			<%= (String) session.getAttribute("loginMessage") %>
  			</div>
		<% } %>
	</div>
</body>
</html>