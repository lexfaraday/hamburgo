function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
}

function sendName() {
	$.ajax({
		type : "GET",
		url : "http://localhost:3000/event-app/",
		error : function(xhr, status, error) {
			if (xhr && xhr.responseText) {
				console.error(xhr.responseText);
			} else {
				console.error(xhr);
			}

		},
		success : function(data, status, xhr) {
			if (data && data.message) {
				console.log(data.message)
			}
		}
	});
}

function showGreeting(message) {
	console.log(JSON.stringify(message));
}

jQuery(window).load(function() {
	var stompClient = null;
	var socket = new SockJS('/hello');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/eventflow/flow', function(greeting) {
			console.log('received');
			showGreeting(JSON.parse(greeting.body));
		});
	});

});