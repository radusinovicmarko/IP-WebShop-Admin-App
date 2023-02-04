<%@page import="org.unibl.etf.ip.model.dto.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="userBean" type="org.unibl.etf.ip.model.beans.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Korisnici</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="css/styles.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container mt-3">
		<h3>Korisnici</h3>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Ime</th>
						<th>Prezime</th>
						<th>Korisničko ime</th>
						<th>Avatar URL</th>
						<th>E-mail</th>
						<th>Aktiviran</th>
						<th>Telefon</th>
						<th>Grad</th>
						<th>Obrisan</th>
						<th>Akcija</th>
					</tr>
				</thead>
				<tbody>
					<% for (User user : userBean.getAll()) { %>
						<tr>
							<td><%= user.getFirstName() %></td>
							<td><%= user.getLastName() %></td>
							<td><%= user.getUsername() %></td>
							<td>
								<% if (user.getAvatarUrl() != null) { %>
									<a href=<%= user.getAvatarUrl() %> target="_blank">Avatar</a>
								<% } else { %>
									/
								<% } %>
							</td>
							<td><%= user.getEmail() %></td>
							<td><input class="form-check-input" type="checkbox" <%= user.isActivated() ? "checked" : "" %> disabled /></td>
							<td><%= user.getContactPhone() %></td>
							<td><%= user.getLocation() %></td>
							<td><input class="form-check-input" type="checkbox" <%= user.isDeleted() ? "checked" : "" %> disabled /></td>
							<td class="action-cell">
								<a class="btn btn-primary" href=<%= "\"Controller?action=updateUser&id=" + user.getId() + "\"" %>>Promijenite</a>
								<a class="btn btn-primary" href=<%= "\"Controller?action=deleteUser&id=" + user.getId() + "\"" %>>Obrišite</a>
							</td>
						</tr>
					<% } %>
				</tbody>
			</table>
		</div>
		<a class="btn btn-primary" href="Controller?action=addUser">Dodajte</a>
	</div>
</body>
</html>