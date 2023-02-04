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
<title>Dodavanje kategorije</title>
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
		<h4>Nova kategorija</h4>
		<form action="Controller?action=addCategory" method="post">
			<div class="mb-3 mt-3">
				<input type="text" class="form-control" id="username"
					placeholder="Naziv" name="name" required />
			</div>
			<div class="mb-3 mt-3">
				<select class="form-select" required name="parentId">
					<option value="0">/</option>
					<% for (Category category : categoryBean.getAll()) { %>
						<option value=<%= category.getId() %>><%= category.getName() %></option>
					<% } %>
				</select>
			</div>
			<div class="mb-3">
				<input type="number" min="1" class="form-control"
					placeholder="Broj atributa" name="noAttributes" required />
			</div>
			<button type="submit" class="btn btn-primary">Dalje</button>
		</form>
	</div>
</body>
</html>