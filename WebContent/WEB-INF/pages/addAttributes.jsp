<%@page import="org.unibl.etf.ip.model.dto.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="categoryBean" type="org.unibl.etf.ip.model.beans.CategoryBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Dodavanje atributa</title>
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
		<h4>Dodavanje atributa</h4>
		<form action="Controller?action=addAttributes" method="post">
			<% for (int i = 0; i < (int) session.getAttribute("noAttributes"); i++) { %>
			<div class="mb-3 mt-3">
				<label class="form-label">Naziv: *</label>
				<input type="text" class="form-control" id="username"
					name=<%= "name" + i %> required />
			</div>
			<div class="mb-3 mt-3">
				<label class="form-label">Tip: *</label>
				<select class="form-select" required name=<%= "type" + i %>>
					<option value="String">String</option>
					<option value="Number">Broj</option>
				</select>
			</div>
			<hr />
			<% } %>
			<button type="submit" class="btn btn-primary">Dodajte</button>
		</form>
	</div>
</body>
</html>