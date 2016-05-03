/**
 * 
 */

var stompClient = null;
var blocks = 0;
function setConnected(connected) {
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
    var socket = new SockJS('/send');
    stompClient = Stomp.over(socket);
    stompClient.connect($("#user").attr("value"),$("#user").attr("value"), function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/chat', function(responseJSON){
            showGreeting(JSON.parse(JSON.stringify(responseJSON.body)));
        });
    });
}

function send() {
	blocks = 0;
	var message = $('#message').val();
	if( message == undefined || message.length == 0 ){
		alert( $("#user").val() + ".. send a meaningful message!")
		return false;
	}

	stompClient.send("/app/send", {}, JSON.stringify({ 'message': message,'name': $("#user").val() }));
	
}

function removePadding(message){
	return message.split("[]")[0];
}

function showGreeting(responseJSON) {
    var response = document.getElementById('response');
    
    var div = document.createElement('div');
    div.id = JSON.parse(responseJSON).messageID;
    
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';

    div.appendChild(p);
    p.appendChild(document.createTextNode(JSON.parse(responseJSON).message));
    
    var small = document.createElement('small');
    var code = document.createElement('code');
    small.appendChild(code);
    
    if(JSON.parse(responseJSON).name == $("#user").attr("value")){
    	$(p).attr("class","text-success");
    	$(div).attr("class","text-right");	
    }else{
        p.appendChild(small);
        $(p).attr("class","text-primary");
        code.appendChild(document.createTextNode(" -"+JSON.parse(responseJSON).name));
        
    	$(div).attr("class","text-left");
    }
    
    response.appendChild(div);
    $('#message').val('');
}

$(function() {
    console.log( "ready!" );
    connect();
});

$("#send").click(function(){
	send();
});

$('#message').keypress(function (e) {
  if (e.which == 13) {
	send();
    return false;
  }
});
