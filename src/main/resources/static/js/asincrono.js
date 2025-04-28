function buscarNit(_nit, callback){
	var NIT_URL= jsContext + "ruc/nombre/"+_nit;	
	jQuery("span.agentName").text(m.nit.verificando);
    jQuery.ajax({   url:NIT_URL, 
                    type:"POST",
                    dataType:"json",                    
                    success:callback, 
                    error:onErrorNit
                });
}

function onSuccessNitR(data, statusText){
    if(statusText == "success"){
        try{
            jQuery("input.inputNIT").val("");
            if(!data.valid){
            	msgR(m.nit.noEncontrado);
                jQuery("p.agentName").html("");
                DATAS.newR.nit=null;
                DATAS.newR.isBienestar=null;
                DATAS.newR.isIpsfa=null;
                DATAS.newR.nombreAgente=null;
                jQuery("#addingIncomeR .conditional").hide(200);
            }else{
                jQuery("p.agentName").html(data.nombre+"<br/>"+data.nit);
                DATAS.newR.nit=data.nit;
                DATAS.newR.isBienestar=data.bienestar;
                DATAS.newR.isIpsfa=data.ipsfa;
                DATAS.newR.nombreAgente=data.nombre + " ";
                jQuery("#addingIncomeR .conditional").show(200);
            }
        }catch(e){
        	error("Inconveniente:" + e);
            
        }
    }else{
    	DATAS.newR.nit=null;
        DATAS.newR.isBienestar=null;
        DATAS.newR.isIpsfa=null;
        DATAS.newR.nombreAgente=null;
        jQuery("#addingIncomeR .conditional").hide(200);
    }     
    
}
function onSuccessNitRNR60(data, statusText){
    if(statusText == "success"){
        try{
        	jQuery("input.inputNIT").val("");
            if(!data.valid){
            	msgRNR60(m.nit.noEncontrado);
                jQuery("p.agentName").html("");
                DATAS.newR.nit=null;
                DATAS.newR.isBienestar=null;
                DATAS.newR.isIpsfa=null;
                DATAS.newR.nombreAgente=null;
                jQuery("#addingIncomeNR60 .conditional").hide(200);
            }else{
                jQuery("p.agentName").html(data.nombre+"<br/>"+data.nit);
                DATAS.newR.nit=data.nit;
                DATAS.newR.isBienestar=data.bienestar;
                DATAS.newR.isIpsfa=data.ipsfa;
                DATAS.newR.nombreAgente=data.nombre + " ";
                jQuery("#addingIncomeNR60 .conditional").show(200);
                if(incomeType	==	incomeTypes.SALARY){
                	jQuery("div#codesNR60Container").hide();
                }
            }
        }catch(e){
            error("Inconveniente:" + e);
            
        }
    }else{
    	DATAS.newR.nit=null;
        DATAS.newR.isBienestar=null;
        DATAS.newR.isIpsfa=null;
        DATAS.newR.nombreAgente=null;
        jQuery("#addingIncomeNR60 .conditional").hide(200);
    }     
    
}



function onErrorNit(data){
    switch(parseInt(data.status)){
        case 400:
        case 403:
        case 404:
        case 405:
        case 500:
        case 503:
            error("Inconveniente:"+data.status);
            break;
        default:
            error("Error al intentar consultar NIT");
            break;
    }
    
}