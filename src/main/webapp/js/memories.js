$(document).ready(function () {
    // API Url
    const apiUrl = 'http://localhost:8080/memories';
    
    // Category API Url
    const categoryApiUrl = 'http://localhost:8080/categories';

    let darkMode = false;
    let currentResult = [];
    let currentCard;
    let tempId;
    let sortByVal;
    let fromIdVal;

    // First api call 
    getAllCategories();
    getAll(1, 'id', true);

    $("#dark-cards").click(function () {
        darkMode = !darkMode;

        $("#dark-cards").text(darkMode ? "Светли елементи" : "Тъмни елементи");
        $(".card").toggleClass('bg-dark text-white');
    })

    $("#sort-by").change(function () {
        sortByVal = $(this).val();
        fromIdVal = $("#from-id").val();

        if (!fromIdVal) fromIdVal = 1;

        if (sortByVal) getAll(fromIdVal, sortByVal, false);
    })

    $("#from-id").change(function () {
        fromIdVal = $(this).val();
        sortByVal = $("#sort-by").val();

        if (fromIdVal && (currentResult.length === 0 || (tempId && tempId > fromIdVal))) {
            getAll(fromIdVal, 'id', false);
        }

        if (fromIdVal) {
            const filteredResult = currentResult.filter(el => el.id >= fromIdVal)

            tempId = fromIdVal;

            renderData(filteredResult, false);
        }
    })

    // or form submit with serialize function called
    $("#submit-btn").click(function () {
        const title = $("#title").val();
        const description = $("#description").val();
        const categoryId = $("select[name='categoryId']").val();

        if (title && description && categoryId) addCard(title, description, categoryId);
    })

    $(document).on('click', '#edit-btn', function () {
        const currentId = currentCard && currentCard.id ? currentCard.id : 0;
        const title = $("#edit-title").val();
        const description = $("#edit-description").val();
		const categoryId = $("select[name='edit-categoryId']").val();

        if (title && description && currentId && categoryId) editCard(currentId, title, description, categoryId);
    });

    $(document).on('click', '.remove-card', function () {
        const id = $(this).data('id').split('-')[1];

        if (id) deleteCard(id);
    });

    $(document).on('click', '.edit-card', function () {
        $("#add-form").hide();
        $("#loading-spinner").show();

        const id = $(this).data('id').split('-')[1];

        currentCard = currentResult.find(el => el.id == id);

        $("#edit-title").val(currentCard.title);
        $("#edit-description").val(currentCard.description);
        $("select[name='edit-categoryId']").val(currentCard.category.id);

        $("#loading-spinner").hide();
        $("#edit-form").show();
    });
    
    function getAllCategories() {
    	setTimeout(function () {
            $.ajax({
                method: "GET",
                url: `${categoryApiUrl}`,
                dataType: "json"
            }).done(function (data) {
            	let optionsHtml = '';
                for (let i = 0; i < data.length; i++) {
                	optionsHtml += `<option value="${data[i].id}">${data[i].name}</option>`
                }
                $("#categoryId").html(optionsHtml);
                $("#edit-categoryId").html(optionsHtml);
                $("#categoryIdFilter").html(optionsHtml);
            }).catch(function (err) {
                onApiError(err);
            });
        }, 300)
    }

    function getAll(fromId = 1, sortBy, isFirst) {
    	// TODO - Use sortBY and fromId
        setTimeout(function () {
            $.ajax({
                method: "GET",
                url: `${apiUrl}`,
                dataType: "json"
            }).done(function (data) { 
                currentResult = [...data];

                renderData(data, isFirst);
            }).catch(function (err) {
                onApiError(err);
            });
        }, 300)
    }

    function addCard(title, description, categoryId) {
        setTimeout(function () {
            $.post(`${apiUrl}`, {
                title: title,
                description: description,
                categoryId: categoryId
            }, function (data) {
                currentResult.push(data);

                const card = createCard(data);
                $("#cards-list").prepend(card);

                $("#title").empty();
                $("#description").empty();
                // maybe reset select too

                card.fadeIn(1000);
            }).catch(function (err) {
                onApiError(err);
            })
        }, 500)
    }

    function deleteCard(id) {
        setTimeout(function () {
            $.ajax({
                url: `${apiUrl}/delete/${id}`,
                type: 'DELETE',
                success: () => {
                    $(`#card-${id}`).fadeOut(1000);
                }
            }).catch(function (err) {
                onApiError(err);
            });
        }, 300)
    }

    function editCard(id, title, description, categoryId) {
        const data = {
            id: id,
            title: title,
            description: description,
            categoryId: categoryId
        }

        setTimeout(function (data) {
            $.ajax({
                type: 'PUT',
                url: `${apiUrl}/${id}`,
                contentType: 'application/json',
                data: JSON.stringify(data)
            }).done(function (updatedData) {
                $("#edit-form").hide();
                $("#add-form").show();
                if (currentCard) {
                    const editedCard = $(`#card-${currentCard.id}`);
                    editedCard.find('h5').html(`<span class="title-id">${updatedData.id}</span> - <span class="title-name">${updatedData.title}</span>`);
                    editedCard.find('.card-text').text(`${updatedData.description}`);
                    editedCard.find('.card-category').text(`Категория: ${updatedData.category.name}`);
                    editedCard.find('.card-user').text(`Потребител: ${updatedData.user.username}`);
                }
            }).catch(function (err) {
                onApiError(err);
                $("#edit-form").hide();
                $("#add-form").show();
            });
        }, 500)
    }

    function renderData(data, isFirst) {
        $("#cards-list").empty();

        if (isFirst) initDisplay();

        for (let i = 0; i < data.length; i++) {
            const card = createCard(data[i]);
            $("#cards-list").append(card);
            card.show();
        }

        $("#cards-list").fadeIn(1500);
    }

    function onApiError(err) {
    	console.log(err);
        alert('Проблем с API call-а. Моля проверете дали има работещо API и дали apiUrl-ът е правилен');
    }

    function initDisplay() {
        $("#left").fadeIn(800);
        $("#right").fadeIn(800);
        // $("#footer-text").fadeIn(2000);
    }

    function createCard(data) {
        let clonedCard = $("#clone-this-card").clone();

        clonedCard.removeAttr('id');
        clonedCard.attr('id', `card-${data.id}`)
        clonedCard.find('.remove-card').data('id', `remove-${data.id}`);
        clonedCard.find('.edit-card').data('id', `edit-${data.id}`);
        clonedCard.find('h5').html(`<span class="title-id">${data.id}</span> - <span class="title-name">${data.title}</span>`);
        clonedCard.find('.card-text').text(`${data.description}`);
        clonedCard.find('.card-category').text(`Категория: ${data.category.name}`);
        clonedCard.find('.card-user').text(`Потребител: ${data.user.username}`);
        // clonedCard.find('.card-created-at').text(`${data.created_at}`);

        return clonedCard;
    }
})
