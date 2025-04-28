/*requiere jQuery 1.4*/
jQuery.noConflict();
var asincronoPlazo = {
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
    
   
    resolucionUrlPdf:"http://172.16.21.35:6688/rsapp/images/RESOLUCION.pdf",
    mandamientoUrlPdf:"/pagoRenta/mandamientoCuotaPdf.htm",
    pagoPagoesUrl:"http://172.16.21.35:6688/rsapp",
    /*imprimirMandamiento:function(){
        PGui.psAlert("Espere un momento...");
        jQuery.ajax({   url:this.mandamientoUrl, 
                        type:"POST",
                        dataType:"json",
                        data:{tm:jQuery("input[name=tpago]:checked").val(), monto:parseFloat(jQuery("input[name=pparcial]").val())}, 
                        success:this.onSuccessM, 
                        error:this.onErrorM
                    });
        
    },*/
    pagoPagoes:function(){
        PGui.psAlert("Procesando su pago, espere unos segundos");
        f       =   jQuery("<form>").attr("action", asincronoPago.pagoPagoesUrl).attr("method","POST");
        hidden1 = jQuery("<input>", {type:"hidden", value:parseFloat(jQuery("input[name=tpago]").val()), name:"tpago"});
        hidden2 = jQuery("<input>", {type:"hidden", value:parseFloat(jQuery("input[name=pparcial]").val()), name:"monto"});
        f.append(hidden1);
        f.append(hidden2);
        jQuery("div#psLyt").append(f);
        setTimeout(function(){confirmarAbandono(false);f.submit()}, 2000);
    },
    imprimirResolucion:function(cresolucion){
        try{
            /*PlzGui.psAlert("Espere un momento...");
            
            PlzGui.paso = 2;
                jQuery("div#pstp2").animate({left:0, top:0}
                                         ,500
                                         , function(){
                                                PlzGui.configurePaso(null, null);
                                            }
                                        )*/
            
        }catch(e){
            alert(e);
        }
    },
    
    imprimirMandamiento:function(cmandamiento){
        try{
            PlzGui.psAlert("Espere un momento...");
            PlzGui.paso = 3;
                jQuery("div#pstp3").animate({left:0, top:0}
                                         ,500
                                         , function(){
                                                PlzGui.configurePaso(null, null);
                                            }
                                        )
            PlzGui.psAlert("<b>Generando su Mandamiento de Pago. Espere unos segundos...", 3000);
            var f   =   jQuery("<form>").attr("action", jsContext + asincronoPlazo.mandamientoUrlPdf).attr("method", "POST").attr("target", "pdfreceptorMandamiento").appendTo("div#ifrmMndmnt");
            setTimeout(function(){f.submit();}, 1000);
        }catch(e){
            alert(e);
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
                text = asincronoPlazo.mensajes.errores["error"+data.status];
                break;
            default:
                text =  "No se puede procesar su petici&oacute;n en este momento. Intente m&aacute;s tarde<br>"+data;
                break;
        }
        
        PlzGui.psAlert(text+data.status, 2000)
    }
    


}