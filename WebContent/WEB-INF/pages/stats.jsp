<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.unibl.etf.ip.model.dto.Log"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="statsBean" type="org.unibl.etf.ip.model.beans.StatsBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Statistika</title>
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
		<h3>Logovi</h3>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Level</th>
						<th>Klasa</th>
						<th>Datum i vrijeme</th>
						<th>Poruka</th>
					</tr>
				</thead>
				<tbody>
					<% for (Log log : statsBean.getAll()) { %>
						<tr>
							<td class=<%= "ERROR".equals(log.getLevel()) ? "table-danger" : "table-info" %>><%= log.getLevel() %></td>
							<td><%= log.getLogger() %></td>
							<td><%= new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss").format(log.getDated()) %></td>
							<td><%= log.getMessage() %></td>
						</tr>
					<% } %>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>