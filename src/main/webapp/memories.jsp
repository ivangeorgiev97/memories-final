<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Final project</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
        integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous" />
    <link rel="stylesheet" href="css/style.css" />
</head>

<body>
	<%@ include file="header.jsp" %>
    <div id="main-container" class="container bg-light p-5">
        <div class="row">
            <div class="col-xs-12 col-md-4" style="display: none;" id="left">
                <div class="spinner-border" id="loading-spinner" role="status" style="display: none;">
                    <span class="sr-only">Loading...</span>
                </div>
                <form id="add-form" name="add-form">
                    <div class="form-group">
                        <label for="title">Заглавие:</label>
                        <input type="text" class="form-control" id="title" placeholder="Заглавие" minlength="2" />
                    </div>
                    <div class="form-group">
                        <label for="description">Описание:</label>
                        <textarea class="form-control" id="description" rows="6" minlength="4"></textarea>
                    </div>
                    <div class="form-group">
                    	<label for="categoryId">Категория:</label>
                    	<select class="form-control custom-select" id="categoryId" name="categoryId" required>
                    	
                    	</select>
                    </div>
                    <button id="submit-btn" type="button" class="btn btn-primary">Добави</button>
                </form>
                <form id="edit-form" name="edit-form" style="display: none;">
                    <form id="addForm">
                        <div class="form-group">
                            <label for="edit-title">Заглавие:</label>
                            <input type="text" class="form-control" id="edit-title" name="edit-title" placeholder="Заглавие" minlength="2" />
                        </div>
                        <div class="form-group">
                            <label for="edit-description">Описание:</label>
                            <textarea class="form-control" id="edit-description" name="edit-description" rows="6" minlength="4"></textarea>
                        </div>
                        <div class="form-group">
	                    	<label for="edit-categoryId">Категория:</label>
	                    	<select class="form-control custom-select" id="edit-categoryId" name="edit-categoryId" required>
	                 
	                    	</select>
	                    </div>
                        <button id="edit-btn" type="button" class="btn btn-success">Обнови</button>
                    </form>
                </form>
            </div>
            <div class="col-xs-12 col-md-8" style="display: none;" id="right">
                <h3>Вашите спомени:</h3>

                <div class="row">
                    <div class="col-6">
                        <div class="form-group">
                            <label for="sort-by">Сортирай по (с ajax заявка)</label>
                            <select class="form-control custom-select" id="sort-by">
                                <option value="" selected disabled hidden>Моля изберете</option>
                                <option value="title">Заглавие</option>
                                <option value="date">Дата</option>
                                <option value="id">Идентификатор</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group">
                            <label for="from-id">ID филтриране (без ajax)</label>
                            <select class="form-control custom-select" id="from-id">
                                <option value="" selected disabled hidden>Моля изберете</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="15">15</option>
                            </select>
                        </div>
                    </div>
                </div>
                
                <div class="row mt-1">
                    <div class="col-6">
                        <div class="form-group">
                            <label for="categoryIdFilter">Филтриране по категория</label>		
							<select class="form-control custom-select" id="categoryIdFilter">
                                <option value="" selected disabled hidden>Моля изберете</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group">
                            <label for="userIdFilter">Филтриране по потребител</label>		
							<select class="form-control custom-select" id="userIdFilter">
                                <option value="" selected disabled hidden>Моля изберете</option>
                            </select>
                        </div>
                    </div>
                </div>

                <button class="btn btn-warning my-2" id="dark-cards">Тъмни елементи</button>

                <div id="cards-list" style="display: none;">
                </div>

                <div id="clone-this-card" style="display: none;" class="col-12 my-2">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Заглавие</h5>
                            <p class="card-text">Описание</p>
                            <p class="card-category">Категория</p>
                            <p class="card-user">Потребител</p>
                            <p class="card-actions">
	                            <button type="button" class="btn btn-warning edit-card">Промени</button>
	                            <button type="button" class="btn btn-danger remove-card">Изтрий</button>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
    </div>

    <script src="js/memories.js"></script>
</body>

</html>
