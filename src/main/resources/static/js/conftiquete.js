
$(document).ready(function(){	

$('#tabElement > tbody > tr > td').on('click',function(){
	var col = $(this).parent().children().index($(this));
	var row = $(this).parent().parent().children().index($(this).parent());
	if ($('#oculto').val()=="") return;
	if ($('#valrow').val()!= ""){
		//limpieza
		ref=$('#refrow').val();
		$(ref).html($('#valrow').val());
	}
	document.body.style.cursor="copy";
	document.getElementById("inpdat").style.cursor="copy";
	trow=row+1;
	var ref = '#tabElement tr:nth-child('+trow+') td:nth-child(2)';
	$('#refrow').val(ref);
	var texto=$(ref).text();
	$('#valrow').val(texto);	
	$(ref).html("<span style='color:red;font-size:24px'>"+texto+"</span>");
	
});
	
 $(document).bind('keydown',function(e){
  if ( e.which != 27 && e.which != 13 ) return;
       
   if ($('#oculto').val() == "") {
	   document.body.style.cursor="auto";
	   document.getElementById("inpdat").style.cursor="auto";
	   return;
   }
   document.body.style.cursor="auto";
   
    var i=$('#oculto').val();
    var idvo='#idv'+i;
	var con="";
	if (e.which == 27) con=$('#sihayesc').val();
	if (e.which == 13) con=$('#inpdat').val();
	var divo=con;
	var regvar=/\[[0-9]*\]/g
        //var regvar=/\$\$[0-9]*/g
	//var varin=regvar.exec(con);
	var result=con.match(regvar);
        if (result!=null){
            for (i=0; i<result.length; i++) {
		divo=divo.replace(result[i],"<span style='color:red'>"+result[i]+"</span>");
            }
        }
	//divo=divo.replace(varin,"<span style='color:red'>$"+varin+"</span>");
	$(idvo).html(divo);
	$('#oculto').val("");
	var ref=$('#refrow').val();
	var texto=$('#valrow').val();
	$(ref).html(texto);
	$('#refrow').val("");
	$('#valrow').val("");
	
	$('#editab > tbody > tr > td').on('click',function(){
            if ($(this).attr('class')=='oxx') return;
	var col = $(this).parent().children().index($(this));
		var row = $(this).parent().parent().children().index($(this).parent());
		var idvo='#idv'+row;
                if ($(idvo).attr('class')=='oxx') return;
		var posi=$(idvo).position();
		var contenido=$(idvo).text();		
		$('#oculto').val(row);
		$('#sihayesc').val(contenido);
		$(idvo).html('<div id="errante"><input type="text" id="inpdat" onkeypress="keyp(event,'+row+')" onclick="txtclick()" ></div>');
		$("#inpdat").val(contenido);
		$("#inpdat").focus();
		$('#editab > tbody > tr > td').off('click');
});
   
   
   
  });
  
  $('#cunidadRecepId').on('change',function(){
      load();      
  })
	
});  //fin ready

function keyp(e,i){
	if (e.keyCode!=13) return;
	var idvo='#idv'+i;
	var con=$('#inpdat').val();
	var divo=con     
	//var regvar=/\$\$[0-9]*/g
        var regvar=/\[[0-9]*\]/g
	var result=con.match(regvar);
        if (result!=null){
            for (i=0; i<result.length; i++) {
                    divo=divo.replace(result[i],"<span style='color:red'>"+result[i]+"</span>");
            }
        }
	//divo=divo.replace(varin,"<span style='color:red'>$"+varin+"</span>");
	$(idvo).html(divo);
	$('#oculto').val("");
	var ref=$('#refrow').val();
	var texto=$('#valrow').val();
	$(ref).html(texto);
	$('#refrow').val("");
	$('#valrow').val("");
	
	$('#editab > tbody > tr > td').on('click',function(){
                if ($(this).attr('class')=='oxx') return;
		var col = $(this).parent().children().index($(this));
		var row = $(this).parent().parent().children().index($(this).parent());
		var idvo='#idv'+row;
                if ($(idvo).attr('class')=='oxx') return;
		var posi=$(idvo).position();
		var contenido=$(idvo).text();		
		$('#oculto').val(row);
		$('#sihayesc').val(contenido);
		$(idvo).html('<div id="errante"><input type="text" id="inpdat" onkeypress="keyp(event,'+row+')" onclick="txtclick()" ></div>');
		$("#inpdat").val(contenido);
		$("#inpdat").focus();
		$('#editab > tbody > tr > td').off('click');
});
}//keyp

