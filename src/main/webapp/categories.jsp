<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memories | Categories</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous" />
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="container">
		<div id="main-container" class="container bg-light p-5">
			<div class="row">
				<div class="col-xs-12 col-md-4" id="left">
					<div class="spinner-border" id="loading-spinner" role="status"
						style="display: none;">
						<span class="sr-only">Loading...</span>
					</div>
					<form id="add-form" name="add-form">
						<div class="form-group">
							<label for="name">Заглавие:</label> <input type="text"
								class="form-control" id="name" placeholder="Име" minlength="2" />
						</div>
						<button id="submit-btn" type="button" class="btn btn-primary">Добави</button>
					</form>
					<form id="edit-form" name="edit-form" style="display: none;">
						<div class="form-group">
							<label for="edit-name">Заглавие:</label> <input type="text"
								class="form-control" id="edit-name" name="edit-name"
								placeholder="Име" minlength="2" />
						</div>
						<button id="edit-btn" type="button" class="btn btn-success">Обнови</button>
					</form>
				</div>
				<div class="col-xs-12 col-md-8" id="right">
					<div id="categoriesTable" style="display: none;">
						<table class="table table-responsive">
							<thead>
								<tr>
									<th>Ид</th>
									<th>Име</th>
									<th>Действия</th>
								</tr>
							</thead>
							<tbody id="categoriesTableContent">
							</tbody>
						</table>
					</div>

					<table style="display: none;">
						<tbody>
							<tr id="cloneThisTableRow" class="category-row">
								<td class="category-id">Id here</td>
								<td class="category-name">Category name here</td>
								<td><button class="btn btn-warning edit-table-row">Редактирай</button><button class="btn btn-danger remove-table-row">Изтрий</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<%@ include file="footer.jsp" %>
		</div>
	</div>

	<script src="js/categories.js"></script>
</body>
</html>
