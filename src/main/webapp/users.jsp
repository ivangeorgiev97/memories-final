<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Memories | Users</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
    integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous" />
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
			                 <label for="username">Потребител</label>
			                 <input type="text" class="form-control" id="username" name="username" placeholder="Потребител" required minlength="4">
			             </div>
			             <div class="form-group">
			                 <label for="email">Email</label>
			                 <input type="email" class="form-control" id="email" name="email" placeholder="example@example.com" required minlength="4">
			             </div>
			             <div class="form-group">
			                 <label for="password">Парола</label>
			                 <input type="password" class="form-control" id="password" name="password" placeholder="Парола" required minlength="4">
			             </div>
			             <div class="form-group">
			                 <label for="repeatPassword">Повторете паролата</label>
			                 <input type="password" class="form-control" id="repeatPassword" name="repeatPassword" placeholder="Повторете паролата" required minlength="4">
			             </div>
			             <button type="button" id="submit-btn" class="btn btn-primary">Създай</button>
			         </form>
   					<form id="edit-form" name="edit-form" style="display: none;">		
			             <div class="form-group">
			                 <label for="username">Потребител</label>
			                 <input type="text" class="form-control" id="edit-username" name="edit-username" placeholder="Потребител" required minlength="4">
			             </div>
			             <div class="form-group">
			                 <label for="email">Email</label>
			                 <input type="email" class="form-control" id="edit-email" name="edit-email" placeholder="example@example.com" required minlength="4">
			             </div>
			             <div class="form-group">
			                 <label for="password">Парола</label>
			                 <input type="password" class="form-control" id="edit-password" name="edit-password" placeholder="Парола" required minlength="4">
			             </div>
			             <div class="form-group">
			                 <label for="repeatPassword">Повторете паролата</label>
			                 <input type="password" class="form-control" id="edit-repeatPassword" name="edit-repeatPassword" placeholder="Повторете паролата" required minlength="4">
			             </div>
			             <button type="button" class="btn btn-success" id="edit-btn">Обнови</button>
			         </form>
				</div>
				<div class="col-xs-12 col-md-8" id="right">
					<div id="usersTable">
						<table class="table table-responsive">
							<thead>
								<tr>
									<th>Ид</th>
									<th>Име</th>
									<th>Имейл адрес</th>
									<th>Действия</th>
								</tr>
							</thead>
							<tbody id="usersTableContent">
							</tbody>
						</table>
					</div>

					<table style="display: none;">
						<tbody>
							<tr id="cloneThisTableRow" class="category-row">
								<td class="user-id">Id here</td>
								<td class="user-username">Username here</td>
								<td class="user-email">Email here</td>
								<td class="user-actions"><button class="btn btn-warning edit-table-row">Редактирай</button><button class="btn btn-danger remove-table-row">Изтрий</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<%@ include file="footer.jsp" %>
		</div>
	</div>

    <script src="js/users.js"></script>
</body>
</html>