function load(){
	   $("#errante").hide();
           //var versel=$.session.get('estaVersion')===undefined?-1:$.session.get('estaVersion');
           //$.session.remove('estaVersion');
           lin=0;
	   var divo="";
           var aux="";
           var unidad=$('#cunidadRecepId').val();
           //alert(unidad);
	   //lo de hijo de son 
           var json = { "tramite" : 1,"prioridad" : 1,"holgura" : 0, "opt" : 0, "idTiquete" : 1,"unidad": unidad };
	   $.ajax({ 
		    url: window.location.protocol + "//" + window.location.host+"/colas/conftiquete/getTq",
		    type: 'POST', 
                    dataType: 'json', 
		    data: JSON.stringify(json),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) { 
				if(isEmptyObject(data)){
				   mostrarAlertaError("Error","Error sumo y profundo","error");
					
				}else{
					//alert(JSON.stringify(data));
                                        $('#fmversion').empty();
                                        $('#fmplantillas').empty();
                                        $('#editab tr').each(function() {
                                            $(this).remove();
                                         });
                                        $('#titulocs').html(data.titulo);
                                        if (data.versiones.length!=0){
                                            for (i=0;i<data.versiones.length;i++){
                                                $('#fmversion').append($('<option>', { 
                                                   value: data.versiones[i],
                                                  text : "Versi\u00F3n "+data.versiones[i] 
                                                 }));
                                                }
                                              $('#nversion').prop("checked", false);
                                              $('#nversion').attr("disabled", false);
                                        }else{
                                            $('#fmversion').append($('<option>', { 
                                                value: 0,
                                                text : "Versi\u00F3n 0" 
                                              }));
                                              $('#nversion').prop("checked", "checked");
                                              $('#nversion').attr("disabled", true);
                                        }
                                        $('#fmplantillas').append($('<option>', { 
                                                value: -1,
                                                text : 'Seleccionar Plantilla...' 
                                              }));
                                        for (i=0;i<data.Nplan.length;i++){
                                            aux=data.Nplan[i].split(";")
                                            $('#fmplantillas').append($('<option>', { 
                                                value: aux[0],
                                                text : aux[1] 
                                              }));
                                        }
                                      
                                        var voxx=0;
                                        //var regvar=/\$\$[0-9]*/g;
                                        var regvar=/\[[0-9]*\]/g
                                        for (i=0;i<data.lineas.length;i++){
                                            linea=data.lineas[i];
                                            var result=linea.match(regvar);
                                            if (result != null){
                                                for (j=0; j<result.length; j++) {
                                                    if (result[j]=="$$6" || result[j]=="$$9" ||result[j]=="$$10" ||result[j]=="$$11" ) voxx=1;
                                                    linea=linea.replace(result[j],"<span style='color:red'>"+result[j]+"</span>");
                                                }
                                            }
                                            var clase="inn";
                                            if (voxx) clase="oxx";
                                            divo="<tr><td><div id='idv"+i+"' class='"+clase+"'>"+linea+"</div></td></tr>";
                                            $('#editab > tbody:last').append(divo);
                                            voxx=0;
                                        }
                                        $('#idrow').val(data.nid);
                                        if (data.bactivo==1)
                                            $('#bactivo').prop("checked",true);
                  
                                        $('#lineasrow').val(data.lineas.length)
                                        $('#imagen').attr('src',data.img);
                                        $('#idunidad').val(data.unidad);
                                        
                                        //lo demas
                                        
                                        $('#editab > tbody > tr > td').on('click',function(){                
		var col = $(this).parent().children().index($(this));
		var row = $(this).parent().parent().children().index($(this).parent());
                var idvo='#idv'+row;
                if ($(idvo).attr('class')=='oxx') return;
		var posi=$(idvo).position();
		var contenido=$(idvo).text();		
		$('#oculto').val(row);
		$('#sihayesc').val(contenido);
		$(idvo).html('<div id="errante"><input type="text" id="inpdat" onkeypress="keyp(event,'+row+')" onclick="txtclick()" ></div>');
		$("#inpdat").val(contenido);
		$("#inpdat").focus();
		$('#editab > tbody > tr > td').off('click');	
});

$( "#fmversion" ).change(function() { 
    var length = $('#mySelectList > option').length;
    if (length==0) return;
  var nversion=$('#fmversion').val();
  var unidad=$('#idunidad').val();
  //alert(unidad);
  //cambio de combo
  var json = { "nversion" : nversion,"unidad" : unidad};
  //alert(JSON.stringify(json));
	   $.ajax({ 
		    url: window.location.protocol + "//" + window.location.host+"/colas/conftiquete/chgTq",
		    type: 'POST', 
                    dataType: 'json', 
		    data: JSON.stringify(json),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) { 
				if(isEmptyObject(data)){
				   mostrarAlertaError("Error","Error sumo y profundo","error");
					
				}else{
					//alert(JSON.stringify(data));
                                        $('#editab tr').each(function() {
                                            $(this).remove();
                                         });
                                        var voxx=0;
                                        //var regvar=/\$\$[0-9]*/g;
                                        var regvar=/\[[0-9]*\]/g
                                        for (i=0;i<data.lineas.length;i++){
                                            linea=data.lineas[i];
                                            var result=linea.match(regvar);
                                            if (result != null){
                                                for (j=0; j<result.length; j++) {
                                                    if (result[j]=="$$6" || result[j]=="$$9" ||result[j]=="$$10" ||result[j]=="$$11" ) voxx=1;
                                                    linea=linea.replace(result[j],"<span style='color:red'>"+result[j]+"</span>");
                                                }
                                            }
                                            var clase="inn";
                                            if (voxx) clase="oxx";
                                            divo="<tr><td><div id='idv"+i+"' class='"+clase+"'>"+linea+"</div></td></tr>";
                                            $('#editab > tbody:last').append(divo);
                                            voxx=0;
                                        }
                                        $('#idrow').val(data.nid);
                                        if (data.bactivo==1)
                                            $('#bactivo').prop("checked",true);
                                        else
                                            $('#bactivo').prop("checked",false);
                  
                                        $('#lineasrow').val(data.lineas.length)
                                        $('#imagen').attr('src',data.img);                                        
				}
		    },
		    error:function(data,status,er) { 
		        alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
		    }
		});
                
       $('#editab > tbody > tr > td').on('click',function(){                
		var col = $(this).parent().children().index($(this));
		var row = $(this).parent().parent().children().index($(this).parent());
                var idvo='#idv'+row;
                if ($(idvo).attr('class')=='oxx') return;
		var posi=$(idvo).position();
		var contenido=$(idvo).text();		
		$('#oculto').val(row);
		$('#sihayesc').val(contenido);
		$(idvo).html('<div id="errante"><input type="text" id="inpdat" onkeypress="keyp(event,'+row+')" onclick="txtclick()" ></div>');
		$("#inpdat").val(contenido);
		$("#inpdat").focus();
		$('#editab > tbody > tr > td').off('click');	
});
  
});

$( "#fmplantillas" ).change(function() {
    var npl=$('#fmplantillas').val();
    if (npl==-1) return;
    $.msgBox({
        title: "Confirmar",
        content:"Est\u00E1 seguro(a) de aplicar Plantilla?",
        type: "confirm",
        async:false,
        buttons: [{ value: "Si" }, { value: "No" }],
        success: function (result) {
        if (result == "Si") {
            //desde
            
            var nplantilla=$('#fmplantillas').val();
           var json = { "nplantilla" : nplantilla};
           //alert(JSON.stringify(json));
  	   $.ajax({ 
		    url: window.location.protocol + "//" + window.location.host+"/colas/conftiquete/reaPla",
		    type: 'POST', 
                    dataType: 'json', 
		    data: JSON.stringify(json),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) { 
				if(isEmptyObject(data)){
				   mostrarAlertaError("Error","Error sumo y profundo","error");
					
				}else{
					//alert(JSON.stringify(data));
                                        $('#editab tr').each(function() {
                                            $(this).remove();
                                         });
                                        var voxx=0;
                                        //var regvar=/\$\$[0-9]*/g;
                                        var regvar=/\[[0-9]*\]/g
                                        for (i=0;i<data.lineas.length;i++){
                                            linea=data.lineas[i];
                                            var result=linea.match(regvar);
                                            if (result != null){
                                                for (j=0; j<result.length; j++) {
                                                    if (result[j]=="$$6" || result[j]=="$$9" ||result[j]=="$$10" ||result[j]=="$$11" ) voxx=1;
                                                    linea=linea.replace(result[j],"<span style='color:red'>"+result[j]+"</span>");
                                                }
                                            }
                                            var clase="inn";
                                            if (voxx) clase="oxx";
                                            divo="<tr><td><div id='idv"+i+"' class='"+clase+"'>"+linea+"</div></td></tr>";
                                            $('#editab > tbody:last').append(divo);
                                            voxx=0;
                                        }
                                        $('#lineasrow').val(data.lineas.length);                                       
				}
		    },
		    error:function(data,status,er) { 
		        alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
		    }
		});
                
       $('#editab > tbody > tr > td').on('click',function(){                
		var col = $(this).parent().children().index($(this));
		var row = $(this).parent().parent().children().index($(this).parent());
                var idvo='#idv'+row;
                if ($(idvo).attr('class')=='oxx') return;
		var posi=$(idvo).position();
		var contenido=$(idvo).text();		
		$('#oculto').val(row);
		$('#sihayesc').val(contenido);
		$(idvo).html('<div id="errante"><input type="text" id="inpdat" onkeypress="keyp(event,'+row+')" onclick="txtclick()" ></div>');
		$("#inpdat").val(contenido);
		$("#inpdat").focus();
		$('#editab > tbody > tr > td').off('click');	
            });
            
            
            
            //hasta
            }
        }
    });    
       
});
                                        
                                        //fin de lo demas
                                        
                                        
                                        
				}
		    },
		    error:function(data,status,er) { 
		        alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
		    }
		});           
           //fin de son

} //load

