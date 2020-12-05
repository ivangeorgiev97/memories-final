$(document).ready(function() {
	// API Url
	const apiUrl = 'http://localhost:8080/users';

	let currentResult = [];
	let currentTableRow;
	let currentLoggedInUser;
	
	getCurrentLoggedInUser();
	
	getAll();
	
	// or form submit with serialize function called
	$("#submit-btn").click(function() {
		const username = $("#username").val();
		const password = $("#password").val();
		const repeatPassword = $("#repeatPassword").val();
		const email = $("#email").val();
		
		if (password !== repeatPassword) {
			alert('Паролите не са еднакви');
			return;
		}

		if (username && password && email) addTableRow(username, password, email);
	})
	
	$(document).on('click', '.edit-table-row', function() {
		$("#add-form").hide();
		$("#loading-spinner").show();

		const id = $(this).data('id').split('-')[1];

		currentTableRow = currentResult.find(el => el.id == id);

		$("#edit-username").val(currentTableRow.username);
		$("#edit-email").val(currentTableRow.email);

		$("#loading-spinner").hide();
		$("#edit-form").show();
	});
	
	$(document).on('click', '#edit-btn', function(event) {
		const currentId = currentTableRow && currentTableRow.id ? currentTableRow.id : 0;
		const username = $("#edit-username").val()
		const password = $("#edit-password").val();
		const editRepeatPassword = $("#edit-repeatPassword").val();
		const email = $("#edit-email").val();
		
		if (password !== editRepeatPassword) {
			alert('Паролите не са еднакви');
			return;
		}

		if (username && currentId && password && email) editTableRow(currentId, username, password, email);
	});

	$(document).on('click', '.remove-table-row', function() {
		const id = $(this).data('id').split('-')[1];

		if (id) deleteTableRow(id);
	});
	
    function getCurrentLoggedInUser() {
    	$.ajax({
    		url: "/getLoggedInUser",
    		method: "GET",
    		complete: function(data) {
    			switch (data.status) {
    				case 200:
    				currentLoggedInUser = data.responseJSON;
    				break;
    				
    				case 401:
    				console.log('User still not logged in')
    				break;
    			}
    		}, fail: function() {
    			window.location.href="index.html";
    		}
    	});
    }
	
	function addTableRow(username, password, email) {
		$.ajax({
			type: "POST",
			url: apiUrl,
			data: JSON.stringify({
				username: username,
				password: password,
				email: email
			}),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				currentResult.push(data);

				const tableRow = createTableRow(data);
				$("#usersTableContent").prepend(tableRow);

				$("#username").empty();
				$("#password").empty();
				$("#repeatPassword").empty();
				$("#email").empty();

				tableRow.fadeIn(1000);
			},
			error: function() {
				onApiError();
			}
		});
	}
	
	function editTableRow(id, username, password, email) {
		const data = {
			id: id,
			username: username,
			password: password,
			email: email
		};

		$.ajax({
			type: 'PUT',
			url: `${apiUrl}/${id}`,
			contentType: 'application/json',
			data: JSON.stringify(data)
		}).done(function() {
			$("#edit-form").hide();
			$("#add-form").show();
			if (currentTableRow) {
				const editedTableRow = $(`#tr-${currentTableRow.id}`);
				editedTableRow.find('.user-id').text(`${data.id}`);
				editedTableRow.find('.user-username').text(`${data.username}`);
				editedTableRow.find('.user-email').text(`${data.email}`);
			}
		}).catch(function() {
			onApiError();
			$("#edit-form").hide();
			$("#add-form").show();
		});
	}

	function deleteTableRow(id) {
		$.ajax({
			url: `${apiUrl}/${id}`,
			type: 'DELETE',
			success: () => {
				$(`#tr-${id}`).fadeOut(1000);
			}
		}).catch(function() {
			onApiError();
		});
	}
	
	function getAll() {
		$.ajax({
			method: "GET",
			url: `${apiUrl}`,
			dataType: "json"
		}).done(function(data) {
			currentResult = [...data];

			renderData(data);
		}).catch(function() {
			onApiError();
		});
	}
	
	function renderData(data) {
		$("#usersTableContent").empty();

		for (let i = 0; i < data.length; i++) {
			const tableRow = createTableRow(data[i]);
			$("#usersTableContent").append(tableRow);
			tableRow.show();
		}

		$("#categoriesTable").fadeIn(1000);
	}
	
	function createTableRow(data) {
		let clonedTableRow = $("#cloneThisTableRow").clone();

		clonedTableRow.removeAttr('id');
		clonedTableRow.attr('id', `tr-${data.id}`)
		clonedTableRow.find('.user-id').text(`${data.id}`);
		clonedTableRow.find('.user-username').text(`${data.username}`);
		clonedTableRow.find('.user-email').text(`${data.email}`);
		
		clonedTableRow.find('.user-actions').empty();
		if (currentLoggedInUser && currentLoggedInUser === data.id) {
			clonedTableRow.find('.user-actions').html(`
				<button class="btn btn-warning edit-table-row" data-id="edit-${data.id}">Редактирай</button><button class="btn btn-danger remove-table-row" data-id="remove-${data.id}">Изтрий</button>
			`);
		}

		return clonedTableRow;
	}
	
	function onApiError() {
		alert('Проблем с API call-а. Моля проверете дали има работещо API и дали apiUrl-ът е правилен');
	}

});
