<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Dodavanje korisnika</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="css/styles.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="form container mt-3">
		<h4>Novi korisnik</h4>
		<form action="Controller?action=addUser" method="post">
			<div class="mb-3 mt-3">
				<input type="text" class="form-control"
					placeholder="Ime" name="firstName" required />
			</div>
			<div class="mb-3 mt-3">
				<input type="text" class="form-control"
					placeholder="Prezime" name="lastName" required />
			</div>
			<div class="mb-3 mt-3">
				<input type="text" class="form-control"
					placeholder="Korisničko ime" name="username" required />
			</div>
			<div class="mb-3 mt-3">
				<input type="password" class="form-control"
					placeholder="Lozinka" name="password" required />
			</div>
			<div class="mb-3 mt-3">
				<input type="url" class="form-control"
					placeholder="Avatar URL" name="avatarUrl" />
			</div>
			<div class="mb-3 mt-3">
				<input type="email" class="form-control"
					placeholder="E-mail" name="email" required />
			</div>
			<div class="mb-3">
				<input type="text" min="1" class="form-control"
					placeholder="Telefon" name="contactPhone" required />
			</div>
			<div class="mb-3 mt-3">
				<input type="text" class="form-control"
					placeholder="Grad" name="location" required />
			</div>
			<button type="submit" class="btn btn-primary">Dodajte</button>
		</form>
	</div>
</body>
</html>