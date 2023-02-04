<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="userBean" type="org.unibl.etf.ip.model.beans.UserBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Promjena korisnika</title>
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
		<h4>Promjena podataka</h4>
		<form action="Controller?action=updateUser" method="post">
			<div class="mb-3 mt-3">
				<input type="text" class="form-control" value="<%= userBean.getUser().getFirstName() %>"
					placeholder="Ime" name="firstName" required />
			</div>
			<div class="mb-3 mt-3">
				<input type="text" class="form-control" value="<%= userBean.getUser().getLastName() %>"
					placeholder="Prezime" name="lastName" required />
			</div>
			<div class="mb-3 mt-3">
				<input type="text" class="form-control" value="<%= userBean.getUser().getUsername() %>"
				placeholder="Korisničko ime" name="username" required />
			</div>
			<div class="mb-3 mt-3">
				<input type="password" class="form-control"
					placeholder="Nova lozinka" name="password" />
			</div>
			<div class="mb-3 mt-3">
				<input type="url" class="form-control" placeholder="Avatar URL" name="avatarUrl" 
					<%= userBean.getUser().getAvatarUrl() == null ? "" : "value='" + userBean.getUser().getAvatarUrl() + "'"  %>/>
			</div>
			<div class="mb-3 mt-3">
				<input type="email" class="form-control" value="<%= userBean.getUser().getEmail() %>"
					placeholder="E-mail" name="email" required />
			</div>
			<div class="mb-3">
				<input type="text" class="form-control" value="<%= userBean.getUser().getContactPhone() %>"
					placeholder="Telefon" name="contactPhone" required />
			</div>
			<div class="mb-3 mt-3">
				<input type="text" class="form-control" value="<%= userBean.getUser().getLocation() %>"
					placeholder="Grad" name="location" required />
			</div>
			<div class="mb-3 mt-3">
				<input type="checkbox" class="form-check-input" <%= userBean.getUser().isActivated() ? "checked" : "" %>
					name="activated" />
				<label class="form-check-label">Aktiviran</label>
			</div>
			<div class="mb-3 mt-3">
				<input type="checkbox" class="form-check-input" <%= userBean.getUser().isDeleted() ? "checked" : "" %>
					name="deleted" />
				<label class="form-check-label">Obrisan</label>
			</div>
			<button type="submit" class="btn btn-primary">Promijenite</button>
		</form>
	</div>

</body>
</html>