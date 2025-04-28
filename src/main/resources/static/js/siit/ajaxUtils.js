/*
 * Requiere jQuery
 * 
 * 
 */




jQuery(document).ready(
	function(){
		jQuery("form.ajaxForm").submit(
			function(event){
				event.preventDefault();
				sendForm(jQuery(this));
				return false;
			}
		);
		
	}
);

function sendForm(f, callback){
	jsonAttribute	=	{};
	json			=	{};
	f.find(".fieldAttribute").each(
		function(index, object){
			var obj	=	jQuery(object);
			if(obj.attr("name") != undefined && obj.attr("name")!=null){
				json[obj.attr("name")]	=	obj.val();	
			}
		}
	);
	
	if(f.attr("id") != undefined && f.attr("id") != null){
		//jsonAttribute[f.attr("id")]	=	json; momentaneamente desactivado...
	}else{
		jsonAttribute	=	json;
	}
	jsonAttribute	=	json;
	var action	=	(f.attr("action") != null && f.attr("action")) != undefined ? f.attr("action") : "";
	var method	=	(f.attr("method") != null && f.attr("method")) != undefined ? f.attr("method") : "post";
	jQuery.ajax({
		url:action,
		success:function(data){
			TMessages.showNoticeMessage(data.messages, TMessages.SHORT_TIME);
			if (callback != null && callback != undefined)callback(data);
		},
		error:function(data){
			TMessages.showErrorMessage(data.textStatus, TMessages.LONG_TIME);
			if (callback != null && callback != undefined)callback(data);
		},
		dataType: 'json',
		type:method,
		data:jsonAttribute
	});
}
//jQuery('div#tMessage').ajaxComplete(function(e,xhr,settings) {
//	  jQuery(this).html('Triggered ajaxComplete handler.'+xhr.responseHTML+"");
//	  TMessages.showNoticeMessage(":X");
//	  alert("ajaxcomplete!");
//	});