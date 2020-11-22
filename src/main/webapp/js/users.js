$(document).ready(function() {
	$('#loginForm').submit(function(e) {
		e.preventDefault();

		$.ajax({
			url: "login",
			method: "POST",
			data: {
				username: $('#username').val(),
				password: $('#password').val()
			},
			success: function(data) {
				window.location.replace(data);
			},
			fail: function() {
				window.location.href = "error.html"
			}
		});

	});
});