function txtclick(){
	if ($('#valrow').val()=="") return;
	var txtctl=document.getElementById ("inpdat");
	var pos=txtctl.selectionStart;
	var texto=$("#inpdat").val();
	var newtexto=texto.substring(0, pos)+$('#valrow').val()+texto.substring(pos);
	var texto=$("#inpdat").val(newtexto);
	document.body.style.cursor="auto";
	document.getElementById("inpdat").style.cursor="auto";
	ref=$('#refrow').val();
	$(ref).html($('#valrow').val());
	$('#refrow').val("");
	$('#valrow').val("");
}

function isEmptyObject(obj){
    return JSON.stringify(obj) === '{}';
}

function readURL(input) {
     if (input.files && input.files[0]) {
         var reader = new FileReader();
         reader.onload = function (e) {
         $('#imagen')
            .attr('src', e.target.result)
           .width(65)
           .height(45);
        };
        reader.readAsDataURL(input.files[0]);
      }
   }
   
   function borarimg(){
       $('#imagen').attr('src','#');
   }
   
function Guardar(){
    if ($('#oculto').val() != ""){
        mostrarAlertaError ('Favor Finalizar la edici\u00F3n antes de guardar.')
        return;
    }    
    var id=$('#idrow').val();
    var img=$('#imagen').attr('src');
    var unidad=$('#idunidad').val();
    var bactiva=$('#bactivo').prop("checked")?1:0;
    var nueva=$('#nversion').prop("checked")?1:0;
    var enca='<html><head><meta charset="UTF-8"><estilo></head><body><div class="marco" style="width:300px; margin:4px auto;"><center>';
    var piepa='</center></div></body></html>';
    var numlin=$('#lineasrow').val();
    var nversion=$('#fmversion').val();
    var idvo="";
    var txthtml="";
    for (i=0;i<numlin;i++){
        idvo="#idv"+i;
        txthtml=txthtml+'<div class="inn">'+$(idvo).text()+'</div>';
    }
    txthtml=enca+txthtml+piepa;
    var ok=0;
    //alert(txthtml);
    var json = {"id" : id,"img" : img,"unidad" : unidad, "bactiva" :bactiva,"nueva":nueva,"xcontenido":txthtml,"nversion":nversion};
    		$.ajax({ 
		    url: window.location.protocol + "//" + window.location.host+"/colas/conftiquete/saveTq",
		    type: 'POST', 
                    dataType: 'json', 
		    data: JSON.stringify(json),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) { 
				if(isEmptyObject(data)){					
						mostrarAlertaError("Error","Error al guardad","error");
					
				}else{
					//Data ok here
                                        $('#fmversion').empty();
                                        for (i=0;i<data.versiones.length;i++){
                                            $('#fmversion').append($('<option>', { 
                                                value: data.versiones[i],
                                                text : "Versi\u00F3n "+data.versiones[i] 
                                              }));
                                        }
                                         $('#idrow').val(data.id);
                                        if (data.bactivo==1)
                                            $('#bactivo').prop("checked",true);
                                        else
                                            $('#bactivo').prop("checked",false);                                        
                                        //$('#lineasrow').val(data.lineas.length)
                                        var nver=data.nversion;
                                        $('#fmversion option[value='+nver+']').attr('selected','selected');
					mostrarAlertaError("Informaci\u00F3n","Registro Guardado","info");
                                         $('#nversion').prop("checked", "");
                                              $('#nversion').attr("disabled", false);
                                        //alert("Guardado");
                                        ok=1;
				}
		    },
		    error:function(data,status,er) { 
		        alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
		    }
		});
                                
}	

function visualizar(){
    var id=$('#idrow').val();
   
    //alert(txthtml);
    var json = {"id" : id};
    //alert(JSON.stringify(json));	
		$.ajax({ 
		    url: window.location.protocol + "//" + window.location.host+"/colas/conftiquete/mirarTq",
		    type: 'POST', 
                    dataType: 'json', 
		    data: JSON.stringify(json),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) { 
				if(isEmptyObject(data)){					
						mostrarAlertaError("Error","Error al guardad","error");
					
				}else{
					//Data ok here
                                        //alert(JSON.stringify(data));
                                        var ventimp=window.open("", "", "width=350, height=405");
                                            ventimp.document.write(data.lineas);
                                            ventimp.document.getElementById('theimg').src=data.img;
                                            ventimp.document.close();
                                        
				}
		    },
		    error:function(data,status,er) { 
		        alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
		    }
		});                          
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
function mostrarConfirmar(title, content){
      $.msgBox({
        title: title,
        content:content,
        type: "confirm",
        async:false,
        buttons: [{ value: "Si" }, { value: "No" }],
        success: function (result) {
        if (result == "Si") {
            ret=true;
            }
        }
    });
return ret;
}


