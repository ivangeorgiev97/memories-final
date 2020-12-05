$(document).ready(function () {
	// This could be refactored
	checkIfUserIsLoggedIn();
	
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
	
    function checkIfUserIsLoggedIn() {
    	$.ajax({
    		url: "/getLoggedInUser",
    		method: "GET",
    		success: function(data) {
				if (data) {
					window.location.href="memories.jsp"
				}
	
    			switch (data.status) {
    				case 200:
    				window.location.href="memories.jsp"
    				break;
    				
    				case 401:
    				console.log('User still not logged in')
    				break;
    			}
    		}, error: function() {
    			console.log('User still not logged in')
    		}
    	});
    }
})
