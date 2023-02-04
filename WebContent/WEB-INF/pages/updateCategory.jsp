<%@page import="org.unibl.etf.ip.model.dto.Attribute"%>
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
<title>Promjena kategorije</title>
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
		<h4>Promjena podataka</h4>
		<form action="Controller?action=updateCategory" method="post">
			<div class="mb-3 mt-3">
				<input type="text" class="form-control" value=<%= categoryBean.getUpdateCategory().getName() %>
					placeholder="Naziv" name="name" required />
			</div>
			<button type="submit" class="btn btn-primary">Promijenite</button>
		</form>
		<br />
		<h4>Atributi</h4>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Ime</th>
						<th>Tip</th>
						<th>Akcija</th>
					</tr>
				</thead>
				<tbody>
					<% for (Attribute attribute : categoryBean.getUpdateCategoryAttributes()) { %>
						<tr>
							<td><%= attribute.getName() %></td>
							<td><%= "String".equals(attribute.getType()) ? "String" : "Broj" %></td>
							<td class="action-cell">
								<!--<button type="button" class="btn btn-primary" onclick=<%= "location.href=\"Controller?action=updateAttribute&id=" + attribute.getId() + "\"" %>>Promijenite</button> -->
								<button type="button" class="btn btn-primary" onclick=<%= "location.href=\"Controller?action=deleteAttribute&id=" + attribute.getId() + "\"" %>>Obrišite</button>
							</td>
						</tr>
					<% } %>
				</tbody>
			</table>
		</div>
		<button type="button" class="btn btn-primary" onclick="location.href='Controller?action=addAttribute'">Dodajte</button>
	</div>
</body>
</html>