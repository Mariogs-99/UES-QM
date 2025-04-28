/*requiere jQuery*/

function isNullOrUndefined(val){
	return (val ===  undefined || val == null);
}

function getUrlParam( param ){
	param = param.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	var r1 = "[\\?&]"+param+"=([^&#]*)";
	var r2 = new RegExp( r1 );
	var r3 = r2.exec( window.location.href );
	if( r3 == null ) return "";
	else  return r3[1];
	}


function UpdateQueryString(key, value, url) {
    if (!url) url = window.location.href;
    var re = new RegExp("([?|&])" + key + "=.*?(&|#|$)", "gi");

    if (url.match(re)) {
        if (value)
            return url.replace(re, '$1' + key + "=" + value + '$2');
        else
            return url.replace(re, '$2');
    }
    else {
        if (value) {
            var separator = url.indexOf('?') !== -1 ? '&' : '?',
                hash = url.split('#');
            url = hash[0] + separator + key + '=' + value;
            if (hash[1]) url += '#' + hash[1];
            return url;
        }
        else
            return url;
    }
}




function isNumeric(val){
	if(isNullOrUndefined(val)){
		return false;
	}
	if(isNaN(parseFloat(val))){
		return false;
	}
	return true;
}

function _parse(val){
	if(isNumeric(val))
		return parseFloat(val);
	return -1;
}

function formatearMoneda(valor){
	var m	=	_parse(valor).toFixed(2);
	if(m<0) return "";
	valor	=	parseFloat(valor).toFixed(2);
    if(valor.length){
    	
        m   =   m.replace(",","");
        var d   =   ".00";
        if(m.indexOf(".")>0){
            m   =   m.split(".")[0];
            d   =   "."+valor.split(".")[1];
        }
        var temp    =   "";
        var c   =   1;
        
        for(var i=(m.length-1); i>=0; i--){
            if(c%3 == 0 && i!=0){
                temp = ","+m.charAt(i)+temp;
            }else{
                temp = m.charAt(i)+temp;
            }
            c++;
        }
        return temp + d;
    }
    return m;
}

function getMax(code){
	for(var i=0; i<f11.valoresMaximos.length; i++){
		
		if(f11.valoresMaximos[i]["max_"+code] != null && f11.valoresMaximos[i]["max_"+code] != undefined){
			return f11.valoresMaximos[i];
		}
	}
	return null;
}

function _keyUp(cl, event){
    /* /^\$?[1-9][0-9]{0,2}(,[0-9]{3})*(\.[0-9]{2})?$/.test(str) por si quieren tipo moneda -- pensar en la forma de verificar lo de la coma. */
	c   =   jQuery(cl);
    if(c.val().length == 0){return;}
    
    //alert(event.keyCode);
    if((parseInt(event.keyCode)==37 || parseInt(event.keyCode)==39 || parseInt(event.keyCode)==8 || parseInt(event.keyCode)==46)){return;} //las flechas y teclas al borrar
    
    if(parseInt(event.keyCode)==190 || parseInt(event.keyCode)==110){ // el punto del teclado y del numerico
        if(c.val().indexOf(".")==c.val().lastIndexOf(".") && c.val().lastIndexOf(".")!=-1){return;}
    }
//    if(parseInt(event.keyCode) == 13){jQuery(this).focusout();}
    if(!(/^[+-]?[0-9]+(\.[0-9]+)?$/.test(c.val()))){
        c.val("0.00");
    }else{
    	
		if(!isNullOrUndefined(c.attr("id"))){
    		 code	=	c.attr("id").replace("_","").replace("c", "");
    		 max	=	getMax(code);
    		 if(max!=null){
    			if(parseFloat(max["max_"+code])>0){
    				if(parseFloat(c.val())>parseFloat(max["max_"+code])){
        				c.val(_parse(max["max_"+code]));
        			}
    			}
    			
    		}
    	}
    	
    }
}
function _focusOut(cl){
    /* /^\$?[1-9][0-9]{0,2}(,[0-9]{3})*(\.[0-9]{2})?$/.test(str) por si quieren tipo moneda -- pensar en la forma de verificar lo de la coma. */
	c   =   jQuery(cl);
	
	if(isNumeric(c.val())){
		
		c.val(parseFloat(c.val()).toFixed(2));
		
		return;
	}
	c.val("0.00");
 
}
