$(document).ready(function() {
	// API Url
	const apiUrl = 'http://localhost:8080/categories';

	let currentResult = [];
	let currentTableRow;

	getAll();

	// or form submit with serialize function called
	$("#submit-btn").click(function() {
		const name = $("#name").val();

		if (name) addTableRow(name);
	})

	$(document).on('click', '.edit-table-row', function() {
		$("#add-form").hide();
		$("#loading-spinner").show();

		const id = $(this).data('id').split('-')[1];

		currentTableRow = currentResult.find(el => el.id == id);

		$("#edit-name").val(currentTableRow.name);

		$("#loading-spinner").hide();
		$("#edit-form").show();
	});

	$(document).on('click', '.remove-table-row', function() {
		const id = $(this).data('id').split('-')[1];

		if (id) deleteTableRow(id);
	});

	$(document).on('click', '#edit-btn', function() {
		const currentId = currentTableRow && currentTableRow.id ? currentTableRow.id : 0;
		const name = $("#edit-name").val()

		if (name && currentId) editTableRow(currentId, name);
	});

	function addTableRow(name) {
		$.ajax({
			type: "POST",
			url: apiUrl,
			data: JSON.stringify({
				name: name
			}),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data) {
				currentResult.push(data);

				const tableRow = createTableRow(data);
				$("#categoriesTableContent").prepend(tableRow);

				$("#name").empty();

				tableRow.fadeIn(1000);
			},
			error: function() {
				onApiError();
			}
		});
	}

	function editTableRow(id, name) {
		const data = {
			id: id,
			name: name
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
				editedTableRow.find('.category-id').text(`${data.id}`);
				editedTableRow.find('.category-name').text(`${data.name}`);
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
		$("#categoriesTableContent").empty();

		for (let i = 0; i < data.length; i++) {
			const tableRow = createTableRow(data[i]);
			$("#categoriesTableContent").append(tableRow);
			tableRow.show();
		}

		$("#categoriesTable").fadeIn(1000);
	}

	function createTableRow(data) {
		let clonedTableRow = $("#cloneThisTableRow").clone();

		clonedTableRow.removeAttr('id');
		clonedTableRow.attr('id', `tr-${data.id}`)
		clonedTableRow.find('.remove-table-row').data('id', `remove-${data.id}`);
		clonedTableRow.find('.edit-table-row').data('id', `edit-${data.id}`);
		clonedTableRow.find('.category-id').text(`${data.id}`);
		clonedTableRow.find('.category-name').text(`${data.name}`);

		return clonedTableRow;
	}

	function onApiError() {
		alert('Проблем с API call-а. Моля проверете дали има работещо API и дали apiUrl-ът е правилен');
	}
});
