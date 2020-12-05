$(document).ready(function() {
	// TODO - Put this in separate file
    let loggedInUser;

	getLoggedInUser();
	
    $("#logoutBtn").on('click', function() {
    	$.ajax({
    		url: "/logout",
    		method: "POST",
    		complete: function(data) {
    			if (data.status == 200) {
    				window.location.href = "index.html";
    			} else {
					console.log('Error with logout')
    				window.location.href = "index.html";
    			}
    		}
    	});
    });
    
    function getLoggedInUser() {
    	$.ajax({
    		url: "/getLoggedInUser",
    		method: "GET",
    		complete: function(data) {
    			switch (data.status) {
    				case 200:
    				loggedInUser = data.responseJSON;
    				break;
    				
    				case 401:
    				window.location.href="index.html";
    				break;
    			}
    		}, fail: function() {
    			window.location.href="index.html";
    		}
    	});
    }
});
