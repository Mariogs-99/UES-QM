jQuery(document).ready(function () {    
             setTimeout(getAlertasAl,5000);
        });
        function getAlertasAl(){
            $.ajax({
		    url: window.location.protocol + "//" + window.location.host+"/colas/alrts/getAlerta?id=1",
		    type: 'GET', 
                    dataType: 'json', 		    
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) { 
				if(isEmptyObject(data)){
				   //nada					
				}else{              
                                    var spanD='&nbsp;&nbsp;&nbsp;&nbsp;<span style="background-color:red;color:white">';
                                    var spanP='&nbsp;&nbsp;&nbsp;&nbsp;<span style="background-color:orange;color:white">';
                                    var ntramiteP=0;
                                    var ntramiteD=0;
                                    var nzonaP=0;
                                    var nzonaD=0;
                                    var nseccionP=0;
                                    var nseccionD=0;
                                    var ncentroP=0;
                                    var ncentroD=0;
                                    var elHtml="";
                                    var eltipo=0;
                                    var nnn=1;
                                    var letilde="";
                                    for(i=0;i<data.lista.length;i++){ 
                                        letilde=data.lista[i].nombre;                                       
                                        if (data.lista[i].tipo==="TRAMITE"){
                                        if(parseInt(data.lista[i].porcentaje) >= 80 && parseInt(data.lista[i].porcentaje)<100 ){
                                           ntramiteP+=1;
                                        }
                                        if(parseInt(data.lista[i].porcentaje) >=100){
                                           ntramiteD+=1;
                                        }
                                      }
                                      if (data.lista[i].tipo==="ZONA"){
                                        if(parseInt(data.lista[i].porcentaje) >= 80 && parseInt(data.lista[i].porcentaje)<100 ){
                                           nzonaP+=1;
                                        }
                                        if(parseInt(data.lista[i].porcentaje) >=100){
                                           nzonaD+=1;
                                        }
                                      }
                                       if (data.lista[i].tipo==="SECCION"){
                                        if(parseInt(data.lista[i].porcentaje) >= 80 && parseInt(data.lista[i].porcentaje)<100 ){
                                           nseccionP+=1;
                                        }
                                        if(parseInt(data.lista[i].porcentaje) >=100){
                                           nseccionD+=1;
                                        }
                                      }
                                      
                                       if (data.lista[i].tipo==="CENTRO SERVICIO"){
                                        if(parseInt(data.lista[i].porcentaje) >= 80 && parseInt(data.lista[i].porcentaje)<100 ){
                                           ncentroP+=1;
                                        }
                                        if(parseInt(data.lista[i].porcentaje) >=100){
                                           ncentroD+=1;
                                        }
                                      }                               
                                    };                                 
                                    if (ntramiteP!=0 || ntramiteD!=0){
                                        elHtml=elHtml+nnn+".) TRAMITES: <br/>";
                                        if (ntramiteP>0)
                                            elHtml=elHtml+spanP+"hay "+ntramiteP+" Tr&aacute;mite(s) a punto de desbordar. Favor verificar</span><br>";
                                        if (ntramiteD>0)
                                           elHtml=elHtml+spanD+"hay "+ntramiteD+" Tr&aacute;mite(s) desbordados. Favor verificar</span><br>"; 
                                        nnn++;
                                    }
                                     if (nzonaP!=0||nzonaD!=0){
                                        elHtml=elHtml+nnn+".) ZONAS: <br/>";
                                        if (nzonaP>0)
                                            elHtml=elHtml+spanP+"hay "+nzonaP+" Zona(s) a punto de desbordar. Favor verificar</span><br>";
                                        if (nzonaD>0)
                                           elHtml=elHtml+spanD+"hay "+nzonaD+" Zona(s) desbordadas. Favor verificar</span><br>"; 
                                        nnn++;
                                    }  
                                    if (nseccionP!=0||nseccionD!=0){
                                        elHtml=elHtml+nnn+".) SECCIONES: <br/>"
                                        if (nseccionP>0)
                                             elHtml=elHtml+spanP+"hay "+nseccionP+" Secci&oacute;n(es) a punto de desbordar. Favor verificar</span><br>";
                                         if (nseccionD>0)
                                             elHtml=elHtml+spanD+"hay "+nseccionD+" Secci&oacute;n(es) desbordadas. Favor verificar</span><br>"; 
                                        nnn++;
                                    }
                                    if (ncentroP!=0||ncentroD!=0){
                                        if (ncentroP>0) {
                                            elHtml=elHtml+nnn+".) CENTRO DE SERVICIO: <br/>"+spanP+"Centro de Servicio a punto de desbordar. Favor verificar";
                                        }else{
                                            elHtml=elHtml+nnn+".) CENTRO DE SERVICIO: <br/>"+spanD+"Centro de Servicio desbordado. Favor verificar";
                                        }
                                        nnn++;
                                    }
                                     if (ncentroD!=0 ||nzonaD!=0|| ntramiteD!=0)
                                         eltipo=1;
                                    if (elHtml!=""){                                       
                                        showMsg(elHtml,eltipo);
                                    }
                                
                                                          
                        }
		    },
		    error:function(data,status,er) { 
		        //alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
                        var errono=1;
		    }
		});
            
        }
        function isEmptyObject(obj){
            return JSON.stringify(obj) === '{}';
        }        

        function showMsg(message,tipo) {    
                //if(isEmptyObject(message)) return;
                var eltipo=BootstrapDialog.TYPE_WARNING;
                if (tipo==1) 
                    eltipo=BootstrapDialog.TYPE_DANGER;
                BootstrapDialog.show({
                    type: eltipo,
                    title: "Alerta",
                    message: message,                    
                    buttons: [{
                            label: 'Cerrar',
                            cssClass: 'btn-primary',
                            action: function(dialog) {                               
                                dialog.close();
                                setTimeout(getAlertasAl,120000);
                            }
                    }]
		});
           
        }