/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var ventimp;
var F;

function readTIPO(input) {
    if ($('#stipo').val()=="audio")
             $('#reproductor').html("<audio id='thevideo' width='250' height='200' controls='true'> El Browser no soporta el componente audio </audio> ");
         else    
             $('#reproductor').html("<video id='thevideo' width='250' height='200' controls='true'> El Browser no soporta el componente audio </audio> ");             
         $('#contenido').trigger("change");
     }

function readURL(input) {
   
    if ($('#stipo').val()=="audio")
             $('#reproductor').html("<audio id='thevideo' width='250' height='200' controls='true'> El Browser no soporta el componente audio </audio> ");
         else    
             $('#reproductor').html("<video id='thevideo' width='250' height='200' controls='true'> El Browser no soporta el componente audio </audio> ");             
      if (input.files && input.files[0]) {
         var reader = new FileReader();
         
         reader.onload = function (e) {
         $('#thevideo')
            .attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
      }

}
   
   
   function cargar(){
       //if (1==1) return;
      var t=parseInt($('#nDuracion').text());
      var Hd = Math.floor(t/3600);
      var rem = t % 3600;
      var Md =Math.floor(rem/60);
      rem=rem % 60;
      var SsS=Hd+":"+Md+":"+rem;
      $('#nDuracion').text(SsS);
      var id=$('#nMultimediaId').val();
      //alert("ido "+id);
      var json = {"id" : id};
//      $.ajax({ 
//		    url: window.location.protocol + "//" + window.location.host+"/colas/multimedia/getBlob",
//		    type: 'POST', 
//                    async:false,
//		    dataType: 'json', 
//		    data: JSON.stringify(json),
//		    contentType: 'application/json',
//		    mimeType: 'application/json',
//		    success: function(data) { 
//				if(isEmptyObject(data)){					
//						mostrarAlertaError("Error","Error al cargar","error");
//					
//				}else{
//			           $('#patho').val(data.elBlob);
//                                    //$('#thevideo').attr('src',data.elBlob);                                   
//                                  
//                                 // $('#thevideo').attr('src','/colas/tmp/bbb.mp4');
//                                        
//				}
//		    },
//		    error:function(data,status,er) { 
//		        alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
//		    }
//		});
   }
   
   function isEmptyObject(obj){
    return JSON.stringify(obj) === '{}';
}

function mostrarAlertaError(title, content, type){
	
	$.msgBox({
		title: title,
		content: content,
		type: type,
		buttons: [{ value: "Cerrar" }],
		opacity: 0.5,
		autoClose:false
	});	
};

function mostrarWait(title, content, type){
	
	$.msgBox({
		title: title,
		content: content,
		type: type,
		//buttons: [{ value: "Cerrar" }],
		opacity: 0.5,
		autoClose:false
	});
	
};

function guardo(modo){
    console.log("ENTRANDO A GUARDAR.....");
    event.preventDefault();
    //return;
      var sefue=true 
      //if ($('#flag').val()==0){
          
          if(jQuery("#stipo").val() === "0"){
                        jQuery('#helpBlockEstado').text("Debe Seleccionar un Tipo");
                        $("#divtipo").addClass("has-error");
                        sefue=false;
                    }else{
                        $("#divtipo").removeClass("has-error");
                        jQuery('#helpBlockEstado').text("");
                    }
          
          if (jQuery('#contenido').val()==="" && $('#tpage').val()=="N"){
          jQuery('#helpcontenido').text("Seleccione un archivo");
                        $("#divContenido").addClass("has-error");
                        sefue=false;
                    }else{
                        $("#divContenido").removeClass("has-error");
                        jQuery('#helpcontenido').text("");
                    }
          
       //campo de duración-inicio   
       if ($('#Hdur').val()=="") $('#Hdur').val("0")
       if ($('#Mdur').val()=="") $('#Hdur').val("0")
       if ($('#Sdur').val()=="") $('#Hdur').val("0")
       var Hd=parseInt($('#Hdur').val());
       var Md=parseInt($('#Mdur').val());
       var Sd=parseInt($('#Sdur').val());
       if(Hd<0){
           jQuery('#helpBlockDuracion').text("N&uacute;mero de horas debe ser mayor que cero");
           $("#idduracion").addClass("has-error");
           sefue=false;            
       }else if (Md < 0 || Md >60){
           jQuery('#helpBlockDuracion').text("Número de minutos debe esatr entre 0 y 60");
           $("#idduracion").addClass("has-error");
           sefue=false;        
       }else if (Sd < 0 || Sd >60){
            jQuery('#helpBlockDuracion').text("N&uacute;mero de segundos debe esatr entre 0 y 60");
           $("#idduracion").addClass("has-error");
           sefue=false; 
       } else {         
            var t=Hd*60*60 + Md*60+Sd;            
            $('#nDuracion').val(t);
            $("#idduracion").removeClass("has-error");
            jQuery('#helpBlockDuracion').text("");                   
       }      
                
            //campo de duración-fin    
                
           if(jQuery("#fimultimedia").val()==""){
           jQuery('#helpBlockfivigencia').text("Complete este campo");
                        $("#fiVigenciaDiv").addClass("has-error");
                        sefue=false;            
                }else {
                     $("#fiVigenciaDiv").removeClass("has-error");
                        jQuery('#helpBlockfivigencia').text("");
                }
                
                if(jQuery("#ffmultimedia").val()==""){
           jQuery('#helpBlockffvigencia').text("Complete este campo");
                        $("#ffVigenciaDiv").addClass("has-error");
                        sefue=false;            
                }else {
                     $("#ffVigenciaDiv").removeClass("has-error");
                        jQuery('#helpBlockffvigencia').text("");
                }
                
                
                    if(jQuery("#bActiva").val() === "0"){
                        jQuery('#helpBlockActiva').text("Debe Seleccionar un estado");
                        $("#divEstado").addClass("has-error");
                        sefue=false;
                    }else{
                        $("#divEstado").removeClass("has-error");
                        jQuery('#helpBlockActiva').text("");
                    }
                    
                  if (jQuery('#sRuta').val()===""){
                        jQuery('#helpruta').text("Complete este campo");
                        $("#idruta").addClass("has-error");
                        sefue=false;
                    }else{
                        $("#idruta").removeClass("has-error");
                        jQuery('#helpruta').text("");
                    }
                    
                    if (jQuery('#dMultimedia').val()===""){
                        jQuery('#helpdmulti').text("Complete este campo");
                        $("#divdmulti").addClass("has-error");
                        sefue=false;
                    }else{
                        $("#divdmulti").removeClass("has-error");
                        jQuery('#helpdmulti').text("");
                    }
                    
                    
                     
                    if (!sefue){
                       event.preventDefault();
                       return;
                   }
                   //event.preventDefault();
                  
    //mostrarWait("Guardando...","Guardando datos, Favor Espere...","info");
    $('#esperar').css("top","200px");
    $('#esperar').css("left","600px")
//    var id="";
//    if (modo==0)
//        id="*";
//    else
//        id=$('#nMultimediaId').val();
//    
//    var tipo=$('#stipo').val()
//    var archi=$('#contenido').val()
//    //alert("archi "+archi+" y el id "+id);
//    if (archi=="")
//        archi="*";
//    //alert("archi "+archi+" y el id "+id);
//    var img=$('#thevideo').attr('src');
//    var dur=$('#nDuracion').val();
//    var fi=$('#fimultimedia').val();
//    var ff=$('#ffmultimedia').val();
//    var acti=$('#bActiva').val();
//    var ruta=$('#sRuta').val();
//    var des=$('#dMultimedia').val();
//    var json = {"id" : id,"stipo" :tipo,"archi" :archi,"video" : img,"duracion" : dur,"fi" :fi,"ff" :ff,"acti" :acti,"ruta" :ruta,"descripcion" :des};
//    //alert(JSON.stringify(json));
//    $.ajax({ 
//		    url: window.location.protocol + "//" + window.location.host+"/colas/multimedia/saveMulti",
//		    type: 'POST', 
//                    async:false,
//		    dataType: 'json', 
//		    data: JSON.stringify(json),
//		    contentType: 'application/json',
//		    mimeType: 'application/json',
//		    success: function(data) { 
//				if(isEmptyObject(data)){					
//						mostrarAlertaError("Error","Error al guardar","error");
//					
//				}else{
//                                        //alert(data);
//					var iii=1;
//                                        $('#flag').val(1);
//                                        $('#formServicio').trigger("submit");
//				}
//		    },
//		    error:function(data,status,er) { 
//		        alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
//		    }
//		});
            //}
}
function horror(){
    //alert('Horroroso');
    var pc=$('#patho').val();
     $('#thevideo').attr('src',pc); 
}
function visualizar(){
    var pc=$('#patho').val();
    ventimp=window.open("", "", "width=620, height=520");
    var fuente='<video id="thevideo" width="600" height="500" controls="true" poster="/colas/images/loading.jpg">Browser no soporta tag Video/audio</video>';
    ventimp.document.write(fuente);    
    ventimp.document.close();
    F=setInterval(loadvideo,5000);
}
function loadvideo(){
    clearInterval(F);
    var id=$('#nMultimediaId').val();
    var json = {"id" : id};
          $.ajax({ 
		    url: window.location.protocol + "//" + window.location.host+"/colas/multimedia/getBlob",
		    type: 'POST', 
                    async:false,
		    dataType: 'json', 
		    data: JSON.stringify(json),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) { 
				if(isEmptyObject(data)){					
						mostrarAlertaError("Error","Error al cargar","error");
					
				}else{
			           $('#patho').val(data.elBlob);
                                   ventimp.document.getElementById('thevideo').src=data.elBlob;                                            
                                   ventimp.document.getElementById('thevideo').play();
                                    //$('#thevideo').attr('src',data.elBlob);                                   
                                  
                                 // $('#thevideo').attr('src','/colas/tmp/bbb.mp4');
                                        
				}
		    },
		    error:function(data,status,er) { 
		        alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
		    }
		});
     //$('#thevideo').attr('src',pc); 
     //$('#thevideo').trigger("play");
}

function ajustes() {
    var t = $('#nDuracion').val();
    var div = Math.floor(t / 3600);
    var rem = t % 3600;
    $('#Hdur').val(div);
    div = Math.floor(rem / 60);
    rem = rem % 60;
    $('#Mdur').val(div);
    $('#Sdur').val(rem);
}