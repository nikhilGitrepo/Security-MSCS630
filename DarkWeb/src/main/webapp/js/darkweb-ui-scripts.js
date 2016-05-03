/**
 * 
 */

$("#userIdenficationForm .dropdown-menu li a").click(function(){
  $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>'); // button appearance
  $(this).parents(".dropdown").find('.btn').val($(this).text()); // button value
  
  var selectedBtn = $(this).parents(".dropdown").find('.btn').attr("id");
  
  if( selectedBtn == "encryptionSchemebtn" ){
	  $("#encryptionScheme").attr("value",$(this).attr("value"));
  }
  
  if( selectedBtn == "securityLevelbtn" ){
	  $("#securityLevel").attr("value",$(this).attr("value"));
	  $("#secureKey").attr("placeholder", "Choose a "+ $(this).attr("value") +" bits key");
  }
  
});


$(".go-submit").click(function(){
	
	if($("#user").val().length == 0){
		alert("To proceed you have to have a name!" );
		return false;
	}
	
	var secureKeyLen = $("#secureKey").val().length;
	var securityLevel = $("#securityLevel").attr("value");
	
	if(securityLevel == 128 && secureKeyLen != 16 ){
		alert("For "+ securityLevel + " bit Security Level,\n the key has to be "+ 16 + " characters long.\n Still " + (16-secureKeyLen) + " character/s necessary for perfect key. " );
		return false;
	}else if(securityLevel == 192 && secureKeyLen != 24 ){
		alert("For "+ securityLevel + " bit Security Level,\n the key has to be "+ 24 + " characters long.\n Still " + (24-secureKeyLen) + " character/s needed for perfect key. " );
		return false;
	}else if(securityLevel == 256 && secureKeyLen != 32 ){
		alert("For "+ securityLevel + " bit Security Level,\n the key has to be "+ 32 + " characters long.\n Still " + (32-secureKeyLen) + " character/s needed for perfect key. " );
		return false;
	}
	$("#userIdenficationForm").submit();
});

