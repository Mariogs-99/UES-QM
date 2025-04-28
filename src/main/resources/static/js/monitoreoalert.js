$(document).ready(function () {
    setTimeout(trig_alerta, 3000);
});//del ready
function trig_alerta() {
    getAlerta();
}

function isEmptyObject(obj) {
    return JSON.stringify(obj) === '{}';
}

function mostrarAlertaError(title, content, type) {

    $.msgBox({
        title: title,
        content: content,
        type: type,
        buttons: [{value: "Cerrar"}],
        opacity: 0.5,
        autoClose: false
    });

};

function getAlerta(){
           var json = { "id":1};
	   $.ajax({ 
		    url: window.location.protocol + "//" + window.location.host+"/colas/alrts/getAlerta?id=1",
		    type: 'GET',                     
		    dataType: 'json', 		    
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) { 
				if(isEmptyObject(data)){
				   mostrarAlertaError("Error","Error sumo y profundo","error");
					
				}else{
				//alert(JSON.stringify(data));
                                    var alerta="";                                     
                                    for(i=0;i<data.lista.length;i++){ 
                                        if (data.lista[i].tipo=="TRAMITE"){
                                        if(parseInt(data.lista[i].porcentaje) >= 80 && parseInt(data.lista[i].porcentaje)<100 ){
                                            alerta +="<div class='lacajaW'><table width=100% border=2><tr><td rowspan=2><img src='/colas/images/alert.png' alt='alertas' width='48px' height='48px'/>"+
                                            "</td><td><b> Pr&oacute;ximo a Desbordar "+data.lista[i].hora+"</b><span style=' margin-left: 150px;'>"+data.lista[i].tipo+"</span></td><td>&nbsp;</td><td>&nbsp;</td></tr>"+
                                            "<tr><td> La cola del tr&aacute;mite "+data.lista[i].nombre+" esta a punto de desbordar!</td><td align='right'> Actual: <span class='divP'>"+ data.lista[i].esperado+"</span></td><td align='right'>Esperando</td></tr></table></div>";	 
                                        }
                                        if(parseInt(data.lista[i].porcentaje) >=100){
                                            alerta +="<div class='lacaja'><table width=100% border=2><tr><td rowspan=2><img src='/colas/images/alert.png' alt='alertas' width='48px' height='48px'/>"+
                                            "</td><td><b>Desbordamiento de colas  "+" "+data.lista[i].hora+" </b><span style=' margin-left: 150px;'>"+data.lista[i].tipo+"</span></td><td>&nbsp;</td><td>&nbsp;</td></tr>"+
                                            "<tr><td>La cola del tr&aacute;mite "+data.lista[i].nombre+" esta al m&aacute;ximo!</td><td align='right'> Actual: <span class='divP'>"+ data.lista[i].esperado+"</span></td><td align='right'>Esperando</td></tr>"+"</table></div>";
                                        }
                                      }
                                      
                                      if (data.lista[i].tipo=="SECCION"){
                                        if(parseInt(data.lista[i].porcentaje) >= 80 && parseInt(data.lista[i].porcentaje)<100 ){
                                            alerta +="<div class='lacajaW'><table width=100% border=2><tr><td rowspan=2><img src='/colas/images/alert.png' alt='alertas' width='48px' height='48px'/>"+
                                            "</td><td><b> Pr&oacute;ximo a Desbordar "+data.lista[i].hora+"</b><span style=' margin-left: 150px;'>"+data.lista[i].tipo+"</span></td><td>&nbsp;</td><td>&nbsp;</td></tr>"+
                                            "<tr><td> La Secci&oacute;n "+data.lista[i].nombre+" esta a punto de desbordar!</td><td align='right'> Actual: <span class='divP'>"+ data.lista[i].esperado+"</span></td><td align='right'>Esperando</td></tr></table></div>";	 
                                        }
                                        if(parseInt(data.lista[i].porcentaje) >=100){
                                            alerta +="<div class='lacaja'><table width=100% border=2><tr><td rowspan=2><img src='/colas/images/alert.png' alt='alertas' width='48px' height='48px'/>"+
                                            "</td><td><b>Desbordamiento de Secci&oacute;n  "+" "+data.lista[i].hora+" </b><span style=' margin-left: 150px;'>"+data.lista[i].tipo+"</span></td><td>&nbsp;</td><td>&nbsp;</td></tr>"+
                                            "<tr><td>La Secci&oacute;n "+data.lista[i].nombre+" esta al m&aacute;ximo!</td><td align='right'> Actual: <span class='divP'>"+ data.lista[i].esperado+"</span></td><td align='right'>Esperando</td></tr>"+"</table></div>";
                                        }
                                      }
                                        if (data.lista[i].tipo=="ZONA" || data.lista[i].tipo=="CENTRO SERVICIO"){
                                        if(parseInt(data.lista[i].porcentaje) >= 80 && parseInt(data.lista[i].porcentaje)<100 ){
                                            alerta +="<div class='lacajaW'><table width=100% border=2><tr><td rowspan=2><img src='/colas/images/alert.png' alt='alertas' width='48px' height='48px'/>"+
                                            "</td><td><b> Pr&oacute;ximo a Desbordar "+data.lista[i].hora+"</b><span style='margin-left: 150px;'>"+data.lista[i].tipo+"</span></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>"+
                                            "<tr><td> La Zona "+data.lista[i].nombre+" esta a punto de desbordar!</td><td>Capacidad: "+data.lista[i].capacidad+"</td><td align='right'> Actual: <span class='divP'>"+ data.lista[i].esperado+"</span></td><td align='right'>Esperando</td></tr></table></div>";	 
                                        }
                                        var tipo=""
                                        if (data.lista[i].tipo=="ZONA") 
                                            tipo="zona";
                                        else
                                            tipo="Centro de Servicio"
                                        if(parseInt(data.lista[i].porcentaje) >=100){
                                        alerta +="<div class='lacaja'><table width=100% border=2><tr><td rowspan=2><img src='/colas/images/alert.png' alt='alertas' width='48px' height='48px'/>"+
                                        "</td><td><b>Desbordamiento de "+tipo+" "+data.lista[i].hora+" </b><span style='margin-left: 150px;'>"+data.lista[i].tipo+"</span></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>"+
                                        "<tr><td>"+tipo+" "+data.lista[i].nombre+" esta al m&aacute;ximo!</td><td>Capacidad: "+data.lista[i].capacidad+"</td><td align='right'> Actual: <span class='divP'>"+ data.lista[i].esperado+"</span></td><td align='right'>Esperando</td></tr>"+"</table></div>";
                                        }
                                      }//zona
                                    };
                                 if (alerta==""){
                                      alerta +="<div class='lacajaG' style='height:50px;width:100%;'><center>Ninguna Alerta</center></div>"
                                 }
                                    
                                
                                $("#caja").html(alerta);                             
                        }
                        setTimeout(trig_alerta,60000);
		    },
		    error:function(data,status,er) { 
		        alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
		    }
		});
                
            }
   