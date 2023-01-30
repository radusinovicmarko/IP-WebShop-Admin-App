<%@page import="org.unibl.etf.ip.model.dto.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="categoryBean" type="org.unibl.etf.ip.model.beans.CategoryBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Kategorije</title>
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
		<h3>Kategorije</h3>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Ime</th>
						<th>Id natkategorije</th>
						<th>Akcije</th>
					</tr>
				</thead>
				<tbody>
					<% for (Category category : categoryBean.getAll()) { %>
						<tr>
							<td><%= category.getId() %></td>
							<td><%= category.getName() %></td>
							<td><%= category.getParentId() == 0 ? "/" : category.getParentId() %></td>
							<td class="action-cell">
								<button type="button" class="btn btn-primary">Promijenite</button>
								<button type="button" class="btn btn-primary" onclick=<%= "location.href=\"Controller?action=deleteCategory&id=" + category.getId() + "\"" %>>Obrišite</button>
							</td>
						</tr>
					<% } %>
				</tbody>
			</table>
		</div>
		<button type="button" class="btn btn-primary" onclick="location.href='Controller?action=addCategory'">Dodajte</button>
	</div>
</body>
</html>