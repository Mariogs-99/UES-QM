/**
 * divId => div donde se dibujar la respuesta del ajax
 * monto => monto a pagar
 * callback => nombre de funcion javascript que se invocara luego de procesar la trama
 * fx => funcion que se ejecutara luego de dibujar el widget en el div objetivo
 */

var PAGOES = {
	success: function(url){
		location.href=url;
		
	},
	error:function(error){
		location.href = location.href;
	},
	widgetPagoes:function(divId, monto, callbackSuccess,callbackError, fx){
		var div = jQuery("#"+divId); 
		var params = {};
		params.monto = monto;	
		if(callbackSuccess && callbackSuccess!=null){			
			params.callback = callbackSuccess;
		}
		if(callbackError && callbackError!=null){			
			params.callbackError = callbackError;
		}		
		jQuery.ajax({url:jsContext+'pagoes/', 
            type:"POST",
            data:params,
            success:function(data) {
				div.html(data);
				fx();
			}, 
            error:function(e){
				alert(e);
			}
        });
		
				
	}
};