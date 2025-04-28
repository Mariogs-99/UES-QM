/*requiere jQuery 1.4*/
jQuery.noConflict();
var asincronoPago = {
    ultimoMandamiento:0,
    mensajes:{
        errores:{
                error400:"Peticion corrupta",
                error403:"Permiso denegado",
                error404:"Recurso no encontrado",
                error405:"Metodo no permitido",
                error500:"Error interno de servidor",
                error503:"Error interno de servidor 503"
            },
        mandamiento:{}
        
    },
    

    resolucionUrl:"pagorenta/save/resolucion",
    resolucionUrlPdf:"pagorenta/resolucion/plazo/pdf",
    mandamientoUrl:"pagorenta/mandamiento",
    mandamientoUrlPdf:"pagorenta/mandamiento/pdf",    
    imprimirMandamiento:function(){
        PGui.psAlert("Espere un momento...");
        jQuery.ajax({   url:jsContext+this.mandamientoUrl, 
                        type:"POST",
                        dataType:"json",
                        data:{tipoPago:jQuery("input[name=tpago]:checked").val(), monto:parseFloat(jQuery("input[name=pparcial]").val())}, 
                        success:this.onSuccessM, 
                        error:this.onErrorM
                    });
        
    },
    
    imprimirResolucion:function(){
        if(jQuery("#opcnscts a.actv span").length){
            if(isNaN(parseInt(jQuery("#opcnscts a.actv span").text()))){
                PGui.psAlert("Seleccione un numero de cuotas valido", 3000); 
                return;
            }
            PGui.psAlert("Espere un momento...");
            jQuery.ajax({   url:jsContext+this.resolucionUrl, 
                            type:"POST",
                            dataType:"json",
                            data:{cts:parseInt(jQuery("#opcnscts a.actv span").text()) }, 
                            success:this.onSuccessR, 
                            error:this.onErrorM
                        });
        
        }else{
            PGui.psAlert("Seleccione un plan");
        }
        
    },
    onSuccessR:function(data, statusText){
        
        if(statusText == "success"){
            try{
                confirmarAbandono(false);
                if(data.status == "error"){
                    PGui.psAlert(data.error);
                }else{
                    PGui.paso = 4;
                    jQuery("div#pstp4").animate({left:0, top:0}
                                             ,500
                                             , function(){
                                                    PGui.configurePaso(null, null);
                                                }
                                            )
                    PGui.psAlert("<b>Se ha generado la resoluci&oacute;n # "+data.cresolucion+". Espere unos segundos para que pueda ser descargada...", 4000);
                    var f   =   jQuery("<form>").attr("action", jsContext+asincronoPago.resolucionUrlPdf+"?cresolucion="+data.cresolucion).attr("method", "POST").attr("target", "pdfreceptorResolucion").appendTo("div#ifrmRslcn");
                    if(jQuery("#pdfreceptorResolucionId").contents().find("form").length){
                        jQuery("#pdfreceptorResolucionId").contents().find("form:first").attr("action", jsContext+asincronoPago.resolucionUrlPdf+"?download=true&cresolucion="+data.cresolucion);
                    }
                    confirmarAbandono(false);
                    setTimeout(function(){f.submit();}, 1000);
                    
                }
            }catch(e){
                alert(e);
            }
        }     
    },
    onSuccessM:function(data, statusText){        
        if(statusText == "success"){
            try{
                confirmarAbandono(false);
                if(data.status == "error"){
                    PGui.psAlert(data.error, 2000);
                }else{
                    PGui.paso = 2;
                    jQuery("div#pstp2").animate({left:0, top:0}
                                 ,500
                                 , function(){
                                        PGui.configurePaso(null, null);
                                    }
                                );
                    PGui.psAlert("<b>Se ha generado el mandamiento <br/> Espere unos segundos...", 5000);
                    f   =   jQuery("<form>").attr("action", jsContext+asincronoPago.mandamientoUrlPdf+"?nit="+data.nit).attr("method", "POST").attr("target", "pdfreceptorMandamiento").appendTo("div#ifrm");
                    if(jQuery("#ifrmbncs").contents().find("form:first").length){
                    	jQuery("#ifrmbncs").contents().find("form:first").attr("action", jsContext+asincronoPago.mandamientoUrlPdf+"?download=true&nit="+data.nit);
                    }
                    confirmarAbandono(false);
                    setTimeout(function(){f.submit();}, 1000);
                }
            }catch(e){
                alert(e);
            }
        }     
    },
    
    onErrorM:function(data){    	
        var text    =   "";
        switch(parseInt(data.status)){
            case 400:
            case 403:
            case 404:
            case 405:
            case 500:
            case 503:
                text = asincronoPago.mensajes.errores["error"+data.status];
                break;
            default:
                text =  "No se puede procesar su petici&oacute;n en este momento. Intente m&aacute;s tarde<br>"+data;
                break;
        }
        
        PGui.psAlert(text+data.status, 2000)
    }

}
