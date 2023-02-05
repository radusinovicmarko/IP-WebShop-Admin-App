<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
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
				<label class="form-label">Ime: *</label> <input type="text"
					class="form-control" name="firstName" required />
			</div>
			<div class="mb-3 mt-3">
				<label class="form-label">Prezime: *</label> <input type="text"
					class="form-control" name="lastName" required />
			</div>
			<div class="mb-3 mt-3">
				<label class="form-label">Korisničko ime: *</label> <input type="text"
					class="form-control" name="username" required />
			</div>
			<div class="mb-3 mt-3">
				<label class="form-label">Lozinka: *</label> <input type="password"
					class="form-control" name="password" required />
			</div>
			<div class="mb-3 mt-3">
				<label class="form-label">Avatar URL:</label> <input type="url"
					class="form-control" name="avatarUrl" />
			</div>
			<div class="mb-3 mt-3">
				<label class="form-label">E-mail adresa: *</label> <input type="email"
					class="form-control" name="email" required />
			</div>
			<div class="mb-3">
				<label class="form-label">Telefon: *</label> <input type="text"
					class="form-control" name="contactPhone" required />
			</div>
			<div class="mb-3 mt-3">
				<label class="form-label">Grad: *</label> <input type="text"
					class="form-control" name="location" required />
			</div>
			<button type="submit" class="btn btn-primary">Dodajte</button>
		</form>
	</div>
</body>
</html>